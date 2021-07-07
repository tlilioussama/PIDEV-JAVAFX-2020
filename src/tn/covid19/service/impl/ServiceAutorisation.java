
package tn.covid19.service.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import tn.covid19.entities.Autorisation;
import tn.covid19.entities.Utilisateur;

import tn.covid19.utils.MyDB;
import tn.covid19.utils.Session;
import tn.covid19.service.IServiceAutorisation;

public class ServiceAutorisation implements IServiceAutorisation{
    
        private final Connection cnx;

    public ServiceAutorisation() {
        cnx=MyDB.getInstance().getConnection();
    }
    
    
    @Override
    public void AjouterAutorisation(Autorisation a) throws SQLException{
         Utilisateur u = new Utilisateur();
        ServiceUtilisateur su = new ServiceUtilisateur();
                
                    u=su.getUtilisateurByRole(1);
                    System.out.println(u);
                    int p = u.getIdUtilisateur();
                
        String request;
            request = "INSERT INTO `attestation` (`date_att`,`date_fin_att`,`motif_att`,`id_utilisateur`)"
                    + "VALUES ('"+ a.getDateAutorisation()+ "', '" 
                    + a.getFinAutorisation()+ "', '"
                    + a.getMotifsAutorisation()+ "', '"
                    + p +"')";
                System.out.println(request);
                Statement stm = cnx.createStatement();
                stm.executeUpdate(request);
                
               
     
    }
   
        @Override
    public ArrayList<Autorisation> rechercherAutorisationparCin (String cin) throws SQLException{
        ArrayList<Autorisation> results=new ArrayList<>();
        try{
            String query = "SELECT * FROM utilisateur INNER JOIN attestation ON utilisateur.id_utilisateur = attestation.id_utilisateur WHERE utilisateur.cin="+cin+"";
            Connection cnx=MyDB.getInstance().getConnection();
            Statement stm = cnx.createStatement();
            ResultSet rst = stm.executeQuery(query);
           
            while (rst.next()) {
            Autorisation autorisation = new Autorisation();
            autorisation.setInAutorisation(rst.getInt("id_att"));
            
            autorisation.setMotifsAutorisation(rst.getString("motif_att"));
            autorisation.setDateAutorisation(LocalDate.parse(rst.getString("date_att")));
            autorisation.setFinAutorisation(LocalDate.parse(rst.getString("date_fin_att")));
            
            Utilisateur utilisateur =new Utilisateur();
            utilisateur.setNom(rst.getString("nom"));
            utilisateur.setPrenom(rst.getString("prenom"));
            utilisateur.setCin(rst.getString("cin"));
            utilisateur.setAdresse(rst.getString("adresse"));
            utilisateur.setVille(rst.getString("ville"));
            //utilisateur.setCodepostale(rst.getInt("codepostale"));
            utilisateur.setTel(rst.getInt("tel"));
            autorisation.setUtilisateur(utilisateur);
            
             results.add(autorisation);
             
            
            
            System.out.println(autorisation.toString()+utilisateur.toString());
                
            }
            
        }   catch (SQLException ex) {
               System.out.println("ERREUR"+ ex.getMessage());
            }
        return results;
    }
        

    public void deleteAutorisation(String i) throws SQLException{
        String request ="DELETE FROM `attestation` WHERE id_att="+i;
        
        Statement stm = cnx.createStatement();
        stm.executeUpdate(request);
        
    }

    @Override
    public void ModifierAutorisation(Autorisation a) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    
}
