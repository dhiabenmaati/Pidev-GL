/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

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
public class reponse_reclamationService {
     private Statement ste;
    private PreparedStatement pst;
    private Connection connection;
    private ResultSet rs;

    public reponse_reclamationService() {
        connection=DataSource.getInstance().getCnx();  
    }
    public void ajouterReponse_reclamation(reponse_reclamation r){
        String req="INSERT INTO reponse_reclamation(desc_reponse_rec) VALUES ('"+r.getDesc_reponse_rec()+"')";
        try {
            ste=connection.createStatement();
            ste.executeUpdate(req);
        } catch (SQLException ex) {
            Logger.getLogger(reclamationService.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    public List<reponse_reclamation> readAll(){
        String req="select * from reponse_reclamation";
        List<reponse_reclamation> list=new ArrayList<>();
        try {
            ste=connection.createStatement();
            rs=ste.executeQuery(req);
            while (rs.next()) {  
                list.add(new reponse_reclamation
        (rs.getInt("id_rec_id"),
                rs.getString("desc_reponse_rec")));
                
            }
        } catch (SQLException ex) {
            Logger.getLogger(reclamationService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
       public void supprimerReponse_reclamation (int id){
       String req="DELETE FROM `reponse_reclamation` WHERE id='"+id+"'";
        try {
            ste=connection.createStatement();
            ste.executeUpdate(req);
            System.err.println("Done");
        } catch (SQLException ex) {
            Logger.getLogger(reclamationService.class.getName()).log(Level.SEVERE, null, ex);
        }
   }
   public void updateReponse_reclamation(reponse_reclamation r, int id){
       String req = "UPDATE `reponse_reclamation` SET `desc_reponse_rec`='"+r.getDesc_reponse_rec()+"' WHERE '"+id+"'";
               try {
            ste=connection.createStatement();
            ste.executeUpdate(req);
            System.err.println("Done");
        } catch (SQLException ex) {
            Logger.getLogger(reclamationService.class.getName()).log(Level.SEVERE, null, ex);
        }
   }       

}
