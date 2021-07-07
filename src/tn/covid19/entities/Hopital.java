
package tn.covid19.entities;

public class Hopital {
    private int id_Hop;
    private String nom_Hop;
    private String adresse_Hop;
    private int nbr_lit;

   // List<FichePatient> coordonées_malade =new ArrayList<>();

    public Hopital() {
    }

    public Hopital(String nom_Hop, String adresse_Hop, int nbr_lit) {
        this.nom_Hop = nom_Hop;
        this.adresse_Hop = adresse_Hop;
        this.nbr_lit = nbr_lit;
    }

    public Hopital(int id_Hop, String nom_Hop, String adresse_Hop, int nbr_lit) {
        this.id_Hop = id_Hop;
        this.nom_Hop = nom_Hop;
        this.adresse_Hop = adresse_Hop;
        this.nbr_lit = nbr_lit;
    }

    public int getId_Hop() {
        return id_Hop;
    }

    public void setId_Hop(int id_Hop) {
        this.id_Hop = id_Hop;
    }

    public String getNom_Hop() {
        return nom_Hop;
    }

    public void setNom_Hop(String nom_Hop) {
        this.nom_Hop = nom_Hop;
    }

    public String getAdresse_Hop() {
        return adresse_Hop;
    }

    public void setAdresse_Hop(String adresse_Hop) {
        this.adresse_Hop = adresse_Hop;
    }

    public int getNbr_lit() {
        return nbr_lit;
    }

    public void setNbr_lit(int nbr_lit) {
        this.nbr_lit = nbr_lit;
    }

    @Override
    public String toString() {
        return "Hopital{" + "id_Hop=" + id_Hop + ", nom_Hop=" + nom_Hop + ", adresse_Hop=" + adresse_Hop + ", nbr_lit=" + nbr_lit + '}';
    }

  
}

    

    

