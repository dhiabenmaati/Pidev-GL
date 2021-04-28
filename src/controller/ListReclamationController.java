/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import com.sun.org.apache.xerces.internal.impl.dv.xs.ListDV;
import entity.reclamation;
import entity.reponse_reclamation;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
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
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import service.reclamationService;
import service.reponse_reclamationService;

/**
 * FXML Controller class
 *
 * @author houssem
 */
public class ListReclamationController implements Initializable {

    @FXML
    private AnchorPane reclamation;
    private TableColumn<reclamation, Integer> id;
    @FXML
    private TableColumn<reclamation, String> desc_rec;
    @FXML
    private TableColumn<reclamation, String> status_rec;
    @FXML
    private TableColumn<reclamation, Date> progress_at;
    @FXML
    private TableColumn<reclamation, Date> valid_at;
    @FXML
    private TableColumn<reclamation, Date> reclmation_at;
    @FXML
    private TableColumn<reclamation, String> type;
    @FXML
    private TableColumn<reclamation, Integer> user_id;
    @FXML
    private TableView<reclamation> reclamation_table;
    @FXML
    private Button Reclamation;
    @FXML
    private Button Reponse_Reclamations;
    @FXML
    private Button Publication;
    @FXML
    private Button valider;
    @FXML
    private Button supprimer;
    @FXML
    private Button progress;
    @FXML
    private TextArea textreponse;
    @FXML
    private Button repondre;
    @FXML
    private Button VoirReponse;
    @FXML
    private Button modifier;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
                reclamationService rService=new reclamationService();
                reponse_reclamationService rServiceReponse=new reponse_reclamationService();
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
VoirReponse.setOnAction(event -> {

            try {
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("/view/ListReponseReclamationBackOffice.fxml"));
                Parent page1 = loader.load();
                ListReponseReclamationBackOfficeController controllercCommentaireController = loader.getController();
                controllercCommentaireController.intData(String.valueOf(rService.readAll()
                .get(reclamation_table.getSelectionModel().getSelectedIndex())
                .getId()));
                Scene scene = new Scene(page1);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
            } catch (IOException ex) {
                Logger.getLogger(AccueilController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }); 

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
/////////////////////////////////////////////
valider.setOnAction(event -> {                        
rService.ValidReclamtion(String.valueOf(rService.readAll()
                .get(reclamation_table.getSelectionModel().getSelectedIndex())
                .getId() ));
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information");
        alert.setHeaderText(null);
        alert.setContentText("valider");
        alert.show();        
            });
///////////////////////////////////////////////
progress.setOnAction(event -> {                        
rService.ProgressReclamtion(String.valueOf(rService.readAll()
                .get(reclamation_table.getSelectionModel().getSelectedIndex())
                .getId() ));
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information");
        alert.setHeaderText(null);
        alert.setContentText("progress");
        alert.show();        
            });
//////////////////////////////////
supprimer.setOnAction(event -> {                        
rService.supprimerReclamtion(String.valueOf(rService.readAll()
                .get(reclamation_table.getSelectionModel().getSelectedIndex())
                .getId() ));
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information");
        alert.setHeaderText(null);
        alert.setContentText("supprimer");
        alert.show();        
            });
//////////////////////////////////////////////////////////////////
repondre.setOnAction(event -> {  
    reponse_reclamation r =new reponse_reclamation(rService.readAll()
                .get(reclamation_table.getSelectionModel().getSelectedIndex())
                .getId(), textreponse.getText());
    rService.ValidReclamtion(String.valueOf(rService.readAll()
                .get(reclamation_table.getSelectionModel().getSelectedIndex())
                .getId() ));
    rServiceReponse.ajouterReponse_reclamation(r);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information");
        alert.setHeaderText(null);
        alert.setContentText("done");
        alert.show();        
            });
/////////////////////////////////////////////////////////////////
        ObservableList<reclamation> reclamationList = FXCollections.observableArrayList();
        List<reclamation> reclamations = new ArrayList(rService.readAll());
        for(reclamation r : reclamations)
            reclamationList.add(r);
        
        desc_rec.setCellValueFactory(new PropertyValueFactory<reclamation, String>("desc_rec"));
        status_rec.setCellValueFactory(new PropertyValueFactory<reclamation, String>("status_rec"));
        progress_at.setCellValueFactory(new PropertyValueFactory<reclamation, Date>("progress_at"));
        valid_at.setCellValueFactory(new PropertyValueFactory<reclamation, Date>("valid_at"));
        reclmation_at.setCellValueFactory(new PropertyValueFactory<reclamation, Date>("reclamation_at"));
        type.setCellValueFactory(new PropertyValueFactory<reclamation, String>("type"));
        user_id.setCellValueFactory(new PropertyValueFactory<reclamation, Integer>("user_id"));
        reclamation_table.setItems(reclamationList);  
    

    
    
    
    
    
    }    
               

}
