/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author 21624
 */
public class DataSource {
           private String url="jdbc:mysql://127.0.0.1/piart";
    private String login="root";
    private String pwd="";
    private Connection cnx;
    private PreparedStatement pst;
    private ResultSet rs;
    private  static DataSource instance;
     private DataSource(){
        try {  
            cnx=DriverManager.getConnection(url, login, pwd);
            System.out.println("connexion");
        } catch (SQLException ex) {
            Logger.getLogger(DataSource.class.getName()).log(Level.SEVERE, null, ex);
        }
                }
    public static DataSource getInstance(){
        if (instance==null)
            instance=new DataSource();
        return instance;
    }
      public Connection getCnx(){
        return cnx;
    }
}
