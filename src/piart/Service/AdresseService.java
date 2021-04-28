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
import piart.Entities.Adresse;
import piart.Utils.ConnectionBD;

/**
 *
 * @author Amine
 */
public class AdresseService {
    private Statement stmt;
    private PreparedStatement pstmt;
    private Connection cnx;
    private ResultSet res;
    
    public AdresseService() {
        cnx = ConnectionBD.getInstance().getCnx();
    }
    
    public void ajouterAdresse(Adresse adr) {
        String req="INSERT INTO adresse(user_id, adresse, codepostal, ville, numtel) VALUES (?, ?, ?, ?, ?)";
        try {
            pstmt = cnx.prepareStatement(req);
            pstmt.setInt(1, adr.getUser_id());
            pstmt.setString(2, adr.getAdresse());
            pstmt.setInt(3, adr.getCodepostal());
            pstmt.setString(4, adr.getVille());
            pstmt.setInt(5, adr.getNum_tel());
            pstmt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ProduitService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void modifierAdresse(Adresse adr) {
        String req="UPDATE adresse SET adresse = ?, codepostal = ?, ville = ?, numtel = ? WHERE id = ?";
        try {
            pstmt = cnx.prepareStatement(req);
            pstmt.setString(1, adr.getAdresse());
            pstmt.setInt(2, adr.getCodepostal());
            pstmt.setString(3, adr.getVille());
            pstmt.setInt(4, adr.getNum_tel());
            pstmt.setInt(5, adr.getId());
            pstmt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ProduitService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public Adresse getAdressebyUserID(int id) {
        String req = "SELECT * FROM adresse WHERE user_id = " + id + "";
        try {
            stmt = cnx.createStatement();
            res = stmt.executeQuery(req);
            if(res.next()) {
                Adresse adr = new Adresse(res.getInt("id"), res.getString("adresse"), res.getInt("codepostal"), res.getString("ville"), res.getInt("numtel"), res.getInt("user_id"));
                return adr;
            }
        } catch (SQLException ex) {
            Logger.getLogger(CommandeService.class.getName()).log(Level.SEVERE, null, ex);
        }    
        return null;
    }
    
    public void supprimerAdresse(Adresse adr) {
        String req = "DELETE FROM adresse WHERE id = " + adr.getId();
        try {
            stmt = cnx.createStatement();
            stmt.executeUpdate(req);
        } catch (SQLException ex) {
            Logger.getLogger(CommandeService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public List<Adresse> getAllAdresses() {
        String req = "SELECT * FROM adresse";
        List<Adresse> list = new ArrayList<>();
        try {
            stmt = cnx.createStatement();
            res = stmt.executeQuery(req);
            while(res.next()) {
                list.add(new Adresse(res.getInt("id"), res.getString("adresse"), res.getInt("codepostal"), res.getString("ville"), res.getInt("numtel"), res.getInt("user_id")));
            }
        } catch (SQLException ex) { 
            Logger.getLogger(CommandeService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    
}
