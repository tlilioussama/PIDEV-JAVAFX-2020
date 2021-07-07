/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.covid19.entities;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Tlili Oussama
 */
public class Laboratoire {
    
    
    public int id_labo;
    public String nom_labo;
    public int nbrs_des_anaylses;
    public int nbr_test_positif;

    List<FicheMalade> coordonées_malade =new ArrayList<>();

    public Laboratoire() {
    }

    public Laboratoire(String nom_labo, int nbrs_des_anaylses, int nbr_test_positif) {
        this.nom_labo = nom_labo;
        this.nbrs_des_anaylses = nbrs_des_anaylses;
        this.nbr_test_positif = nbr_test_positif;
    }

    
    public Laboratoire(int id_labo, String nom_labo, int nbrs_des_anaylses, int nbr_test_positif) {
        this.id_labo = id_labo;
        this.nom_labo = nom_labo;
        this.nbrs_des_anaylses = nbrs_des_anaylses;
        this.nbr_test_positif = nbr_test_positif;
    }

    public Laboratoire(int id_labo, String nom_labo, int nbrs_des_anaylses) {
        this.id_labo = id_labo;
        this.nom_labo = nom_labo;
        this.nbrs_des_anaylses = nbrs_des_anaylses;
    }

    
    
    

    
    
    public int getId_labo() {
        return id_labo;
    }

    public void setId_labo(int id_labo) {
        this.id_labo = id_labo;
    }

    public String getNom_labo() {
        return nom_labo;
    }

    public void setNom_labo(String nom_labo) {
        this.nom_labo = nom_labo;
    }

    public int getNbrs_des_anaylses() {
        return nbrs_des_anaylses;
    }

    public void setNbrs_des_anaylses(int nbrs_des_anaylses) {
        this.nbrs_des_anaylses = nbrs_des_anaylses;
    }

    

    public int getNbr_test_positif() {
        return nbr_test_positif;
    }

    public void setNbr_test_positif(int nbr_test_positif) {
        this.nbr_test_positif = nbr_test_positif;
    }

    public List<FicheMalade> getCoordonées_malade() {
        return coordonées_malade;
    }

    @Override
    public String toString() {
        return "Laboratoire{" + "id_labo=" + id_labo + ", nom_labo=" + nom_labo + ", etat_analyse=" + nbrs_des_anaylses + ", nbr_test_positif=" + nbr_test_positif + ", coordon\u00e9es_malade=" + coordonées_malade + '}';
    }

    public void setCoordonées_malade(List<FicheMalade> coordonées_malade) {
        this.coordonées_malade = coordonées_malade;
    }
    
    
    
    
   
    
}
