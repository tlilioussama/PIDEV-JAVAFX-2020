/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.covid19.entities;

import java.time.LocalDate;

/**
 *
 * @author jerbi
 */
public class Autorisation {
    
    private Utilisateur utilisateur;

   

    public Utilisateur getUtilisateur(int id) {
       
        return utilisateur;
    }

    public void setUtilisateur(Utilisateur utilisateur) {
        this.utilisateur = utilisateur;
    }


    private Integer idAutorisation;
    private LocalDate dateAutorisation;
    private LocalDate finAutorisation;
    private String motifsAutorisation;

    public Autorisation() {
    }
    
    
    public Autorisation(Integer idAtt, LocalDate dateAtt, LocalDate finAtt, String motifs) {
       
        this.idAutorisation = idAtt;
        this.dateAutorisation = dateAtt;
        this.finAutorisation = finAtt;
        this.motifsAutorisation = motifs;
    }

    public Autorisation(LocalDate dateAutorisation, LocalDate finAutorisation, String motifsAutorisation) {
        this.dateAutorisation = dateAutorisation;
        this.finAutorisation = finAutorisation;
        this.motifsAutorisation = motifsAutorisation;
    }
    

    

    public Integer getIdAutorisation() {
        return idAutorisation;
    }

    public void setInAutorisation(int idAutorisation) {
        this.idAutorisation = idAutorisation;
    }

    public LocalDate getDateAutorisation() {
        return dateAutorisation;
    }

    public void setDateAutorisation(LocalDate dateAutorisation) {
        this.dateAutorisation = dateAutorisation;
    }

    public LocalDate getFinAutorisation() {
        return finAutorisation;
    }

    public void setFinAutorisation(LocalDate finAutorisation) {
        this.finAutorisation = finAutorisation;
    }

    public String getMotifsAutorisation() {
        return motifsAutorisation;
    }

    public void setMotifsAutorisation(String motifsAutorisation) {
        this.motifsAutorisation = motifsAutorisation;
    }

    @Override
    public String toString() {
        return (super.toString() + "Attestation{" + "idAtt=" + idAutorisation + ", dateAtt=" + dateAutorisation + ", finAtt=" + finAutorisation + ", motifs=" + motifsAutorisation + '}');
    }

   }

   
    
    