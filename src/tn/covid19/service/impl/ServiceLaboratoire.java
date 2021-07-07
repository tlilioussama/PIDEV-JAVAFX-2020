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
import tn.covid19.entities.Laboratoire;
import tn.covid19.service.IserviceLaboratoire;
import tn.covid19.utils.MyDB;

/**
 *
 * @author Tlili Oussama
 */
public class ServiceLaboratoire implements IserviceLaboratoire{
    
    
    private final Connection cnx;

    public ServiceLaboratoire() {
        cnx=MyDB.getInstance().getConnection();
    }
    /*
@Override
    public void addLaboratoire(Laboratoire lo) throws SQLException {
        
         String request="INSERT INTO laboratoire (`id_labo`,`nom_labo`,`nbrs_des_anaylses`,`nbrs_test_positifs`) VALUES (?,?,?,?)";
         try {
             PreparedStatement ps = cnx.prepareStatement(request);
             ps.setInt(1, lo.getId_labo());
             ps.setString(2, lo.getNom_labo());
             ps.setInt(3, lo.getNbrs_des_anaylses());
             ps.setInt(4, lo.getNbr_test_positif());
             
             
             ps.executeUpdate();
         } catch (SQLException ex) {
             Logger.getLogger(ServiceLaboratoire.class.getName()).log(Level.SEVERE, null, ex);
         }
        
        
    }
    */
   

    @Override
     public void addLaboratoire(Laboratoire lo) throws SQLException{
        String request = "INSERT INTO `laboratoire` (`id_labo`,`nom_labo`,`nbrs_des_anaylses`,`nbrs_test_positifs`)"
                + "VALUES (NULL, '"  + lo.getNom_labo()+ "', '"
                + lo.getNbrs_des_anaylses()+ "', '"
                + lo.getNbr_test_positif()+ "')";
               
                
        Statement stm = cnx.createStatement();
        stm.executeUpdate(request);
    }
   
    
    
    @Override
     public ArrayList<Laboratoire> getLaboratoires() throws SQLException{
        ArrayList<Laboratoire> results = new ArrayList<>();
        
        String query = "SELECT * FROM `laboratoire`";
        Statement stm = cnx.createStatement();
        ResultSet rst= stm.executeQuery(query);
        while (rst.next()){
            Laboratoire lo = new Laboratoire();
            lo.setId_labo(rst.getInt("id_labo"));
            lo.setNom_labo(rst.getString(2));
            lo.setNbrs_des_anaylses(rst.getInt(3));
            lo.setNbr_test_positif(rst.getInt(4));
            
            results.add(lo);
        }
        
       // System.out.println("list of lab is "+ results.toString());
       
        return results; 
    }
    
    

    @Override
    public Laboratoire getLabobyNom(String nom) throws SQLException {
        
         String query = "SELECT * FROM `laboratoire` where `nom_labo`="+nom;
         Statement stm = cnx.createStatement();
      
          ResultSet rst= stm.executeQuery(query);
             Laboratoire lab = new Laboratoire();
             while (rst.next()) {
             lab.setId_labo(rst.getInt("id_labo"));
            lab.setNom_labo(rst.getString(2));
            lab.setNbrs_des_anaylses(rst.getInt(3));
            lab.setNbr_test_positif(rst.getInt(4));
            
            //System.out.println(lab.toString());
              }
return lab;
    }
        

    @Override
    public void deleteLaabo(Laboratoire l ) throws SQLException{
        String request ="DELETE FROM `laboratoire` WHERE nom_labo='"+l.getNom_labo()+"'";
        Statement stm = cnx.createStatement();
        stm.executeUpdate(request);
    }

    @Override
    public void updateLaboratoire(Laboratoire f, int id) throws SQLException {
       
        
        String request= "UPDATE `laboratoire` SET `nom_labo`=?, `nbrs_des_anaylses`=?, `nbrs_test_positifs`=? WHERE `id_labo`="+id;
              
        PreparedStatement pst = cnx.prepareStatement(request);
        
        pst.setString(1, f.getNom_labo());
        pst.setInt(2, f.getNbrs_des_anaylses());
        pst.setInt(3, f.getNbr_test_positif());
        
        //pst.setInt(7, f.getId_fiche());
      //  System.out.println(pst);
        
        pst.executeUpdate();
    
    }

    @Override
    public Laboratoire getLabobyId(int id) throws SQLException {
        
         String query = "SELECT * FROM `laboratoire` where `id_labo`="+id;
         Statement stm = cnx.createStatement();
      
          ResultSet rst= stm.executeQuery(query);
             Laboratoire lab = new Laboratoire();
             while (rst.next()) {
             lab.setId_labo(rst.getInt("id_labo"));
            lab.setNom_labo(rst.getString(2));
            lab.setNbrs_des_anaylses(rst.getInt(3));
            lab.setNbr_test_positif(rst.getInt(4));
            
          //  System.out.println(lab.toString());
            
       
    }
return lab;
    }
    

 
    
}
