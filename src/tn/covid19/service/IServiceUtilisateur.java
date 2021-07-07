package tn.covid19.service;

import java.sql.SQLException;
import java.util.ArrayList;
import tn.covid19.entities.Utilisateur;

public interface IServiceUtilisateur {
    
    public Utilisateur ajouterUtilisateur(Utilisateur utilisateur) throws SQLException;
     
    public ArrayList<Utilisateur> getUtilisateurList() throws SQLException ;
     
    public Utilisateur getUtilisateurByRole (int role)throws SQLException;
    
    public void updateUtilisateurDeconnecter() throws SQLException;
     
    public Utilisateur getUtilisateurByCIN (String cin) throws SQLException;
     
    public  ArrayList<Utilisateur> getUtilisateur() throws SQLException;
    
    public void updateUtilisateur(Utilisateur d) throws SQLException;
    
    public void deleteUtilisateur(String cin) throws SQLException;
    
    public void updateUtilisateurConnected(String login, String pwd) throws SQLException;
    
    public Utilisateur getUtilisateurByLognAndPWD (String login, String pwd)throws SQLException;
    
}
