/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import entity.reclamation;
import entity.reponse_reclamation;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.Time;
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
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import service.reclamationService;
import service.reponse_reclamationService;

/**
 * FXML Controller class
 *
 * @author houssem
 */
public class ListeNotificationsController implements Initializable {

    @FXML
    private Label status_rec;
    @FXML
    private Label progress_at;
    @FXML
    private Label valid_at;
    @FXML
    private Text desc_reponse_rec;
    @FXML
    private TableView<reclamation> reclamation_table1;
    @FXML
    private TableColumn<reclamation, String> type;
    @FXML
    private TableColumn<reclamation, String> desc_rec;
    @FXML
    private TableColumn<reclamation, Time> reclamation_at;
    @FXML
    private Label test;
    
    private String repp;
    @FXML
    private Button blog;
    @FXML
    private Button reclamation;
    @FXML
    private Button notification;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        notification.setOnAction(event -> {

            try {
                Parent page1 = FXMLLoader.load(getClass().getResource("/view/ListeNotifications.fxml"));
                Scene scene = new Scene(page1);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
            } catch (IOException ex) {
                Logger.getLogger(AccueilController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
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
    
                reponse_reclamationService rService1=new reponse_reclamationService();
        ObservableList<reponse_reclamation> reponse_reclamationmList = FXCollections.observableArrayList();
        List<reponse_reclamation> reponse_reclamations = new ArrayList(rService1.readAll());
        
        reclamationService rService=new reclamationService();
        ObservableList<reclamation> reclamationList = FXCollections.observableArrayList();
        List<reclamation> reclamations = new ArrayList(rService.readAllNotification());
        for(reclamation r : reclamations)
            reclamationList.add(r);
        desc_rec.setCellValueFactory(new PropertyValueFactory<reclamation, String>("desc_rec"));
        type.setCellValueFactory(new PropertyValueFactory<reclamation, String>("type"));
        reclamation_at.setCellValueFactory(new PropertyValueFactory<reclamation, Time>("reclamation_at"));
        reclamation_table1.setItems(reclamationList); 
        
        reclamation_table1.setOnMouseClicked(event->{
        status_rec.setText(String.valueOf(rService.readAll()
                .get(reclamation_table1.getSelectionModel().getSelectedIndex())
                .getStatus_rec()));
        progress_at.setText(String.valueOf(rService.readAll()
                .get(reclamation_table1.getSelectionModel().getSelectedIndex())
                .getProgress_at()));
        valid_at.setText(String.valueOf(rService.readAll()
                .get(reclamation_table1.getSelectionModel().getSelectedIndex())
                .getValid_at()));
        List<reponse_reclamation> repList =new ArrayList(rService1.readAllParId(String.valueOf(rService.readAllNotification().get(reclamation_table1.getSelectionModel().getSelectedIndex()).getId())));        
            if (repList.size()==0) {
               repp="";
            }
        for(reponse_reclamation rep : repList){
            repp = rep.getDesc_reponse_rec();
        }
        desc_reponse_rec.setText(repp);
    });
        

        
        
    }    
    
}
