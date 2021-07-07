
package tn.covid19.service;

import java.sql.SQLException;
import java.util.ArrayList;
import tn.covid19.entities.Hopital;


public interface IserviceHopital {
   public void addHopital(Hopital Ho) throws SQLException;
    
    public ArrayList<Hopital> getHopital() throws SQLException;
    
    public Hopital getHopbyId(int id)  throws SQLException;
        
    public void updateHopital(Hopital f,int id) throws SQLException;
    
    public void deleteHopital(int id) throws SQLException;
    
     public Hopital getHopbyNom(String nom_Hop)  throws SQLException;  
}
