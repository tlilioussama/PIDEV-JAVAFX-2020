/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.covid19.service;

import java.sql.SQLException;
import java.util.ArrayList;
import tn.covid19.entities.FicheMalade;

/**
 *
 * @author Tlili Oussama
 */
public interface IserviceFicheMalade {
    
    public void addFicheMalade(FicheMalade f) throws SQLException;
    
    public ArrayList<FicheMalade> getFicheMalade() throws SQLException;
    
    public void updateFicheMalade(FicheMalade f,int id) throws SQLException;
    
    public void deleteFicheMalade(FicheMalade f) throws SQLException;
    
    
    public FicheMalade getFichebyCIN(String cin) throws SQLException;
    
}
