/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entity.Produit;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableView;
import utils.DataSource;

/**
 *
 * @author 21624
 */
public class ProduitService {

    public static void deleteInBatch(List<Produit> produit) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public static void deleteInBatch(TableView<Produit> afficher) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
      private Statement ste;
    private PreparedStatement pst;
    private Connection connection;
    private ResultSet rs;
    public ProduitService(){
        connection=DataSource.getInstance().getCnx();
    }
       public void ajouterProduit(Produit p){
String req="insert into produit (nom_prod,desc_prod,prix_prod,qte_prod,image_prod,valid_prod,categorie_id) values ('"+p.getNom_prod()+"','"+p.getDesc_prod()+"','"+p.getPrix_prod()+"',"
        + "'"+p.getQte_prod()+"','"+p.getImage_prod()+"','"+p.getValid_prod()+"','"+p.getCategorie_id()+"')";
        try {
            ste=connection.createStatement();
            ste.executeUpdate(req);
           System.out.println("Produit ajouter!");
        } catch (SQLException ex) {
            Logger.getLogger(ProduitService.class.getName()).log(Level.SEVERE, null, ex);
        }
}
          public ObservableList<Produit> readAll(){
        String req= "select * from produit";
       ObservableList<Produit> list=FXCollections.observableArrayList();
        try {
            ste=connection.createStatement();
            rs=ste.executeQuery(req);
            while(rs.next()) {
           list.add(new Produit(rs.getString("nom_prod"), rs.getString("desc_prod"), rs.getInt("prix_prod"),rs.getInt("qte_prod"), rs.getString("image_prod"),rs.getInt("valid_prod"),rs.getInt("categorie_id")));
            
            }
                
        } catch (SQLException ex) {
            Logger.getLogger(ProduitService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
       
    }
             public void supprimer(String nom_prod) {
        try {
            String requete = "DELETE FROM produit WHERE nom_prod='"+nom_prod+"'";
            PreparedStatement pst = connection.prepareStatement(requete);
            pst.executeUpdate();
            System.out.println("Produit supprimer !");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }
        public void modifier(Produit p,int id) {
        try {
            String requete = "UPDATE produit SET nom_prod=? WHERE id=? ";
            PreparedStatement pst = connection.prepareStatement(requete);
            pst.setInt(2,id);
            pst.setString(1, p.getNom_prod());
           


            pst.executeUpdate();
            System.out.println("catgorie modifier !");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }
    public ObservableList<Produit> recherche(String rech ) {
        ObservableList<Produit> list = FXCollections.observableArrayList();

        try {
            String requete = "SELECT * FROM produit where nom_prod LIKE '%"+rech+"%'";
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery(requete);
            while (rs.next()) {
                list.add(new Produit (rs.getString(2),rs.getString(3), rs.getInt(4),rs.getInt(5),rs.getString(6),rs.getInt(7),rs.getInt(8)));
            }

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }

        return list;
    } 

    public void modifier(String nom_prod) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
   
}
