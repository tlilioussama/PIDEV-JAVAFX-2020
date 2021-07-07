/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.covid19;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import tn.covid19.entities.Autorisation;
import tn.covid19.entities.Utilisateur;
import tn.covid19.service.impl.ServiceAutorisation;
import tn.covid19.service.impl.ServiceUtilisateur;
import tn.covid19.utils.Session;
/**
 *
 * @author jerbi
 */




public class Covid19 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        //Utilisateur u = new Utilisateur("Test", "Test", "01234567", "ariana", "ariana", 97000111, "test@gmail.com", 20, 1, "rochdi", "rchdi");
        //ServiceUtilisateur su = new ServiceUtilisateur();
        
               // ServiceUtilisateur su1 = new ServiceUtilisateur();
                //Utilisateur userConnected1 = new Utilisateur();
               // Utilisateur userConnected2 = new Utilisateur();
               
                //String login1 = "med";
               // String pwd1 = "med";
               //String cin = "6767";
                 // Utilisateur user = new Utilisateur();
//                 ServiceUtilisateur su = new ServiceUtilisateur();
//                 ArrayList<Utilisateur> list = new ArrayList<>();
//            
//        try {
//            Utilisateur u1 = su.getUtilisateurByLognAndPWD("rochdi", "rochdi");
//            System.out.println(u1);
            
            
            
            // System.out.println("Login : "+login1+"\nMot de passe : "+pwd1);
            
            /*try {
            //userConnected1 = su1.getUtilisateurByLognAndPWD(login1, pwd1);
            user = su1.getUtilisateurByCIN(cin);
            //System.out.println(userConnected1);
            System.out.println(user);
            } catch (SQLException ex) {
            System.out.println("***************************\nLogin ou Mot de passe incorrecte");
            ex.getStackTrace();
            }*/
            /* try {
            //su.ajouterUtilisateur(u);
            Utilisateur u1 = su.getUtilisateurByLognAndPWD("rochdi", "rochdi");
            System.out.println(u1);
            } catch (SQLException ex) {
            System.out.println("ERREUR"+ ex.getMessage());
            }*/
            
            
            /*  Session.onligneuser=new Utilisateur(100, "habib", "jerbi", "00202000", "araiana",
            "ariana", 98423248, "test@mail.com", 0, 0, "rochdi","rochdi");
            
            //java.util.Date myDate = new java.util.Date();
            //java.sql.Date sqlDate = new java.sql.Date(myDate.getTime());
            LocalDate dateatt = LocalDate.of(1985, 07, 25);
            LocalDate datefinatt = LocalDate.now();
            
            Attestation a1 = new Attestation(dateatt,datefinatt,"sortir faire des courses");
            ServiceAttestattion sa = new ServiceAttestattion();
            
            try {
            sa.AjouterAttestation(a1);
            } catch (SQLException ex) {
            
            System.out.println("erreure d'ajout");
            }*/
//        } catch (SQLException ex) {
//            Logger.getLogger(Covid19.class.getName()).log(Level.SEVERE, null, ex);
//        }
        ServiceAutorisation serviceAutorisation = new ServiceAutorisation();
          ServiceUtilisateur sv = new ServiceUtilisateur();
        Utilisateur u = new Utilisateur();
        try {
            u= sv.getUtilisateurByRole(1);
            //System.out.println(u);
        } catch (SQLException ex) {
           ex.getStackTrace();
        }
//        try {
//            sa.rechercherAutorisationparCin(u.getCin());
//        } catch (SQLException ex) {
//            Logger.getLogger(Covid19.class.getName()).log(Level.SEVERE, null, ex);
//        }
        String cin = "55555555";
    ArrayList <Autorisation> autorisationTAB = new ArrayList<>();
        try {
            autorisationTAB=serviceAutorisation.rechercherAutorisationparCin(cin);
        } catch (SQLException ex) {
            Logger.getLogger(Covid19.class.getName()).log(Level.SEVERE, null, ex);
        }
       // System.out.println("Liste 2  : \n"+ autorisationTAB ); 
}
}