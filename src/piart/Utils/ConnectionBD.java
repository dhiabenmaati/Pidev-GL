/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package piart.Utils;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Amine
 */
public class ConnectionBD {
    private String url = "jdbc:mysql://127.0.0.1/piart";
    private String login = "root";
    private String pwd = "";
    
    private Connection cnx;
    
    private static ConnectionBD instance;
    
    private ConnectionBD() {
        try {
            cnx = DriverManager.getConnection(url, login, pwd);
            System.out.println("Connected successfully");
        } catch (SQLException ex) {
            Logger.getLogger(ConnectionBD.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static ConnectionBD getInstance() {
        if(instance == null)
            instance = new ConnectionBD();
        return instance;
    }
    
    public Connection getCnx() {
        return cnx;
    }
}
