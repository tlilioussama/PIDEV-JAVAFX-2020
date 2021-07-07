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
import tn.covid19.entities.FicheMalade;
import tn.covid19.service.IserviceFicheMalade;
import tn.covid19.utils.MyDB;

/**
 *
 * @author Tlili Oussama
 */
public class ServiceFicheMalade implements IserviceFicheMalade{
    
    private Connection cnx;

    public ServiceFicheMalade() {
        cnx=MyDB.getInstance().getConnection();
    }
    
    


    @Override
     public void addFicheMalade(FicheMalade f) throws SQLException{
        String request = "INSERT INTO `fiche_malade` (`id_fiche`,`nom_malade`,`prenom_malade`,`Age`,`cin`,`ville`,`etat_analyse`,`id_labo`) VALUES (?,?,?,?,?,?,?,?)";
       
        try{
            
       PreparedStatement ps =cnx.prepareStatement(request);
       ps.setInt(1,f.getId_fiche());
       ps.setString(2,f.getNom_malade());
       ps.setString(3,f.getPrenom_malade());
       ps.setInt(4, f.getAge());
       ps.setString(5, f.getCin());
       ps.setString(6, f.getVille());
       ps.setInt(7,f.getEtat_analyse());
        ps.setInt(8,f.getIdLabo());
       
       ps.executeUpdate();
        } catch (SQLException ex){
       // Logger.getLogger(ServiceFicheMalade.class.getName().log(Level.SEVERE, null,ex));
            System.out.println("erreur lors de l'insertion " + ex.getMessage());

        }
       
    }
    
    @Override
     public ArrayList<FicheMalade> getFicheMalade() throws SQLException{
        ArrayList<FicheMalade> results = new ArrayList<>();
        
        String query = "SELECT * FROM `fiche_malade`";
        Statement stm = cnx.createStatement();
        ResultSet rst= stm.executeQuery(query);
        while (rst.next()){
            FicheMalade f = new FicheMalade();
            f.setId_fiche(rst.getInt("id_fiche"));
            f.setNom_malade(rst.getString(2));
            f.setPrenom_malade(rst.getString(3));
            f.setAge(rst.getInt(4));
            f.setCin(rst.getString(5));
            f.setVille(rst.getString(6));
            
            f.setEtat_analyse(rst.getInt(7));
            results.add(f);
        }
         System.out.println(results.toString());
        return results; 
    }
    
    /**
     *
     * @param f
     * @throws SQLException
     */
    @Override
    public void updateFicheMalade(FicheMalade f, int id) throws SQLException{
        String request= "UPDATE `fiche_malade` SET `nom_malade`=?, `prenom_malade`=?, `Age`=?, `cin`=?, `ville`=?, `etat_analyse`=? WHERE `id_fiche`="+id;

        PreparedStatement pst = cnx.prepareStatement(request);
        
        pst.setString(1, f.getNom_malade());
        pst.setString(2, f.getPrenom_malade());
        pst.setInt(3, f.getAge());
        pst.setString(4, f.getCin());
        pst.setString(5, f.getVille());
        pst.setInt(6, f.getEtat_analyse());
        //pst.setInt(7, f.getId_fiche());
        System.out.println(pst);
        
        pst.executeUpdate();
    }
    

  
        
    @Override
    public void deleteFicheMalade(FicheMalade f ) throws SQLException{
        String request ="DELETE FROM `fiche_malade` WHERE cin="+f.getCin();
        Statement stm = cnx.createStatement();
        stm.executeUpdate(request);
    }
    
    @Override
    public FicheMalade getFichebyCIN(String cin) throws SQLException {
        
         String query = "SELECT * FROM fiche_malade where cin='"+cin+"'";
         Statement stm = cnx.createStatement();
      
          ResultSet rst= stm.executeQuery(query);
             FicheMalade fiche = new FicheMalade();
             while (rst.next()) {
            fiche.setId_fiche(rst.getInt(1));
            fiche.setNom_malade(rst.getString(2));
            fiche.setPrenom_malade(rst.getString(3));
            fiche.setAge(rst.getInt(4));
             fiche.setCin(rst.getString(5));
            fiche.setVille(rst.getString(6));
            fiche.setEtat_analyse(rst.getInt(7));
            //fiche.setCin(rst.getString(7));
           // fiche.setIdLabo(rst.getInt(7));
            
            
            System.out.println(fiche.toString());
            
       
    }
return fiche;
    }
    
    
    
}
