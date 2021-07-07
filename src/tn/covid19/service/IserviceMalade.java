
package tn.covid19.service;

import java.sql.SQLException;
import java.util.ArrayList;
import tn.covid19.entities.Malade;


public interface IserviceMalade {
  public void addMalade(Malade Ma) throws SQLException;
    
    public ArrayList<Malade> getMalade() throws SQLException;
    
    public Malade getMalbyId(int id)  throws SQLException;
        
    public void updateMalade(Malade m,int id) throws SQLException;
    
    public void deleteMalade(int id) throws SQLException;
    
     public Malade getMalbyNom(String Nom_Malade)  throws SQLException;  
}
