/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package piart.Controllers;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.paint.Color;
import piart.Entities.Adresse;
import piart.Entities.Commande;
import piart.Entities.DetailCommande;
import piart.Entities.Users;
import piart.Service.AdresseService;
import piart.Service.CommandeService;
import piart.Service.UserService;

/**
 * FXML Controller class
 *
 * @author Amine
 */
public class DetailCommandeLivController implements Initializable {

    @FXML
    private TableView<DetailCommande> tvDetailCmd;
    @FXML
    private TableColumn<DetailCommande, String> colProd;
    @FXML
    private TableColumn<DetailCommande, Double> colPrix;
    @FXML
    private TableColumn<DetailCommande, Integer> colQte;
    @FXML
    private TableColumn<DetailCommande, Double> colTotale;
    @FXML
    private Label lbTotale;
    @FXML
    private Label lbStatus;
    Commande cmd;
    @FXML
    private Label lbNom;
    @FXML
    private Label lbPrenom;
    @FXML
    private Label lbAdresse;
    @FXML
    private Label lbVille;
    @FXML
    private Label lbTel;
    @FXML
    private Label lbCP;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }

    public void initData(Commande c) {
        cmd = c;
        showDetailCommande();
        showInfo();
        status();
    }

    public void showDetailCommande() {
        CommandeService cs = new CommandeService();
        ObservableList<DetailCommande> DetailCommandes = FXCollections.observableArrayList();
        List<DetailCommande> dcs = new ArrayList(cs.getDetailCommande(cmd.getId()));
        for (DetailCommande dc : dcs) {
            DetailCommandes.add(dc);
        }
        colPrix.setCellValueFactory(new PropertyValueFactory<DetailCommande, Double>("prix"));
        colTotale.setCellValueFactory(new PropertyValueFactory<DetailCommande, Double>("totale"));
        colProd.setCellValueFactory(new PropertyValueFactory<DetailCommande, String>("nom_prod"));
        colQte.setCellValueFactory(new PropertyValueFactory<DetailCommande, Integer>("qte"));
        lbTotale.setText(Double.toString(cs.getPrixTotal(cmd.getId())) + " DT");
        tvDetailCmd.setItems(DetailCommandes);
    }
    
    public void showInfo() {
        UserService us = new UserService();
        AdresseService as = new AdresseService();
        Users user = us.getUserByID(cmd.getLivreur_id()); // USER ID
        lbNom.setText(user.getName());
        lbPrenom.setText(user.getSurname());
        System.out.println(cmd.getUser_id());
        Adresse adr = as.getAdressebyUserID(cmd.getLivreur_id());// USER ID
        if(adr == null) return ;
        lbAdresse.setText(adr.getAdresse());
        lbCP.setText(Integer.toString(adr.getCodepostal()));
        lbVille.setText(adr.getVille());
        lbTel.setText(Integer.toString(adr.getNum_tel()));
    }

    @FXML
    private void updateStatus(ActionEvent event) {
        CommandeService cs = new CommandeService();
        cs.updateStatus(2, cmd.getId());
        cmd.setStatus(2);
        status();
    }
    
    public void status() {
        if (cmd.getStatus() == 0) {
            lbStatus.setText("En Attent");
            lbStatus.setTextFill(Color.web("#FF5733"));
        } else if (cmd.getStatus() == 1) {
            lbStatus.setText("En Cours de livraison");
            lbStatus.setTextFill(Color.web("#FFC300"));
        } else {
            lbStatus.setText("Livrée");
            lbStatus.setTextFill(Color.web("#4FC12E"));
        }
    }
    
}
