
package tn.covid19.service.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import tn.covid19.entities.Hopital;
import tn.covid19.service.IserviceHopital;
import tn.covid19.utils.MyDB;

public class ServiceHopital implements IserviceHopital {
    

    
    private final Connection cnx;

    public ServiceHopital() {
        cnx=MyDB.getInstance().getConnection();
    }

    public void addHopital(Hopital ho) throws SQLException {
        
         String request="INSERT INTO Hopital (`id_hop`,`nom_hop`,`adresse_Hop`,`nbr_lit`) VALUES (?,?,?,?)";
         try {
             PreparedStatement ps = cnx.prepareStatement(request);
             ps.setInt(1, ho.getId_Hop());
             ps.setString(2, ho.getNom_Hop());
             ps.setString(3, ho.getAdresse_Hop());
             ps.setInt(4, ho.getNbr_lit());
             
             
             ps.executeUpdate();
         } catch (SQLException ex) {
             Logger.getLogger(ServiceHopital.class.getName()).log(Level.SEVERE, null, ex);
         }
        
        
    }
    
    public Hopital getHopbyNom(String nom_Hop)  throws SQLException{
         String query = "SELECT * FROM hopital where nom_hop='"+nom_Hop+"'";
         Statement stm;
          stm = cnx.createStatement();
      
          ResultSet rst= stm.executeQuery(query);
             Hopital hop = new Hopital();
            while (rst.next()) {
            hop.setId_Hop(rst.getInt(1));
            hop.setNom_Hop(rst.getString(2));
            hop.setAdresse_Hop(rst.getString(3));
            hop.setNbr_lit(rst.getInt(4));
            
          
            
       
    }
return hop;
      }

    public ArrayList<Hopital> getHopital() throws SQLException{
        ArrayList<Hopital> results = new ArrayList<>();
        
        String query = "SELECT * FROM `hopital`";
        Statement stm = cnx.createStatement();
        ResultSet rst= stm.executeQuery(query);
        while (rst.next()){
            Hopital ho = new Hopital();
            ho.setId_Hop(rst.getInt("id_hop"));
            ho.setNom_Hop(rst.getString(2));
            ho.setAdresse_Hop(rst.getString(3));
            ho.setNbr_lit(rst.getInt(4));
            
            results.add(ho);
        }
        
        //System.out.println("list of Hopital is "+ results.toString());
       
        return results; 
    }

    public void deleteHopital(int id) throws SQLException{
        String request ="DELETE  FROM `hopital` WHERE id_hop="+id;
        Statement stm = cnx.createStatement();
        stm.executeUpdate(request);
    }

    public void updateHopital(Hopital f, int id) throws SQLException {
       
        
        String request= "UPDATE `Hopital` SET `nom_Hop`=?, `adresse_Hop`=?, `nbr_lit`=? WHERE `id_Hop`="+id;
              
        PreparedStatement pst = cnx.prepareStatement(request);
        
        pst.setString(1, f.getNom_Hop());
        pst.setString(2, f.getAdresse_Hop());
        pst.setInt(3, f.getNbr_lit());
        
        //pst.setInt(7, f.getId_fiche());
        System.out.println(pst);
        
        pst.executeUpdate();
    
    }
  
    public Hopital getHopbyId(int id) throws SQLException {
        
         String query = "SELECT * FROM `Hopital` where `id_Hop`="+id;
         Statement stm;
          stm = cnx.createStatement();
      
          ResultSet rst= stm.executeQuery(query);
             Hopital hop = new Hopital();
            while (rst.next()) {
            hop.setId_Hop(rst.getInt("id_Hop"));
            hop.setNom_Hop(rst.getString(2));
            hop.setAdresse_Hop(rst.getString(3));
            hop.setNbr_lit(rst.getInt(4));
            
            System.out.println(hop.toString());
            
       
    }
return hop;
    }

}

 


