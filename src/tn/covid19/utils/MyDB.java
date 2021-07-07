/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.covid19.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author jerbi
 */
public class MyDB {
    String url = "jdbc:mysql://127.0.0.1:3306/covid";
    String login = "root";
    String password="";
    
    Connection cnx;
    
    static MyDB instance;
    
    private MyDB(){
        try {
            cnx = DriverManager.getConnection(url, login, password);
            System.out.println("connection etablie");
        } catch (SQLException ex) {
            System.out.println("Erreur connection:"+ex.getMessage());
        }
        
       
    }
    
    public static MyDB getInstance(){
        if(instance == null)
            instance = new MyDB();
        return instance;
    }
    
    public Connection getConnection(){
        return cnx;
    }
    
    
}
