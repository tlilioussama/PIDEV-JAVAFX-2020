/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.covid19.service;

import java.sql.SQLException;
import java.util.ArrayList;
import tn.covid19.entities.Laboratoire;

/**
 *
 * @author Tlili Oussama
 */
public interface IserviceLaboratoire {
    
    
    public void addLaboratoire(Laboratoire lo) throws SQLException;
    
    public ArrayList<Laboratoire> getLaboratoires() throws SQLException;
    
    public Laboratoire getLabobyId(int id)  throws SQLException;
        
    public void updateLaboratoire(Laboratoire f,int id) throws SQLException;
    
    public void deleteLaabo(Laboratoire l ) throws SQLException;
    
    public Laboratoire getLabobyNom(String nom) throws SQLException ;
    
}
