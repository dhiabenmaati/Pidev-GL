/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;
import entity.reclamation;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import utils.DataSource;

/**
 *
 * @author houssem
 */
public class reclamationService {
    private Statement ste;
    private PreparedStatement pst;
    private Connection connection;
    private ResultSet rs;

    public reclamationService() {
        connection=DataSource.getInstance().getCnx();  
    }
    public void ajouterReclamation(reclamation r){
        String req="INSERT INTO reclamation(desc_rec,type) VALUES ('"+r.getDesc_rec()+"','"+r.getType()+"')";
        try {
            ste=connection.createStatement();
            ste.executeUpdate(req);
        } catch (SQLException ex) {
            Logger.getLogger(reclamationService.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    public List<reclamation> readAll(){
        String req="select * from reclamation";
        List<reclamation> list=new ArrayList<>();
        try {
            ste=connection.createStatement();
            rs=ste.executeQuery(req);
            while (rs.next()) {  
                list.add(new reclamation(rs.getInt("id") , rs.getString("desc_rec") , rs.getString("status_rec") , 
                        rs.getDate("progress_at"), rs.getDate("valid_at") , rs.getDate("reclamation_at") , 
                        rs.getString("type"),rs.getInt("user_id")));
                
            }
        } catch (SQLException ex) {
            Logger.getLogger(reclamationService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
   public void supprimerReclamtion (int id){
       String req="DELETE FROM `reclamation` WHERE id='"+id+"'";
        try {
            ste=connection.createStatement();
            ste.executeUpdate(req);
            System.err.println("Done");
        } catch (SQLException ex) {
            Logger.getLogger(reclamationService.class.getName()).log(Level.SEVERE, null, ex);
        }
   }
   public void updateReclamation(reclamation r, int id){
       String req = "UPDATE `pidev`.`reclamation` SET `desc_rec`='"+r.getDesc_rec()+"',`status_rec`='"+r.getStatus_rec()+"',`type`='"+r.getType()+"','WHERE `id`='"+id+"'";
               try {
            ste=connection.createStatement();
            ste.executeUpdate(req);
            System.err.println("Done");
        } catch (SQLException ex) {
            Logger.getLogger(reclamationService.class.getName()).log(Level.SEVERE, null, ex);
        }
   }
   
    
    
}
