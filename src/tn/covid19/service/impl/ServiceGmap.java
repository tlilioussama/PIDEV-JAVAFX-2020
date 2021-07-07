/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.covid19.service.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import tn.covid19.entities.GMapCoordinates;
import tn.covid19.service.IserviceGmap;
import tn.covid19.utils.MyDB;

/**
 *
 * @author Tlili Oussama
 */
public class ServiceGmap implements IserviceGmap{
    
     private Connection cnx;

    public ServiceGmap() {
        cnx=MyDB.getInstance().getConnection();
    }
    
    @Override
     public void addGmap(GMapCoordinates g) throws SQLException{
        String request = "INSERT INTO `ville` (`id`,`nom`,`latitude`,`longitude`,`nbrs_des_anaylses`,`nbrs_test_positifs`) VALUES (?,?,?,?,?,?)";
       
        try{
            
       PreparedStatement ps =cnx.prepareStatement(request);
       ps.setLong(1,g.getId());
       ps.setString(2,g.getNomVille());
       ps.setDouble(3,g.getLatitude());
       ps.setDouble(4, g.getLongitude());
       ps.setInt(5, g.getNbrs_des_anaylses());
       ps.setInt(6, g.getNbrs_des_anaylses());
       
       
       ps.executeUpdate();
        } catch (SQLException ex){
       // Logger.getLogger(ServiceFicheMalade.class.getName().log(Level.SEVERE, null,ex));
            System.out.println("erreur lors de l'insertion " + ex.getMessage());

        }
       
    }
    
    @Override
     public ArrayList<GMapCoordinates> getVille() throws SQLException{
        ArrayList<GMapCoordinates> results = new ArrayList<>();
        
        String query = "SELECT * FROM `ville`";
        Statement stm = cnx.createStatement();
        ResultSet rst= stm.executeQuery(query);
        while (rst.next()){
            GMapCoordinates v = new GMapCoordinates();            
            v.setId(rst.getLong(1));
            v.setNomVille(rst.getString("nom"));
            
            
            results.add(v);
        }
        
        //System.out.println("list of lab is "+ results.toString());
       
        return results; 
    }
     
    @Override
    public List<GMapCoordinates> getAll() {
        ArrayList<GMapCoordinates> coordinates = new ArrayList<>();
        try {
            
            String query = "SELECT * FROM `ville` WHERE `nbrs_des_anaylses` > 0  ";
            Statement stm = cnx.createStatement();
            ResultSet rst= stm.executeQuery(query);
            
            while (rst.next()){
                GMapCoordinates gMapCoordinates = new GMapCoordinates();
                gMapCoordinates.setId(rst.getLong("id"));
                 gMapCoordinates.setNomVille(rst.getString("nom"));
                gMapCoordinates.setLatitude(rst.getDouble("latitude"));
                gMapCoordinates.setLongitude(rst.getDouble("longitude"));
                gMapCoordinates.setNbrs_des_anaylses(rst.getInt("nbrs_des_anaylses"));
                gMapCoordinates.setNbr_test_positif(rst.getInt("nbrs_test_positifs"));
                
                coordinates.add(gMapCoordinates);
            }
            
        } catch (SQLException e) {
            throw new NoSuchElementException("Cannot find coordinates!");
        }
        
        return coordinates;
    }

    @Override
    public void updateVille(GMapCoordinates ville, Long id) throws SQLException {
      
            String request= "UPDATE `ville` SET `nom`=?, `latitude`=?, `longitude`=? , `nbrs_des_anaylses`=?, `nbrs_test_positifs`=? WHERE `id`="+id;
            
            PreparedStatement pst = cnx.prepareStatement(request);
            
            pst.setString(1, ville.getNomVille());
            pst.setDouble(2, ville.getLatitude());
            pst.setDouble(3, ville.getLongitude());
            pst.setInt(4, ville.getNbrs_des_anaylses());
            pst.setInt(5, ville.getNbr_test_positif());
           // System.out.println(pst);
            
            pst.executeUpdate();
       
    }

    @Override
    public GMapCoordinates getGmapById(int id) throws SQLException {
        
        
         String query = "SELECT * FROM `ville` where `id`="+id;
         Statement stm = cnx.createStatement();
      
          ResultSet rst= stm.executeQuery(query);
             GMapCoordinates gMap = new GMapCoordinates();
             while (rst.next()) {
                gMap.setId(rst.getLong("id"));
                gMap.setNomVille(rst.getString("nom"));
                gMap.setLatitude(rst.getDouble("latitude"));
                gMap.setLongitude(rst.getDouble("longitude"));
                gMap.setNbrs_des_anaylses(rst.getInt("nbrs_des_anaylses"));
                gMap.setNbr_test_positif(rst.getInt("nbrs_des_anaylses"));
            
           // System.out.println(gMap.toString());
            
       
    }
return gMap;   
    }  

    @Override
    public GMapCoordinates getGmapByNom(String name) throws SQLException {
        
        
         String query = "SELECT * FROM `ville` WHERE `nom` ='"+name+"'";
         Statement stm = cnx.createStatement();
      
          ResultSet rst= stm.executeQuery(query);
             GMapCoordinates gMap = new GMapCoordinates();
             while (rst.next()) {
              gMap.setId(rst.getLong("id"));
                gMap.setNomVille(rst.getString("nom"));
                gMap.setLatitude(rst.getDouble("latitude"));
                gMap.setLongitude(rst.getDouble("longitude"));
                gMap.setNbrs_des_anaylses(rst.getInt("nbrs_des_anaylses"));
                gMap.setNbr_test_positif(rst.getInt("nbrs_des_anaylses"));
            
           // System.out.println(gMap.toString());
              }
return gMap;
    }

//String query = "SELECT * FROM `ville` where `nom`="+name;
//         Statement stm = cnx.createStatement();
//      
//             ResultSet rst= stm.executeQuery(query);
//             GMapCoordinates mapos = new GMapCoordinates();
//             while (rst.next()) {
//            mapos.setId(rst.getLong(1));
//            mapos.setNomVille(rst.getString(2));
//            mapos.setLatitude(rst.getDouble(3));
//            mapos.setLongitude(rst.getDouble(4));
//             mapos.setNbrs_des_anaylses(rst.getInt(5));
//            mapos.setNbr_test_positif(rst.getInt(6));
//            
//            
//            
//            System.out.println(mapos.toString());
//            
//       
//    }
//return mapos;
}
   