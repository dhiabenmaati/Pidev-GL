/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import entity.blog;
import entity.reclamation;
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
public class blogService {
     private Statement ste;
    private PreparedStatement pst;
    private Connection connection;
    private ResultSet rs;

    public blogService() {
        connection=DataSource.getInstance().getCnx();  
    }
    public void ajouterBlog(blog b){
                long millisecond=System.currentTimeMillis();  
java.sql.Timestamp date=new java.sql.Timestamp(millisecond);  
        String req="INSERT INTO blog(title,description,date,image,updated_at,user_id) VALUES "
                + "('"+b.getTitle()+"','"+b.getDescription()+"','"+date+"','"+b.getImage()+"','"+date+"','"+b.getUser_id()+"')";
        try {
            ste=connection.createStatement();
            ste.executeUpdate(req);
        } catch (SQLException ex) {
            Logger.getLogger(reclamationService.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    public List<blog> readAll(){
        String req="select * from blog";
        List<blog> list=new ArrayList<>();
        try {
            ste=connection.createStatement();
            rs=ste.executeQuery(req);
            while (rs.next()) {  
                list.add(new blog(rs.getInt("id"),rs.getString("title"),rs.getString("description"),
                        rs.getDate("date"),rs.getString("image"),
                        rs.getDate("updated_at"),rs.getInt("valid"),rs.getInt("user_id")));
                
            }
        } catch (SQLException ex) {
            Logger.getLogger(reclamationService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
     public List<blog> readAllValid(){
        String req="select * from blog WHERE valid=1 ";
        List<blog> list=new ArrayList<>();
        try {
            ste=connection.createStatement();
            rs=ste.executeQuery(req);
            while (rs.next()) {  
                list.add(new blog(rs.getInt("id"),rs.getString("title"),rs.getString("description"),
                        rs.getDate("date"),rs.getString("image"),
                        rs.getDate("updated_at"),rs.getInt("valid"),rs.getInt("user_id")));
                
            }
        } catch (SQLException ex) {
            Logger.getLogger(reclamationService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
          public List<blog> readAllValidtitle(String tltString){
        String req="select * from blog WHERE title='"+tltString+"' ";
        List<blog> list=new ArrayList<>();
        try {
            ste=connection.createStatement();
            rs=ste.executeQuery(req);
            while (rs.next()) {  
                list.add(new blog(rs.getInt("id"),rs.getString("title"),rs.getString("description"),
                        rs.getDate("date"),rs.getString("image"),
                        rs.getDate("updated_at"),rs.getInt("valid"),rs.getInt("user_id")));
                
            }
        } catch (SQLException ex) {
            Logger.getLogger(reclamationService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
    public void supprimerBlog (String id){
       String req="DELETE FROM `blog` WHERE id='"+id+"'";
        try {
            ste=connection.createStatement();
            ste.executeUpdate(req);
        } catch (SQLException ex) {
            Logger.getLogger(reclamationService.class.getName()).log(Level.SEVERE, null, ex);
        }
   }
       public void ValiderBlog(String id){
                   String valid="1";
       String req = "UPDATE `pidev`.`blog` SET `valid`='"+valid+"' WHERE id='"+id+"'";
               try {
            ste=connection.createStatement();
            ste.executeUpdate(req);
        } catch (SQLException ex) {
            Logger.getLogger(reclamationService.class.getName()).log(Level.SEVERE, null, ex);
        }
   }

}
