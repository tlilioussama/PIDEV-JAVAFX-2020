
package tn.covid19.entities;


import java.time.LocalDate;

public class Malade {
    
    
    
    private int id_malade;
    private String Nom_Malade;
    private String Prenom_malade;
    private String adresse_malade;
    private LocalDate date_hospital;
    private LocalDate date_fin_hospital;

    public Malade() {
    }

    public Malade(String Nom_Malade, String Prenom_malade, String adresse_malade, LocalDate date_hospital, LocalDate date_fin_hospital) {
        this.Nom_Malade = Nom_Malade;
        this.Prenom_malade = Prenom_malade;
        this.adresse_malade = adresse_malade;
        this.date_hospital = date_hospital;
        this.date_fin_hospital = date_fin_hospital;
    }

    public Malade(int id_malade, String Nom_Malade, String Prenom_malade, String adresse_malade, LocalDate date_hospital, LocalDate date_fin_hospital) {
        this.id_malade = id_malade;
        this.Nom_Malade = Nom_Malade;
        this.Prenom_malade = Prenom_malade;
        this.adresse_malade = adresse_malade;
        this.date_hospital = date_hospital;
        this.date_fin_hospital = date_fin_hospital;
    }

    public int getId_malade() {
        return id_malade;
    }

    public void setId_malade(int id_malade) {
        this.id_malade = id_malade;
    }

    public String getNom_Malade() {
        return Nom_Malade;
    }

    public void setNom_Malade(String Nom_Malade) {
        this.Nom_Malade = Nom_Malade;
    }

    public String getPrenom_malade() {
        return Prenom_malade;
    }

    public void setPrenom_malade(String Prenom_malade) {
        this.Prenom_malade = Prenom_malade;
    }

    public String getAdresse_malade() {
        return adresse_malade;
    }

    public void setAdresse_malade(String adresse_malade) {
        this.adresse_malade = adresse_malade;
    }

    public LocalDate getDate_hospital() {
        return date_hospital;
    }

    public void setDate_hospital(LocalDate date_hospital) {
        this.date_hospital = date_hospital;
    }

    public LocalDate getDate_fin_hospital() {
        return date_fin_hospital;
    }

    public void setDate_fin_hospital(LocalDate date_fin_hospital) {
        this.date_fin_hospital = date_fin_hospital;
    }

    @Override
    public String toString() {
        return "Malade{" + "id_malade=" + id_malade + ", Nom_Malade=" + Nom_Malade + ", Prenom_malade=" + Prenom_malade + ", adresse_malade=" + adresse_malade + ", date_hospital=" + date_hospital + ", date_fin_hospital=" + date_fin_hospital + '}';
    }

    
  
    
    
    
    
}

