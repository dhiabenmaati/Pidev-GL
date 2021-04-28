/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import entity.blog;
import entity.reclamation;
import entity.reponse_reclamation;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
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
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import service.blogService;
import service.reclamationService;
import service.reponse_reclamationService;

/**
 * FXML Controller class
 *
 * @author houssem
 */
public class ListReponseReclamationBackOfficeController implements Initializable {

    private TableColumn<reponse_reclamation, Integer> id;
    @FXML
    private Button Reclamation;
    @FXML
    private Button Reponse_Reclamations;
    @FXML
    private Button Publication;
    @FXML
    private Button Commentaire_Publication;
    @FXML
    private TableView<reponse_reclamation> reponse_reclamation_table;
    @FXML
    private TableColumn<reponse_reclamation, Integer> id_rec_id;
    @FXML
    private TableColumn<reponse_reclamation, String> desc_reponse_rec;
    @FXML
    private TextArea rp;
    @FXML
    private Button supprimer;
    @FXML
    private Button modifier;
    
    public void intData(String id){
                            reponse_reclamationService rService =new reponse_reclamationService();
                ObservableList<reponse_reclamation>  reponse_reclamationlList = FXCollections.observableArrayList();
        List<reponse_reclamation>  reponse_reclamations = new ArrayList(rService.readAllParId(id));
        

        for(reponse_reclamation r : reponse_reclamations)
            reponse_reclamationlList.add(r);
        
        desc_reponse_rec.setCellValueFactory(new PropertyValueFactory<reponse_reclamation, String>("desc_reponse_rec"));
        reponse_reclamation_table.setItems(reponse_reclamationlList);
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
                    reponse_reclamationService rService =new reponse_reclamationService();
                    reponse_reclamation_table.setOnMouseClicked(event->{
        rp.setText(String.valueOf(rService.readAll()
                .get(reponse_reclamation_table.getSelectionModel().getSelectedIndex())
                .getDesc_reponse_rec()));   
                    });

                //////////////////////////////                
         Reclamation.setOnAction(event -> {

            try {
                Parent page1 = FXMLLoader.load(getClass().getResource("/view/ListReclamation.fxml"));
                Scene scene = new Scene(page1);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
            } catch (IOException ex) {
                Logger.getLogger(AccueilController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });     
///////////////////////////////         
////////////////////////         
////////////////////////         
         Publication.setOnAction(event -> {

            try {
                Parent page1 = FXMLLoader.load(getClass().getResource("/view/ListPublicationBackOffice.fxml"));
                Scene scene = new Scene(page1);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
            } catch (IOException ex) {
                Logger.getLogger(AccueilController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
supprimer.setOnAction(event -> {                        
            rService.supprimerReponse_reclamation(String.valueOf(rService.readAll()
                .get(reponse_reclamation_table.getSelectionModel().getSelectedIndex())
                .getId()));
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information");
        alert.setHeaderText(null);
        alert.setContentText("supprimer");
        alert.show();
        rp.setText("");
        
  
            });
modifier.setOnAction(event -> {                        
rService.updateReponse_reclamation(rp.getText(),String.valueOf(rService.readAll()
                .get(reponse_reclamation_table.getSelectionModel().getSelectedIndex())
                .getId() ));
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information");
        alert.setHeaderText(null);
        alert.setContentText("modifier");
        alert.show();
        rp.setText("");
        
  
            });


 /////////////////////////////////
 

    }    
    
}
