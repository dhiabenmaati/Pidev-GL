/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import entity.blog;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import entity.reclamation;
import entity.reponse_reclamation;
import java.io.IOException;
import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
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
public class ListPublicationBackOfficeController implements Initializable {

    @FXML
    private TableView<blog> publication_table;
    private TableColumn<blog, Integer> id;
    @FXML
    private TableColumn<blog, String> title;
    @FXML
    private TableColumn<blog, String> description;
    @FXML
    private TableColumn<blog, Date> date;
    @FXML
    private TableColumn<blog, String> image;
    @FXML
    private TableColumn<blog, Integer> valid;
    @FXML
    private TableColumn<blog, Integer> user_id;
    @FXML
    private Button valider;
    @FXML
    private Button supprimer;
    @FXML
    private Button VoirCommentaire;
    @FXML
    private Button Reclamation;
    @FXML
    private Button Reponse_Reclamations;
    @FXML
    private Button Publication;
    @FXML
    private Button Commentaire_Publication;



    
    public void initialize(URL url, ResourceBundle rb) {
                 blogService rService=new blogService();
        // TODO
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
/////////////////////////////////////////////////////////////////////////////////////
valider.setOnAction(event -> {                        
rService.ValiderBlog(String.valueOf(rService.readAll()
                .get(publication_table.getSelectionModel().getSelectedIndex())
                .getId() ));
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information");
        alert.setHeaderText(null);
        alert.setContentText("valider");
        alert.show();        
            });
///////////////////////////////////////////////////////////////////////////////////
supprimer.setOnAction(event -> {                        
rService.supprimerBlog(String.valueOf(rService.readAll()
                .get(publication_table.getSelectionModel().getSelectedIndex())
                .getId() ));
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information");
        alert.setHeaderText(null);
        alert.setContentText("supprimer");
        alert.show();        
            });
/////////////////////////////////////////////////////////////////////////////////
VoirCommentaire.setOnAction(event -> {

            try {
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("/view/ListCommentairePublicationBackOffice.fxml"));
                Parent page1 = loader.load();
                ListCommentairePublicationBackOfficeController controllercCommentaireController = loader.getController();
                controllercCommentaireController.intData(String.valueOf(rService.readAll()
                .get(publication_table.getSelectionModel().getSelectedIndex())
                .getId()));
                Scene scene = new Scene(page1);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
            } catch (IOException ex) {
                Logger.getLogger(AccueilController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }); 

///////////////////////////////////////////////////////////
        ObservableList<blog> blogList = FXCollections.observableArrayList();
        List<blog> blogs = new ArrayList(rService.readAll());
        

        for(blog r : blogs)
            blogList.add(r);
        title.setCellValueFactory(new PropertyValueFactory<blog, String>("title"));
        description.setCellValueFactory(new PropertyValueFactory<blog, String>("description"));
        date.setCellValueFactory(new PropertyValueFactory<blog, Date>("date"));
        image.setCellValueFactory(new PropertyValueFactory<blog, String>("image"));
        valid.setCellValueFactory(new PropertyValueFactory<blog, Integer>("valid"));
        user_id.setCellValueFactory(new PropertyValueFactory<blog, Integer>("user_id"));

        publication_table.setItems(blogList);
    }    
    
}
