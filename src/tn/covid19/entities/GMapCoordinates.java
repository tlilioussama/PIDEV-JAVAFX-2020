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
public class GMapCoordinates {
    
    public Long id;
    
    public Double latitude;
    
    public Double longitude;
    
    public String nomVille ;
    
    public int nbrs_des_anaylses;
    public int nbr_test_positif;
    
    
    
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public String getNomVille() {
        return nomVille;
    }

    public void setNomVille(String nomVille) {
        this.nomVille = nomVille;
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

    @Override
    public String toString() {
        return "GMapCoordinates{" + "id=" + id + ", latitude=" + latitude + ", longitude=" + longitude + ", nomVille=" + nomVille + ", nbrs_des_anaylses=" + nbrs_des_anaylses + ", nbr_test_positif=" + nbr_test_positif + '}';
    }

    public GMapCoordinates(Long id, Double latitude, Double longitude, String nomVille, int nbrs_des_anaylses, int nbr_test_positif) {
        this.id = id;
        this.latitude = latitude;
        this.longitude = longitude;
        this.nomVille = nomVille;
        this.nbrs_des_anaylses = nbrs_des_anaylses;
        this.nbr_test_positif = nbr_test_positif;
    }

    public GMapCoordinates() {
    }

    
    
    
}
