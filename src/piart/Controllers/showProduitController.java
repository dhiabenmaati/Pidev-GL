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
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import piart.Entities.Produit;
import piart.Service.PanierService;
import piart.Service.ProduitService;

/**
 *
 * @author Amine
 */
public class showProduitController {

    @FXML
    private Label label;
    @FXML
    private TableView<Produit> tvProduits;
    @FXML
    private TableColumn<Produit, Integer> colId;
    @FXML
    private TableColumn<Produit, String> colNom;
    @FXML
    private TableColumn<Produit, String> colDesc;
    @FXML
    private TableColumn<Produit, Double> colPrix;
    @FXML
    private Button btnMenu;
    @FXML
    private Button btnPanier;
    @FXML
    private Label lbPanier;
    
    public void initialize() {
        showProduits();
    }

    @FXML
    private void goToMenu(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/piart/gui/menu.fxml"));
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }
    
    public void showProduits() {
        ProduitService ps = new ProduitService();
        ObservableList<Produit> produitsList = FXCollections.observableArrayList();
        List<Produit> prods = new ArrayList(ps.getAllProduits());
        for(Produit p : prods)
            produitsList.add(p);
        
        colId.setCellValueFactory(new PropertyValueFactory<Produit, Integer>("id"));
        colNom.setCellValueFactory(new PropertyValueFactory<Produit, String>("nom_prod"));
        colDesc.setCellValueFactory(new PropertyValueFactory<Produit, String>("desc_prod"));
        colPrix.setCellValueFactory(new PropertyValueFactory<Produit, Double>("prix_prod"));
        tvProduits.setItems(produitsList);
    }

    @FXML
    private void handelMouseAction(MouseEvent event) {
        Produit p = tvProduits.getSelectionModel().getSelectedItem();
        PanierService pans = new PanierService();
        pans.add(p);
        lbPanier.setText("Le produit " + p.getNom_prod() + " est ajouté au panier !");
    }

    @FXML
    private void goToPanier(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/piart/gui/panier.fxml"));
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }
    
}
