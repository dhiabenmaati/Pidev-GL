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
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import piart.Entities.Commande;
import piart.Entities.DetailCommande;
import piart.Entities.Users;
import piart.Service.CommandeService;
import piart.Service.LivreurService;
/**
 * FXML Controller class
 *
 * @author Amine
 */
public class DetailCommandeController implements Initializable {

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
    private ChoiceBox<String> cbLivreur;
    @FXML
    private Label lbMsg;
    Commande cmd;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //showDetailCommande();
        initLivreur();
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
    }
    
    public void initLivreur() {
        LivreurService ls = new LivreurService();
        List<Users> livreurs = ls.getAllLivreurs();
        cbLivreur.getItems().add("Selection un livreur");
        cbLivreur.setValue("Selection un livreur");
        for(Users livreur : livreurs) {
            cbLivreur.getItems().add(livreur.getId() + " - " + livreur.getName() + " " + livreur.getName());
        }
    }
    
    @FXML
     void affecterLivreur(ActionEvent event) {
        CommandeService cs = new CommandeService();
        String livreur = cbLivreur.getValue();
        int livreur_id = Integer.parseInt(livreur.substring(0, livreur.indexOf("-") - 1));
        cs.affecterCommande(livreur_id, cmd.getId());
        showDetailCommande();
    }
     
}
