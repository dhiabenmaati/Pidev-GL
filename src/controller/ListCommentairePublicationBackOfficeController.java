/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import entity.blog;
import entity.comment_blog;
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
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import service.blogService;
import service.comment_blogService;
import service.reponse_reclamationService;
import service.signalercommentaireService;

/**
 * FXML Controller class
 *
 * @author houssem
 */
public class ListCommentairePublicationBackOfficeController implements Initializable {


    private TableColumn<comment_blog, Integer> id;
    @FXML
    private TableColumn<comment_blog, Integer> id_blog_id;
    @FXML
    private TableColumn<comment_blog, String> comment;
    @FXML
    private TableColumn<comment_blog, Date> date;
    @FXML
    private TableColumn<comment_blog, Integer> user_id;
    @FXML
    private TableColumn<comment_blog, Integer> signaler;
    @FXML
    private TableView<comment_blog> Reponse_Reclamations_table;
    @FXML
    private Button Reclamation;
    @FXML
    private Button Reponse_Reclamations;
    @FXML
    private Button Publication;
    @FXML
    private Button Commentaire_Publication;
    @FXML
    private Button supprimer;
    private int i =0;

    public void intData(String id){
                                comment_blogService rService=new comment_blogService();
                signalercommentaireService rService1=new signalercommentaireService();
        ObservableList<comment_blog> comment_bloglList = FXCollections.observableArrayList();
        List<comment_blog> comment_bloglList1 = new ArrayList(rService.readAllId(id));
        

        for(comment_blog r : comment_bloglList1){
            i =rService1.NBRSignaler(r.getId());
            rService.updatesign(i, r.getId());
            comment_bloglList.add(r);
            
        }
        
        
        id_blog_id.setCellValueFactory(new PropertyValueFactory<comment_blog, Integer>("id_blog_id"));
        comment.setCellValueFactory(new PropertyValueFactory<comment_blog, String>("comment"));
        date.setCellValueFactory(new PropertyValueFactory<comment_blog, Date>("date"));
        user_id.setCellValueFactory(new PropertyValueFactory<comment_blog, Integer>("user_id"));
        signaler.setCellValueFactory(new PropertyValueFactory<comment_blog, Integer>("signaler"));
        Reponse_Reclamations_table.setItems(comment_bloglList);

    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
                comment_blogService rService=new comment_blogService();
                signalercommentaireService rService1=new signalercommentaireService();

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
////////////////////////////////////////////
supprimer.setOnAction(event -> {                        
rService.supprimerComment_blog(String.valueOf(rService.readAll()
                .get(Reponse_Reclamations_table.getSelectionModel().getSelectedIndex())
                .getId() ));
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information");
        alert.setHeaderText(null);
        alert.setContentText("supprimer");
        alert.show();        
            });
////////////////////////////////////////


    }    
    
}
