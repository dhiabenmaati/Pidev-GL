/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import entity.comment_blog;
import entity.reclamation;
import entity.reponse_reclamation;
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
public class comment_blogService {
     private Statement ste;
    private PreparedStatement pst;
    private Connection connection;
    private ResultSet rs;

    public comment_blogService() {
        connection=DataSource.getInstance().getCnx();  
    }
    public void ajouterComment_blog(comment_blog b){
        String req="INSERT INTO comment_blog(id_blog_id,comment,date,user_id) VALUES "
                + "('"+b.getId_blog_id()+"','"+b.getComment()+"','"+b.getDate()+"','"+b.getUser_id()+"')";
        try {
            ste=connection.createStatement();
            ste.executeUpdate(req);
        } catch (SQLException ex) {
            Logger.getLogger(reclamationService.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
   public List<comment_blog> readAll(){
        String req="select * from comment_blog";
        List<comment_blog> list=new ArrayList<>();
        try {
            ste=connection.createStatement();
            rs=ste.executeQuery(req);
            while (rs.next()) {  
                list.add(new comment_blog(rs.getString("comment")));
                
            }
        } catch (SQLException ex) {
            Logger.getLogger(reclamationService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
    
    
    public void supprimerComment_blog(int id){
       String req="DELETE FROM `comment_blog` WHERE id='"+id+"'";
        try {
            ste=connection.createStatement();
            ste.executeUpdate(req);
            System.err.println("Done");
        } catch (SQLException ex) {
            Logger.getLogger(reclamationService.class.getName()).log(Level.SEVERE, null, ex);
        }
   }
       public void updatecomment_blog(comment_blog r, int id){
       String req = "UPDATE `comment_blog` SET `comment`='"+r.getComment()+"' WHERE '"+id+"'";
               try {
            ste=connection.createStatement();
            ste.executeUpdate(req);
            System.err.println("Done");
        } catch (SQLException ex) {
            Logger.getLogger(reclamationService.class.getName()).log(Level.SEVERE, null, ex);
        }
   }
}
