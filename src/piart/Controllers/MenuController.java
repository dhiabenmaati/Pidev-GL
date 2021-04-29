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
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Amine
 */
public class MenuController implements Initializable {

    @FXML
    private Button btnProduits;
    @FXML
    private Button btnPanier;
    @FXML
    private Button btnCommandes;
    @FXML
    private Button btnMesAdresse;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void goToProduits(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/piart/gui/showProduit.fxml"));
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void goToMesCommandes(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/piart/gui/MesCommandes.fxml"));
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void goToPanier(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/piart/gui/panier.fxml"));
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void goToAdresse(ActionEvent event) throws IOException{
        Parent root = FXMLLoader.load(getClass().getResource("/piart/gui/adresse.fxml"));
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }


    @FXML
    private void goToBackOffice(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/piart/gui/AdminPannel.fxml"));
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }
    
}
