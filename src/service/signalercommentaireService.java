/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import entity.blog;
import entity.comment_blog;
import entity.signalercommentaire;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import utils.DataSource;

/**
 *
 * @author houssem
 */
public class signalercommentaireService {
     private Statement ste;
    private PreparedStatement pst;
    private Connection connection;
    private ResultSet rs;

    public signalercommentaireService() {
        connection=DataSource.getInstance().getCnx();  
    }
    public void ajouterSignaler(signalercommentaire s){  
        String req="INSERT INTO `signalercommentaire`(`user_id`, `comment_blog_id`) VALUES "
                + "('"+s.getUser_id()+"','"+s.getComment_blog_id()+"')";
        try {
            ste=connection.createStatement();
            ste.executeUpdate(req);
        } catch (SQLException ex) {
            Logger.getLogger(reclamationService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public int verfierSignaler(signalercommentaire s){
                String req="select * from signalercommentaire where user_id='"+s.getUser_id()+"'AND comment_blog_id='"+s.getComment_blog_id()+"' ";
        List<signalercommentaire> list=new ArrayList<>();
        try {
            ste=connection.createStatement();
            rs=ste.executeQuery(req);
            while (rs.next()) {  
                list.add(new signalercommentaire(rs.getInt("user_id"),rs.getInt("comment_blog_id")));               
            }
        } catch (SQLException ex) {
            Logger.getLogger(reclamationService.class.getName()).log(Level.SEVERE, null, ex);
        }
        int lengString = list.size();
        return lengString;
    } 
        public int NBRSignaler(int id){
                String req="select * from signalercommentaire where comment_blog_id='"+id+"' ";
        List<signalercommentaire> list=new ArrayList<>();
        try {
            ste=connection.createStatement();
            rs=ste.executeQuery(req);
            while (rs.next()) {  
                list.add(new signalercommentaire(rs.getInt("user_id"),rs.getInt("comment_blog_id")));               
            }
        } catch (SQLException ex) {
            Logger.getLogger(reclamationService.class.getName()).log(Level.SEVERE, null, ex);
        }
        int lengString = list.size();
        return lengString;
    } 
        
    
}
