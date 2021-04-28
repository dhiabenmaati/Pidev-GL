/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import entity.blog;
import entity.reponse_reclamation;
import java.io.IOException;
import java.net.URL;
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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import service.blogService;
import service.comment_blogService;
import service.reponse_reclamationService;

/**
 * FXML Controller class
 *
 * @author houssem
 */
public class AccueilBlogController implements Initializable {

    @FXML
    private Button blog;
    @FXML
    private Button reclamation;
    @FXML
    private Button ajouter_publication;
    @FXML
    private TableView<blog> blog_table;
    @FXML
    private TableColumn<blog, Integer> user_table;
    @FXML
    private TableColumn<blog, String> title_table;
    @FXML
    private Label title;
    @FXML
    private Text description;
    @FXML
    private Label user;
    @FXML
    private Label date;
    @FXML
    private ImageView image;
    @FXML
    private Label nbc;
    @FXML
    private Button voir_commentaire;
    @FXML
    private Button notification;
    @FXML
    private Label textimg;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
                            blogService rService =new blogService();
                            comment_blogService rService1=new comment_blogService();

    ajouter_publication.setOnAction(event -> {

            try {
                Parent page1 = FXMLLoader.load(getClass().getResource("/view/AddPublication.fxml"));
                Scene scene = new Scene(page1);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
            } catch (IOException ex) {
                Logger.getLogger(AccueilController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
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
        voir_commentaire.setOnAction(event -> {

            try {
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("/view/Commentaire.fxml"));
                Parent page1 = loader.load();
                CommentaireController controllercCommentaireController = loader.getController();
                controllercCommentaireController.intData(rService.readAll()
                .get(blog_table.getSelectionModel().getSelectedIndex())
                .getId());
                Scene scene = new Scene(page1);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
            } catch (IOException ex) {
                Logger.getLogger(AccueilController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }); 

    ///////////////////////////////////////////////////////////////////////////////////////////////
            ObservableList<blog>  bList = FXCollections.observableArrayList();
        List<blog>  bloList = new ArrayList(rService.readAllValid());
        

        for(blog r : bloList)
            bList.add(r);
        
        user_table.setCellValueFactory(new PropertyValueFactory<blog, Integer>("user_id"));
        title_table.setCellValueFactory(new PropertyValueFactory<blog, String>("title"));
        blog_table.setItems(bList);
        blog_table.setOnMouseClicked(event->{
        title.setText(String.valueOf(rService.readAll()
                .get(blog_table.getSelectionModel().getSelectedIndex())
                .getTitle()));   
        description.setText(String.valueOf(rService.readAll()
                .get(blog_table.getSelectionModel().getSelectedIndex())
                .getDescription())); 
        user.setText(String.valueOf(rService.readAll()
                .get(blog_table.getSelectionModel().getSelectedIndex())
                .getUser_id()));   
        date.setText(String.valueOf(rService.readAll()
                .get(blog_table.getSelectionModel().getSelectedIndex())
                .getDate())); 
        
        nbc.setText(String.valueOf(rService1.nbc(String.valueOf( 
                rService.readAll()
                .get(blog_table.getSelectionModel().getSelectedIndex())
                .getId()))));         
                    
        
        image.setImage(new Image ("/imgg/"+String.valueOf(rService.readAll()
                .get(blog_table.getSelectionModel().getSelectedIndex())
                .getImage())));

                   });  
    }    
    
}
