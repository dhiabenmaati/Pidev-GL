/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package piart.Controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
import piart.Entities.Commande;
import piart.Entities.DetailCommande;
import piart.Entities.Produit;

/**
 * FXML Controller class
 *
 * @author Amine
 */
public class DetailCommandeController implements Initializable {

    @FXML
    private Button btnBack;
    @FXML
    private TableView<DetailCommande> tvDetailCmd;
    @FXML
    private TableColumn<DetailCommande, String> colProd;
    @FXML
    private TableColumn<DetailCommande, Double> colPrix;
    @FXML
    private TableColumn<DetailCommande, Integer> colQte;
    @FXML
    private TableColumn<DetailCommande, Integer> colTotale;
    @FXML
    private Label lbPrixTotale;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void goToBack(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/piart/gui/showProduit.fxml"));
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }
    
}
