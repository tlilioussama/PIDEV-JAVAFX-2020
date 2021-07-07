/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.covid19.entities;

/**
 *
 * @author Tlili Oussama
 */
public class FicheMalade {
    
    public int id_fiche;
    public String nom_malade;
    public String prenom_malade;
    public int age;
    public String cin;
    public String ville; 
    public int etat_analyse;
    public int idLabo ;

    public FicheMalade(String nom_malade, String prenom_malade, int age, String cin, String ville, int etat_analyse, int idLabo) {
        this.nom_malade = nom_malade;
        this.prenom_malade = prenom_malade;
        this.age = age;
        this.cin = cin;
        this.ville = ville;
        this.etat_analyse = etat_analyse;
        this.idLabo = idLabo;
    }

    public FicheMalade() {
    }

    public FicheMalade(String nom_malade, String prenom_malade, int age, String cin, String ville, int etat_analyse) {
        this.nom_malade = nom_malade;
        this.prenom_malade = prenom_malade;
        this.age = age;
        this.cin = cin;
        this.ville = ville;
        this.etat_analyse = etat_analyse;
    }

    
    
    
    public FicheMalade(int id_fiche, String nom_malade, String prenom_malade, int age, String cin, String ville, int etat_analyse) {
        this.id_fiche = id_fiche;
        this.nom_malade = nom_malade;
        this.prenom_malade = prenom_malade;
        this.age = age;
        this.cin = cin;
        this.ville = ville;
        this.etat_analyse = etat_analyse;
    }
    
    

    public int getId_fiche() {
        return id_fiche;
    }

    public void setId_fiche(int id_fiche) {
        this.id_fiche = id_fiche;
    }

    public String getNom_malade() {
        return nom_malade;
    }

    public void setNom_malade(String nom_malade) {
        this.nom_malade = nom_malade;
    }

    public String getPrenom_malade() {
        return prenom_malade;
    }

    public void setPrenom_malade(String prenom_malade) {
        this.prenom_malade = prenom_malade;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getCin() {
        return cin;
    }

    public void setCin(String cin) {
        this.cin = cin;
    }

    public String getVille() {
        return ville;
    }


    public void setVille(String ville) {
        this.ville = ville;
    }

    public int getEtat_analyse() {
        return etat_analyse;
    }

    public void setEtat_analyse(int etat_analyse) {
        this.etat_analyse = etat_analyse;
    }

    public int getIdLabo() {
        return idLabo;
    }

    public void setIdLabo(int idLabo) {
        this.idLabo = idLabo;
    }

    
    
    @Override
    public String toString() {
        return "FicheMalade{" + "id_fiche=" + id_fiche + ", nom_malade=" + nom_malade + ", prenom_malade=" + prenom_malade + ", age=" + age + ", cin=" + cin + ", ville=" + ville + ", etat_analyse=" + etat_analyse + '}';
    }
    
    
    
    
}
