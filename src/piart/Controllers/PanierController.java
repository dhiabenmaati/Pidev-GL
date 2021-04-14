/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package piart.Controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import piart.Entities.Panier;
import piart.Service.PanierService;

/**
 * FXML Controller class
 *
 * @author Amine
 */
public class PanierController {

    @FXML
    private Label lbTotale;
    @FXML
    private TableView<Panier> tvPanier;
    @FXML
    private TableColumn<Panier, String> colNom;
    @FXML
    private TableColumn<Panier, Double> colPrix;
    @FXML
    private TableColumn<Panier, Integer> colQte;
    @FXML
    private TableColumn<Panier, Double> colTotal;
    @FXML
    private Button btnCommander;
    
    public void initialize() {
        showPanier();
    }
    
    @FXML
    private void goToProduit(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/piart/gui/showProduit.fxml"));
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }
    
    @FXML
    private void Commander(ActionEvent event) {
    }
    
    public void showPanier() {
        PanierService ps = new PanierService();
        ObservableList<Panier> panierItems = FXCollections.observableArrayList();
        List<Panier> items = new ArrayList(ps.getPanierItems());
        for(Panier item : items)
            panierItems.add(item);
        colNom.setCellValueFactory(new PropertyValueFactory<Panier, String>("nom"));
        colPrix.setCellValueFactory(new PropertyValueFactory<Panier, Double>("prix"));
        colQte.setCellValueFactory(new PropertyValueFactory<Panier, Integer>("qte"));
        colTotal.setCellValueFactory(new PropertyValueFactory<Panier, Double>("totale"));
        tvPanier.setItems(panierItems);
    }
    
}
