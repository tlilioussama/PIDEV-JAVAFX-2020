/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.covid19.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import tn.covid19.entities.GMapCoordinates;

/**
 *
 * @author Tlili Oussama
 */
public interface IserviceGmap {
    
    public void addGmap(GMapCoordinates g) throws SQLException;
    
    public ArrayList<GMapCoordinates> getVille() throws SQLException;
    
    public List<GMapCoordinates> getAll();
    
    public void updateVille(GMapCoordinates ville,Long id) throws SQLException;
     
    public GMapCoordinates getGmapById(int id) throws SQLException;
     
    public GMapCoordinates getGmapByNom(String name) throws SQLException;
    
}
