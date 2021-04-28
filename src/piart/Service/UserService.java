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
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import piart.Entities.Users;
import piart.Utils.ConnectionBD;

/**
 *
 * @author Amine
 */
public class UserService {
    private Statement stmt;
    private PreparedStatement pst;
    private Connection conn;
    private ResultSet res;
    
    public UserService() {
        conn = ConnectionBD.getInstance().getCnx();
    }
    
    public void add_user(Users user) {
        String sql = "insert into user (email,password,name,surname, num_tel) values (?,?,?,?,?)";
        try {
            pst = conn.prepareStatement(sql);
            pst.setString(1, user.getEmail());
            pst.setString(2, user.getPassword());
            pst.setString(3, user.getName());
            pst.setString(4, user.getSurname());
            pst.setInt(5, user.getNum_tel());
            pst.execute();
            JOptionPane.showMessageDialog(null, "Users Add success");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }
    
    public void edit_user(Users user) {
        try {
            int value1 = user.getNum_tel();
            String value2 = user.getEmail();
            String value3 = user.getPassword();
            String value4 = user.getName();
            String value5 = user.getSurname();
            int id = user.getId();
            String sql;
            if(!value3.isEmpty()) 
                sql = "update user set num_tel= "+value1+",email= '"+value2+"',password= '"+value3+"',name= '"+value4+"',surname= '"+value5+"' where id="+id;
            else  
                sql = "update user set num_tel= "+value1+",email= '"+value2+"',name= '"+value4+"',surname= '"+value5+"' where id="+id;
            pst= conn.prepareStatement(sql);
            pst.execute();
            JOptionPane.showMessageDialog(null, "Updated");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }
    
    public void delete_user(Users user) {
        String sql = "delete from user where id = ?";
        try {
            pst = conn.prepareStatement(sql);
            pst.setInt(1, user.getId());
            pst.execute();
            JOptionPane.showMessageDialog(null, "Delete");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }
    
    public void update_stripe_id(Users user, String stripeID) {
        try {
            int id = user.getId();
            String sql = "update user set stripe_id = '" + stripeID + "' where id=" + id;
            pst= conn.prepareStatement(sql);
            pst.execute();
            System.out.println("Stripe id updated");
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    
    public Users getUserByID(int id) {
        String req = "SELECT * FROM user WHERE id = " + id;
        try {
            stmt = conn.createStatement();
            res = stmt.executeQuery(req);
            if(res.next()) {
                        Users user = new Users(res.getInt("id"), res.getInt("num_tel"), res.getString("email"), res.getString("password"), res.getString("name"), res.getString("surname"), res.getString("stripe_id"));;
                return user;
            }
        } catch (SQLException ex) {
            Logger.getLogger(CommandeService.class.getName()).log(Level.SEVERE, null, ex);
        }    
        return null;
    }


}
