
package tn.covid19.service.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import tn.covid19.entities.Malade;
import tn.covid19.utils.MyDB;
import tn.covid19.service.IserviceMalade;

public class ServiceMalade implements IserviceMalade {
    


    
     private final Connection cnx;

    public ServiceMalade() {
        cnx= MyDB.getInstance().getConnection();
    }

 public void addMalade(Malade Ma) throws SQLException{
    
     String request="INSERT INTO Malade (`id_Malade`,`nom_Malade`,`prenom_Malade`,`adresse_Malade`,`date_Hospital`,`date_fin_Hospital`) VALUES (?,?,?,?,?,?)";
         try {
             PreparedStatement ps = cnx.prepareStatement(request);
             
            

             LocalDate todayLocalDate = LocalDate.now( ZoneId.of( "Africa/Tunis" ) );
             LocalDate  Local = LocalDate.now().plusMonths(1);
              System.out.println(todayLocalDate);
              System.out.println(Local);   
             java.sql.Date mySqlDate = java.sql.Date.valueOf( todayLocalDate );
             java.sql.Date mySqlDate1 = java.sql.Date.valueOf( Local );
             ps.setInt(1, Ma.getId_malade());
             ps.setString(2, Ma.getNom_Malade());
             ps.setString(3, Ma.getPrenom_malade());
             ps.setString(4, Ma.getAdresse_malade());
             ps.setDate(5, mySqlDate );
             ps.setDate(6, mySqlDate1);
             ps.executeUpdate();
         } catch (SQLException ex) {
             Logger.getLogger(ServiceMalade.class.getName()).log(Level.SEVERE, null, ex);
         }
        
 }
    
    public ArrayList<Malade> getMalade() throws SQLException{
        
         ArrayList<Malade> results = new ArrayList<>();    
        String query = "SELECT * FROM `Malade`";
        Statement stm = cnx.createStatement();
        ResultSet rst= stm.executeQuery(query);
        while (rst.next()){
            Malade ma = new Malade();
            ma.setId_malade(rst.getInt("id_malade"));
            ma.setNom_Malade(rst.getString(2));
            ma.setPrenom_malade(rst.getString(3));
            ma.setAdresse_malade(rst.getString(4));
            ma.setDate_hospital(rst.getDate(5).toLocalDate());
            ma.setDate_fin_hospital(rst.getDate(6).toLocalDate());
            
           // results.add(ma);
        }
        
        //System.out.println("list of Hopital is "+ results.toString());
       
        return results; 
        
     
    }
    
    public Malade getMalbyId(int id)  throws SQLException{
        
         String query = "SELECT * FROM `Malade` where `id_Malade`="+id;
         Statement stm;
          stm = cnx.createStatement();
      
          ResultSet rst= stm.executeQuery(query);
             Malade Ma = new Malade();
            while (rst.next()) {
            Ma.setId_malade(rst.getInt("id_Malade"));
            Ma.setNom_Malade(rst.getString(2));
            Ma.setPrenom_malade(rst.getString(3));
            Ma.setAdresse_malade(rst.getString(4));
            Ma.setDate_hospital(rst.getDate(5).toLocalDate());
            Ma.setDate_fin_hospital(rst.getDate(6).toLocalDate());
            
            System.out.println(Ma.toString());}
     return Ma;   
    }
        
    public void updateMalade(Malade m,int id) throws SQLException{
        
        
             LocalDate todayLocalDate = LocalDate.now( ZoneId.of( "Africa/Tunis" ) );
             LocalDate  Local = LocalDate.now().plusMonths(1);
              System.out.println(todayLocalDate);
              System.out.println(Local);   
             java.sql.Date mySqlDate = java.sql.Date.valueOf( todayLocalDate );
             java.sql.Date mySqlDate1 = java.sql.Date.valueOf( Local );
        
        
        String request= "UPDATE `Malade` SET `Nom_Malade`=?, `Prenom_malade`=?, `Adresse_malade`=? ,`Date_hospital`=? ,`Date_fin_hospital`=? WHERE `id_Malade`="+id;
              
        PreparedStatement pst = cnx.prepareStatement(request);
        
        pst.setString(1, m.getNom_Malade());
        pst.setString(2, m.getPrenom_malade());
        pst.setString(3, m.getAdresse_malade());
        pst.setDate(4,mySqlDate );
        pst.setDate(5, mySqlDate1);
       
        System.out.println(pst);
        
        pst.executeUpdate();
    }
    
    public void deleteMalade(int id) throws SQLException{
       String request ="DELETE  FROM `Malade` WHERE id_Malade="+id;
        Statement stm = cnx.createStatement();
        stm.executeUpdate(request);
 
        
        
    }
    
     public Malade getMalbyNom(String Nom_Malade)  throws SQLException{
      return null;   
     }

  
    
}

