/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entity.Categorie;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import static jdk.nashorn.internal.runtime.Debug.id;

/**
 *
 * @author 21624
 */
public class CategorieService {
     private Statement ste;
   private PreparedStatement pst;
   private Connection connection;
   private ResultSet rs;
   public CategorieService(){
       connection=utils.DataSource.getInstance().getCnx();
   }
   public void ajouterCategorie(Categorie c){
    String req="insert into categorie(nom_cat,desc_cat,confirmation) values ('"+c.getNom_cat()+"','"+c.getDesc_cat()+"','"+c.getConfirmation()+"')";
       try {
           ste=connection.createStatement();
           ste.executeUpdate(req);
       } catch (SQLException ex) {
           Logger.getLogger(CategorieService.class.getName()).log(Level.SEVERE, null, ex);
       } 
       
   }
    public ObservableList<Categorie> readAll(){
        String req= "select * from categorie";
       ObservableList<Categorie> list=FXCollections.observableArrayList();
        try {
            ste=connection.createStatement();
            rs=ste.executeQuery(req);
            while(rs.next()) {
                list.add(new Categorie(rs.getInt("id"), rs.getString("nom_cat"), rs.getString("desc_cat"), rs.getString("confirmation")));
            
            }
                
        } catch (SQLException ex) {
            Logger.getLogger(ProduitService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
   
    }
        public void supprimer(String nom_cat) {
        try {
            String requete = "DELETE FROM categorie WHERE nom_cat='"+nom_cat+"'";
            PreparedStatement pst = connection.prepareStatement(requete);
           
            pst.executeUpdate();
            System.out.println("Categorie supprimer !");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }
    public void modifier(Categorie c,int id) {
        try {
            String requete = "UPDATE categorie SET nom_cat=? WHERE id=? ";
            PreparedStatement pst = connection.prepareStatement(requete);
            pst.setInt(2,id);
            pst.setString(1, c.getNom_cat());
           


            pst.executeUpdate();
            System.out.println("catgorie modifier !");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }
   public void activer(String nom_cat) {
        try {
            String requete = "UPDATE categorie SET confirmation='active' WHERE nom_cat=? ";
            PreparedStatement pst = connection.prepareStatement(requete);
            pst.setString(1,nom_cat);
            
            
           
         
            pst.executeUpdate();
            System.out.println("categorie activé avec succé !");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }
        
         
          public void desactiver(String nom_cat) {
        try {
            String requete = "UPDATE categorie SET confirmation='desactive' WHERE nom_cat=? ";
            PreparedStatement pst = connection.prepareStatement(requete);
            pst.setString(1, nom_cat);
            
            
           
         
            pst.executeUpdate();
            System.out.println("categorie desactivé avec succé !");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }
}

    
