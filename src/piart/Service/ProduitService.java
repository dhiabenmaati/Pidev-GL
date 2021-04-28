/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package piart.Service;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import piart.Entities.Produit;
import piart.Utils.ConnectionBD;

/**
 *
 * @author Amine
 */
public class ProduitService {
    private Statement stmt;
    private PreparedStatement pstmt;
    private Connection cnx;
    private ResultSet res;
    
    public ProduitService() {
        cnx = ConnectionBD.getInstance().getCnx();
    }
    
    public void ajouterProduit(Produit p) {
        String req="INSERT INTO PRODUIT(nom_prod, desc_prod, prix_prod, qte_prod, image_prod, valid_prod) VALUES (?, ?, ?, ?, ?, ?)";
        try {
            pstmt = cnx.prepareStatement(req);
            pstmt.setString(1, p.getNom_prod());
            pstmt.setString(2, p.getDesc_prod());
            pstmt.setDouble(3, p.getPrix_prod());
            pstmt.setInt(4, p.getQte_prod());
            pstmt.setString(5, p.getImage_prod());
            pstmt.setBoolean(6, p.isValid_prod());
            pstmt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ProduitService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public List<Produit> getAllProduits() {
        String req = "SELECT * FROM produit";
        List<Produit> list = new ArrayList<>();
        try {
            stmt = cnx.createStatement();
            res = stmt.executeQuery(req);
            while(res.next()) {
                list.add(new Produit(res.getInt("id"), res.getString("nom_prod"), res.getString("desc_prod"), res.getDouble("prix_prod"), res.getInt("qte_prod"), res.getString("image_prod"), res.getBoolean("valid_prod")));
            }
        } catch (SQLException ex) { 
            Logger.getLogger(CommandeService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
    
    
    public Produit getProduitByID(int id) {
        String req = "SELECT * FROM produit WHERE id = "+id;
        try {
            stmt = cnx.createStatement();
            res = stmt.executeQuery(req);
            if(res.next()) {
                Produit p = new Produit(res.getInt("id"), res.getString("nom_prod"), res.getString("desc_prod"), res.getDouble("prix_prod"), res.getInt("qte_prod"), res.getString("image_prod"), res.getBoolean("valid_prod"));
                return p;
            }
        } catch (SQLException ex) {
            Logger.getLogger(CommandeService.class.getName()).log(Level.SEVERE, null, ex);
        }    
        return null;
    }

}
