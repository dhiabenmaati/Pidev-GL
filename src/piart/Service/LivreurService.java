/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package piart.Service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import piart.Entities.Users;
import piart.Utils.ConnectionBD;

/**
 *
 * @author Amine
 */
public class LivreurService {
    private Statement stmt;
    private PreparedStatement pstmt;
    private Connection cnx;
    private ResultSet res;
    
    public LivreurService() {
        cnx = ConnectionBD.getInstance().getCnx();
    }
    
    public List<Users> SearchLivreurByName(String val) {
        String req = "SELECT * FROM user WHERE name LIKE'%"+val+"%' OR surname LIKE'%"+val+"%' OR CONCAT(CONCAT(surname, ' '), name) LIKE '%"+val+"%' OR CONCAT(CONCAT(name, ' '), surname) LIKE '%"+val+"%'";
                
        List<Users> list = new ArrayList<>();
        try {
            stmt = cnx.createStatement();
            res = stmt.executeQuery(req);
            while(res.next()) {
                list.add(new Users(res.getInt("id"), res.getInt("num_tel"), res.getString("email"), res.getString("name"), res.getString("surname")));
            }
        } catch (SQLException ex) { 
            Logger.getLogger(CommandeService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list; 
    }
    
    
    public List<Users> getAllLivreurs() {
        String req = "SELECT * FROM user";
        List<Users> list = new ArrayList<>();
        try {
            stmt = cnx.createStatement();
            res = stmt.executeQuery(req);
            while(res.next()) {
                list.add(new Users(res.getInt("id"), res.getInt("num_tel"), res.getString("email"), res.getString("name"), res.getString("surname")));
            }
        } catch (SQLException ex) { 
            Logger.getLogger(CommandeService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
    
}
