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
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import piart.Entities.Adresse;
import piart.Service.AdresseService;

/**
 * FXML Controller class
 *
 * @author Amine
 */
public class AdresseController implements Initializable{

    @FXML
    private TextField tfAdresse;
    @FXML
    private TextField tfVille;
    @FXML
    private TextField tfCP;
    @FXML
    private TextField tfNum;
    @FXML
    private Button btnAdd;
    @FXML
    private Button btnEdit;
    @FXML
    private Button btnDelete;
    @FXML
    private Label lbId;
    @FXML
    private Label lbSuccess;
    @FXML
    private Label lbError;
    @FXML
    private AnchorPane goToMenu;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        showAdresse();
    }     

    @FXML
    private void goToMenu(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/piart/gui/menu.fxml"));
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void add(ActionEvent event) {
        if(tfAdresse.getText().isEmpty() || tfCP.getText().isEmpty() || tfNum.getText().isEmpty() || tfVille.getText().isEmpty()) {
            lbError.setText("Le champs adresse est obligatoire !");
        } else {
            AdresseService as = new AdresseService();
            Adresse adr = new Adresse(tfAdresse.getText(), Integer.parseInt(tfCP.getText()), tfVille.getText(), Integer.parseInt(tfCP.getText()), 11);
            as.ajouterAdresse(adr);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Adresse Ajouté");
            alert.setHeaderText("Succées");
            alert.setContentText("Votre adresse a été ajoutée avec succées !");
            alert.showAndWait();
        }
    }

    @FXML
    private void edit(ActionEvent event) {
        AdresseService as = new AdresseService();
        Adresse adr = new Adresse(Integer.parseInt(lbId.getText()), tfAdresse.getText(), Integer.parseInt(tfCP.getText()), tfVille.getText(), Integer.parseInt(tfCP.getText()), 11); // USER ID
        as.modifierAdresse(adr);
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Adresse Ajouté");
            alert.setHeaderText("Succées");
            alert.setContentText("Votre adresse a été mdifiée avec succées !");
            alert.showAndWait();
    }

    @FXML
    private void delete(ActionEvent event) {
        AdresseService as = new AdresseService();
        Adresse adr = new Adresse(Integer.parseInt(lbId.getText()), tfAdresse.getText(), Integer.parseInt(tfCP.getText()), tfVille.getText(), Integer.parseInt(tfCP.getText()), 11); // USER ID
        as.supprimerAdresse(adr);
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Adresse Ajouté");
            alert.setHeaderText("Succées");
            alert.setContentText("Votre adresse a été supprimée avec succées !");
            alert.showAndWait();
    }
    

    private void showAdresse() {
        AdresseService as = new AdresseService();
        Adresse adr = as.getAdressebyUserID(11);
        if(adr == null) 
            return;
        lbId.setText(Integer.toString(adr.getId()));
        tfAdresse.setText(adr.getAdresse());
        tfCP.setText(Integer.toString(adr.getCodepostal()));
        tfNum.setText(Integer.toString(adr.getNum_tel()));
        tfVille.setText(adr.getVille());
    }


    @FXML
    private void goToAdresse(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/piart/gui/adresse.fxml"));
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
    private void goToMesCommandes(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/piart/gui/MesCommandes.fxml"));
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }
    
}
