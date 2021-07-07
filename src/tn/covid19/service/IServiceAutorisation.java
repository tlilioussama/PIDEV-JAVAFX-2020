/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.covid19.service;

import java.sql.SQLException;
import java.util.ArrayList;
import tn.covid19.entities.Autorisation;

/**
 *
 * @author jerbi
 */
public interface IServiceAutorisation {
    
    public void AjouterAutorisation(Autorisation a) throws SQLException;
    public ArrayList<Autorisation> rechercherAutorisationparCin(String cin) throws SQLException;
    public void ModifierAutorisation(Autorisation a) throws SQLException;
    public void deleteAutorisation(String i) throws SQLException;

}
