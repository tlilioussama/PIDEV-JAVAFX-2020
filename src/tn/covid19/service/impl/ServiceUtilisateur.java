package tn.covid19.service.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import tn.covid19.entities.Utilisateur;
import tn.covid19.service.IServiceUtilisateur;
import tn.covid19.utils.MyDB;




public class ServiceUtilisateur implements IServiceUtilisateur{
    private final Connection cnx;
    
    public ServiceUtilisateur() {
        cnx=MyDB.getInstance().getConnection();
    }    
    @Override
   
    public Utilisateur ajouterUtilisateur(Utilisateur utilisateur) throws SQLException {
      if (getUtilisateurByCIN(utilisateur.getCin())!=null){
          return utilisateur;
        }
        String request = "INSERT INTO `utilisateur` (`id_utilisateur`,`nom`,`prenom`,`cin`,`adresse`,`ville`,`tel`,`mail`,`age`,`role`,`login`, `motDePasse`)"
                 + "VALUES (" 
                 + utilisateur.getIdUtilisateur()+ ", '"
                 + utilisateur.getNom()+ "', '"
                 + utilisateur.getPrenom()+ "', "
                 + utilisateur.getCin()+ ", '"
                 + utilisateur.getAdresse()+ "', '"
                 + utilisateur.getVille()+ "', "
                 + utilisateur.getTel()+ ", '"
                 + utilisateur.getMail()+ "', "
                 + utilisateur.getAge()+ ", "
                 + utilisateur.getRole()+", '"
                 + utilisateur.getLogin()+ "','"
                 + utilisateur.getMotDePasse()+
                 "'"+ 
                 ")";                          
        try {
            Statement stm = cnx.createStatement();
            stm.executeUpdate(request);
            return utilisateur;
        } catch (SQLException ex) {
                System.out.println("Erreur : ajout impssible, utilisateur existe");
                ex.getMessage();
            return null; 
            }            
    }
    @Override
    public Utilisateur getUtilisateurByRole (int role) throws SQLException {
        
        String query = "SELECT * FROM `utilisateur` WHERE role="+role;
        Statement stm;
        try {
            stm = cnx.createStatement();
            ResultSet rst= stm.executeQuery(query);
            if (rst.next()){
                 return new Utilisateur(rst.getInt("id_utilisateur"), rst.getNString("nom"), 
                                        rst.getNString("prenom"), rst.getNString("cin"), rst.getNString("adresse"),
                                        rst.getNString("ville"), rst.getInt("tel"), rst.getNString("mail"), rst.getInt("age"),
                                        rst.getInt("role"), rst.getString("login"),rst.getString("motDePasse"));       
            }
        } catch (SQLException ex) {
            ex.printStackTrace();  
         }       
        return null;
    } 
    @Override
    public Utilisateur getUtilisateurByCIN (String cin) throws SQLException {
        
        String query = "SELECT * FROM `utilisateur` WHERE cin="+cin;
        Statement stm;
         try {
             stm = cnx.createStatement();
             ResultSet rst= stm.executeQuery(query);
             if (rst.next()){
                 return new Utilisateur(rst.getInt("id_utilisateur"), rst.getNString("nom"), 
                         rst.getString("prenom"), rst.getString("cin"), rst.getString("adresse"),
                         rst.getString("ville"), rst.getInt("tel"), rst.getNString("mail"), rst.getInt("age"),
                         rst.getInt("role"), rst.getString("login"),rst.getString("motDePasse"));
                         
                }
         } catch (SQLException ex) {
            ex.printStackTrace();
            
         }
       
        return null;
    }  
    @Override
    public  Utilisateur getUtilisateurByLognAndPWD(String login, String pwd) throws SQLException {
        String query =" SELECT *  FROM UTILISATEUR WHERE login= '"+login+"' AND motDePasse='"+pwd+"' ";
        Statement stm;    
         try {
             stm = cnx.createStatement();
             ResultSet rst= stm.executeQuery(query);
             if (rst.next()){
                 return new Utilisateur(rst.getInt("id_utilisateur"), rst.getNString("nom"), 
                         rst.getNString("prenom"), rst.getNString("cin"), rst.getNString("adresse"),
                         rst.getNString("ville"), rst.getInt("tel"), rst.getNString("mail"), rst.getInt("age"),
                         rst.getInt("role"), rst.getString("login"),rst.getString("motDePasse"));                      
                }
         } catch (SQLException ex) {
            ex.printStackTrace();
            
         }
       
        return null;
    }
    @Override
    public ArrayList<Utilisateur> getUtilisateur() throws SQLException {
         ArrayList<Utilisateur> results = new ArrayList<>();
        
        String query = "SELECT * FROM `utilisateur`";
        Statement stm = cnx.createStatement();
        ResultSet rst= stm.executeQuery(query);
        while (rst.next()){
            Utilisateur u = new Utilisateur();
            
            u.setIdUtilisateur(rst.getInt(1));
            u.setNom(rst.getString(2));
            u.setPrenom(rst.getString(3));
            u.setCin(rst.getString("cin"));
            u.setAdresse(rst.getString(5));
            u.setVille(rst.getString(6));
            u.setAge(rst.getInt(7));
            u.setTel(rst.getInt("tel"));
            u.setLogin(rst.getString("login"));
            u.setMotDePasse(rst.getString("MotDePasse"));
            u.setMail(rst.getString("mail"));  
            
            results.add(u);
        }
        
        return results; 
    }
    @Override
    public ArrayList<Utilisateur> getUtilisateurList() throws SQLException {
         ArrayList<Utilisateur> results = new ArrayList<>();
        
        String query = "SELECT nom, prenom,cin, adresse, tel, mail, age FROM `utilisateur`";
        Statement stm = cnx.createStatement();
        ResultSet rst= stm.executeQuery(query);
        while (rst.next()){
            Utilisateur u = new Utilisateur();
            
            u.setNom(rst.getString("nom"));
            u.setPrenom(rst.getString("prenom")); 
            u.setCin(rst.getString("cin"));
            u.setAdresse(rst.getString("adresse"));          
            u.setAge(rst.getInt("age"));
            u.setTel(rst.getInt("tel"));          
            u.setMail(rst.getString("mail"));  
            
            results.add(u);
        }
        
        return results; 
    }
    @Override
    public void updateUtilisateur(Utilisateur d) throws SQLException {
        String request= "UPDATE `Utilisateur` SET `nom`=?, `prenom`=?,`adresse`=?,`ville`=?,`age`=?,`tel`=?,`mail`=?,`role`=?,`login`=?, `motDePasse`=?"
                +"WHERE `id_utilisateur`=?";

        PreparedStatement pst = cnx.prepareStatement(request);
       
        pst.setString(1, d.getNom());
        pst.setString(2, d.getPrenom());
        pst.setString(3, d.getAdresse());
        pst.setString(4, d.getVille());
        pst.setInt(5, d.getAge());
        pst.setInt(6, d.getTel());
        pst.setString(7, d.getMail());
        pst.setInt(8, d.getRole());
        pst.setString(9, d.getLogin());
        pst.setString(10, d.getMotDePasse());
        pst.setInt(11, d.getIdUtilisateur());
        System.out.println(pst);
        
        pst.executeUpdate();
    }
    @Override
    public void deleteUtilisateur(String cin) throws SQLException {
      String supp = "DELETE FROM `utilisateur` WHERE cin="+cin;
        Statement statm; 
        
        statm = cnx.createStatement();
        statm.executeUpdate(supp);
    }
    
    public void updateUtilisateurConnected(String login, String pwd) throws SQLException{
        String request= "UPDATE `utilisateur` SET `role`=1 WHERE login= '"+login+"' AND motDePasse='"+pwd+"' ";
         Utilisateur d = new Utilisateur();
        PreparedStatement pst = cnx.prepareStatement(request);

        pst.setInt(8, d.getRole());

        pst.executeUpdate();
        
    }
    
    public void updateUtilisateurDeconnecter() throws SQLException{
         String request= "UPDATE `utilisateur` SET `role`=0 ";
        Connection cnx = MyDB.getInstance().getConnection();
                   try {
                       PreparedStatement pst = cnx.prepareStatement(request);
                       pst.execute();
                   } catch (SQLException ex) {
                      
                   }
        
    }
    
}
