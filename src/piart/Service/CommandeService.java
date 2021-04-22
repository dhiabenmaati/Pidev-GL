/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package piart.Service;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
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
    
    public void ajouterDetailCommade() {
        Commande lastc = getLastCommande();
        System.out.println(" id c : " + lastc.getId());
        PanierService ps = new PanierService();
        List<Panier> items = ps.getPanierItems();
        System.out.println(items.size());
        for(Panier item : items) {
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
        String req = "SELECT * FROM commande WHERE status="+status;
        try {
            stmt = cnx.createStatement();
            res = stmt.executeQuery(req);
            while(res.next()) {
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
        if(datedeb != null) 
            dd = datedeb.toString();
        if(datefin != null) 
            df = datefin.toString();
        System.out.println(dd+" " +df);
        List<Commande> list = new ArrayList<>();
        String req = new String();
        if(!dd.isEmpty() && df.isEmpty())
            req = "SELECT * FROM commande WHERE date_creer>='"+dd+"'";
        else if(dd.isEmpty() && !df.isEmpty())
            req = "SELECT * FROM commande WHERE date_creer<='"+df+"'";
        else if(!dd.isEmpty() && !df.isEmpty())
            req = "SELECT * FROM commande WHERE date_creer>='"+dd+"' AND date_creer<='"+df+"'";
        else if(dd.isEmpty() && df.isEmpty())
            req = "SELECT * FROM commande";
        System.out.println(req);
        try {
            stmt = cnx.createStatement();
            res = stmt.executeQuery(req);
            while(res.next()) {
                Commande c = new Commande(res.getInt("id"), res.getInt("user_id"), res.getInt("livreur_id"), res.getDate("date_creer"), res.getDate("date_expedirer"), res.getInt("status"));
                list.add(c);
            }
        } catch (SQLException ex) {
            Logger.getLogger(CommandeService.class.getName()).log(Level.SEVERE, null, ex);
        }    
        return list;
    }
    
}
