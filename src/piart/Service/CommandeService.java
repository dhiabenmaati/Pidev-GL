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
import piart.Utils.ConnectionBD;
import piart.Entities.Commande;
import piart.Entities.Panier;

/**
 *
 * @author Amine
 */
public class CommandeService {
    private Statement stmt;
    private PreparedStatement pstmt;
    private Connection cnx;
    private ResultSet res;
    
    public CommandeService() {
        cnx = ConnectionBD.getInstance().getCnx();
    }
    
    public void ajouterCommande(Commande c) {
        String req = "INSERT INTO Commande(user_id, date_creer, status) VALUES (?, ?, ?)";
        try {
            pstmt = cnx.prepareStatement(req);
            pstmt.setInt(1, c. getUser_id());
            pstmt.setDate(2, c.getData_creer());
            pstmt.setInt(3, c.getStatus());
            pstmt.executeUpdate();
        } catch (SQLException ex) {

            Logger.getLogger(ProduitService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public List<Commande> getAllCommandes() {
        String req = "SELECT * FROM Commande";
        List<Commande> list = new ArrayList<>();
        try {
            stmt = cnx.createStatement();
            res = stmt.executeQuery(req);
            while(res.next()) {
                list.add(new Commande(res.getInt("id"), res.getInt("user_id"), res.getInt("livreur_id"), res.getDate("date_creer"), res.getDate("date_expedirer"), res.getInt("status")));
            }
        } catch (SQLException ex) { 
            Logger.getLogger(CommandeService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
    
    public Commande getLastCommande() {
        String req = "SELECT * FROM commande ORDER BY id DESC LIMIT 1";
        try {
            stmt = cnx.createStatement();
            res = stmt.executeQuery(req);
            if(res.next()) {
                Commande c = new Commande(res.getInt("id"), res.getInt("user_id"), res.getInt("livreur_id"), res.getDate("date_creer"), res.getDate("date_expedirer"), res.getInt("status"));
                return c;
            }
        } catch (SQLException ex) {
            Logger.getLogger(CommandeService.class.getName()).log(Level.SEVERE, null, ex);
        }    
        return null;
    }
    
    public void changerStatus(Commande c) {
        
    }
}
