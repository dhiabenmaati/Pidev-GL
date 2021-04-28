/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import entity.reclamation;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import service.reclamationService;

/**
 * FXML Controller class
 *
 * @author houssem
 */
public class AddReclamationController implements Initializable {
    

    @FXML
    private Button add;
    @FXML
    private TextArea desc_rec;
    @FXML
    private TextField type;
    @FXML
    private Button blog;
    @FXML
    private Button reclamation;
    @FXML
    private ChoiceBox<String> choiceboxtype;




    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
            ObservableList<String> availableChoices = FXCollections.observableArrayList("Produit", "Vendeur","Livarison", "Enchere", "Autres"); 
            choiceboxtype.setItems(availableChoices);
                    blog.setOnAction(event -> {

            try {
                Parent page1 = FXMLLoader.load(getClass().getResource("/view/AccueilBlog.fxml"));
                Scene scene = new Scene(page1);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
            } catch (IOException ex) {
                Logger.getLogger(AccueilController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
    reclamation.setOnAction(event -> {

            try {
                Parent page1 = FXMLLoader.load(getClass().getResource("/view/AddReclamation.fxml"));
                Scene scene = new Scene(page1);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
            } catch (IOException ex) {
                Logger.getLogger(AccueilController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }); 
        // TODO
long millisecond=System.currentTimeMillis();  
java.sql.Timestamp date=new java.sql.Timestamp(millisecond);  

                add.setOnAction(event -> {
            
            reclamation p = new reclamation(desc_rec.getText(), date, choiceboxtype.getSelectionModel().getSelectedItem(), 5);
            reclamationService rService=new reclamationService();
            rService.ajouterReclamation(p);
            Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Information");
        alert.setHeaderText(null);
        alert.setContentText("reclamaton envoye");
        alert.show();
        desc_rec.setText("");  
            });
    }    
    
}
