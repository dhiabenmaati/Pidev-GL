/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package piart.Service;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.control.Alert;
import javax.swing.JOptionPane;
import piart.Utils.ConnectionBD;
import piart.Entities.Commande;
import piart.Entities.DetailCommande;
import piart.Entities.Panier;
import piart.Entities.Produit;

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
            pstmt.setInt(1, c.getUser_id());
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
            while (res.next()) {
                list.add(new Commande(res.getInt("id"), res.getInt("user_id"), res.getInt("livreur_id"), res.getDate("date_creer"), res.getDate("date_expedirer"), res.getInt("status")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(CommandeService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    public List<Commande> getCommandeByUserID(int id) {
        String req = "SELECT * FROM Commande WHERE user_id = " + id;
        List<Commande> list = new ArrayList<>();
        try {
            stmt = cnx.createStatement();
            res = stmt.executeQuery(req);
            while (res.next()) {
                list.add(new Commande(res.getInt("id"), res.getInt("user_id"), res.getInt("livreur_id"), res.getDate("date_creer"), res.getDate("date_expedirer"), res.getInt("status")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(CommandeService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    public List<Commande> getCommandeByLivreurID(int id) {
        String req = "SELECT * FROM Commande WHERE livreur_id = " + id + " AND status = 1";
        List<Commande> list = new ArrayList<>();
        try {
            stmt = cnx.createStatement();
            res = stmt.executeQuery(req);
            while (res.next()) {
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
            if (res.next()) {
                Commande c = new Commande(res.getInt("id"), res.getInt("user_id"), res.getInt("livreur_id"), res.getDate("date_creer"), res.getDate("date_expedirer"), res.getInt("status"));
                return c;
            }
        } catch (SQLException ex) {
            Logger.getLogger(CommandeService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public void ajouterDetailCommade() {
        Commande lastc = getLastCommande();
        System.out.println(" id c : " + lastc.getId());
        PanierService ps = new PanierService();
        List<Panier> items = ps.getPanierItems();
        System.out.println(items.size());
        for (Panier item : items) {
            System.out.println(item.toString());
            String req = "INSERT INTO detail_commande(commande_id, produit_id, qte) VALUES (?, ?, ?)";
            try {
                pstmt = cnx.prepareStatement(req);
                pstmt.setInt(1, lastc.getId());
                pstmt.setInt(2, item.getID_Prod());
                pstmt.setInt(3, item.getQte());
                pstmt.executeUpdate();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e);
            }
        }
        JOptionPane.showMessageDialog(null, "Votre commande a été placée !");
    }

    public List<Commande> getCommandeByStatus(int status) {
        List<Commande> list = new ArrayList<>();
        String req = "SELECT * FROM commande WHERE status=" + status;
        try {
            stmt = cnx.createStatement();
            res = stmt.executeQuery(req);
            while (res.next()) {
                Commande c = new Commande(res.getInt("id"), res.getInt("user_id"), res.getInt("livreur_id"), res.getDate("date_creer"), res.getDate("date_expedirer"), res.getInt("status"));
                list.add(c);
            }
        } catch (SQLException ex) {
            Logger.getLogger(CommandeService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    public List<Commande> getCommandeByDate(LocalDate datedeb, LocalDate datefin) {
        String dd = new String();
        String df = new String();
        if (datedeb != null) {
            dd = datedeb.toString();
        }
        if (datefin != null) {
            df = datefin.toString();
        }
        System.out.println(dd + " " + df);
        List<Commande> list = new ArrayList<>();
        String req = new String();
        if (!dd.isEmpty() && df.isEmpty()) {
            req = "SELECT * FROM commande WHERE date_creer>='" + dd + "'";
        } else if (dd.isEmpty() && !df.isEmpty()) {
            req = "SELECT * FROM commande WHERE date_creer<='" + df + "'";
        } else if (!dd.isEmpty() && !df.isEmpty()) {
            req = "SELECT * FROM commande WHERE date_creer>='" + dd + "' AND date_creer<='" + df + "'";
        } else if (dd.isEmpty() && df.isEmpty()) {
            req = "SELECT * FROM commande";
        }
        System.out.println(req);
        try {
            stmt = cnx.createStatement();
            res = stmt.executeQuery(req);
            while (res.next()) {
                Commande c = new Commande(res.getInt("id"), res.getInt("user_id"), res.getInt("livreur_id"), res.getDate("date_creer"), res.getDate("date_expedirer"), res.getInt("status"));
                list.add(c);
            }
        } catch (SQLException ex) {
            Logger.getLogger(CommandeService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    public List<DetailCommande> getDetailCommande(int id) {
        String req = "SELECT * FROM detail_commande WHERE commande_id = " + id;
        List<DetailCommande> list = new ArrayList<>();
        ProduitService ps = new ProduitService();
        try {
            stmt = cnx.createStatement();
            res = stmt.executeQuery(req);
            while (res.next()) {
                Produit p = ps.getProduitByID(res.getInt("produit_id"));
                list.add(new DetailCommande(p.getNom_prod(), p.getPrix_prod(), res.getInt("qte")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(CommandeService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    public Double getPrixTotal(int id) {
        Double total = 0.0;
        String req = "SELECT SUM(qte * prix_prod) FROM produit p, detail_commande d WHERE p.id = d.produit_id AND d.commande_id = " + id;
        try {
            stmt = cnx.createStatement();
            res = stmt.executeQuery(req);
            res.next();
            total = res.getDouble(1);
        } catch (Exception e) {
            System.out.println(e);
        }
        return total;
    }

    public void affecterCommande(int livreur_id, int id_cmd) {
        String req = "UPDATE commande SET livreur_id = " + livreur_id + ", status = 1 WHERE id = " + id_cmd;
        try {
            stmt = cnx.createStatement();
            stmt.executeUpdate(req);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Affectation de livreur");
            alert.setHeaderText("Affectation de livreur");
            alert.setContentText("Le livreur a été affecté avec succés à cette commande");
            alert.showAndWait();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    public void updateStatus(int status, int id) {
        String req = "UPDATE Commande SET status = ?, date_expedirer = ? WHERE id = " + id;
        long millis = System.currentTimeMillis();
        java.sql.Date date = new java.sql.Date(millis);
        try {
            pstmt = cnx.prepareStatement(req);
            pstmt.setInt(1, status);
            pstmt.setDate(2, date);
            pstmt.executeUpdate();
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Etat de livraison");
            alert.setHeaderText("Etat de livraison");
            alert.setContentText("La commande est livrée !");
            alert.showAndWait();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

}
