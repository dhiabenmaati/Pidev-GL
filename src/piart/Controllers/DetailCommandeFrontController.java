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
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.paint.Color;
import piart.Entities.Commande;
import piart.Entities.DetailCommande;
import piart.Service.CommandeService;

/**
 * FXML Controller class
 *
 * @author Amine
 */
public class DetailCommandeFrontController implements Initializable {

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
    Commande cmd;
    @FXML
    private Label lbStatus;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    public void initData(Commande c) {
        cmd = c;
        showDetailCommande();
    }
    
    public void showDetailCommande() {
        CommandeService cs = new CommandeService();
        ObservableList<DetailCommande> DetailCommandes = FXCollections.observableArrayList();
        List<DetailCommande> dcs = new ArrayList(cs.getDetailCommande(cmd.getId()));
        for(DetailCommande dc : dcs)
            DetailCommandes.add(dc);
        colPrix.setCellValueFactory(new PropertyValueFactory<DetailCommande, Double>("prix"));
        colTotale.setCellValueFactory(new PropertyValueFactory<DetailCommande, Double>("totale"));
        colProd.setCellValueFactory(new PropertyValueFactory<DetailCommande, String>("nom_prod"));
        colQte.setCellValueFactory(new PropertyValueFactory<DetailCommande, Integer>("qte"));
        lbTotale.setText(Double.toString(cs.getPrixTotal(cmd.getId())) + " DT");
        tvDetailCmd.setItems(DetailCommandes);
        
        if(cmd.getStatus() == 0) {
            lbStatus.setText("En Attent");
            lbStatus.setTextFill(Color.web("#FF5733"));
        } else if(cmd.getStatus() == 1) {
            lbStatus.setText("En Cours de livraison");
            lbStatus.setTextFill(Color.web("#FFC300"));
        } else {
            lbStatus.setText("Livrée");
            lbStatus.setTextFill(Color.web("#4FC12E"));
        }
    }
}
