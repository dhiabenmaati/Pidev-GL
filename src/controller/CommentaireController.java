/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import entity.blog;
import entity.comment_blog;
import entity.signalercommentaire;
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
import javafx.scene.text.Text;
import javafx.stage.Stage;
import service.blogService;
import service.comment_blogService;
import service.signalercommentaireService;

/**
 * FXML Controller class
 *
 * @author houssem
 */
public class CommentaireController implements Initializable {

    private Text t;
    @FXML
    private Button blog;
    @FXML
    private Button reclamation;
    @FXML
    private TableView<comment_blog> commentaire_table;
    @FXML
    private TableColumn<comment_blog, Integer> user_table;
    @FXML
    private TableColumn<comment_blog, Date> date_table;
    @FXML
    private Text text_commentaire;
    @FXML
    private TextArea commentaire;
    @FXML
    private Button ajouter;
    @FXML
    private Button signaler;
    
    public String idd;
    
    public void intData(int id){
                comment_blogService rService =new comment_blogService();
                        signalercommentaireService rService1 =new signalercommentaireService();

                ObservableList<comment_blog>  comment_bloglisList = FXCollections.observableArrayList();
        List<comment_blog>  cList = new ArrayList(rService.readAllId(String.valueOf(id)));
        
        for(comment_blog r : cList)
            comment_bloglisList.add(r);
        
        user_table.setCellValueFactory(new PropertyValueFactory<comment_blog, Integer>("user_id"));
        date_table.setCellValueFactory(new PropertyValueFactory<comment_blog, Date>("date"));
        commentaire_table.setItems(comment_bloglisList);
        commentaire_table.setOnMouseClicked(event->{
        text_commentaire.setText(String.valueOf(rService.readAllId(String.valueOf(id))
                .get(commentaire_table.getSelectionModel().getSelectedIndex())
                .getComment()));           
                    });  
            ajouter.setOnAction(event -> {            
      comment_blog c1=new comment_blog(id, commentaire.getText(), 5);
            rService.ajouterComment_blog(c1);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information");
        alert.setHeaderText(null);
        alert.setContentText("commentaire Ajouter");
        alert.show();
        commentaire.setText("");  
            });
                signaler.setOnAction(event -> {            
      signalercommentaire s1=new signalercommentaire(1,rService.readAllId(String.valueOf(id))
                .get(commentaire_table.getSelectionModel().getSelectedIndex())
                .getId()) ;
      if (rService1.verfierSignaler(s1)!=0){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information");
        alert.setHeaderText(null);
        alert.setContentText("Deja Signaler");
        alert.show();
      }
      else{
          rService1.ajouterSignaler(s1);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information");
        alert.setHeaderText(null);
        alert.setContentText("commentaire signaler");
        alert.show();
      }
            });

        }
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        comment_blogService rService =new comment_blogService();
        signalercommentaireService rService1 =new signalercommentaireService();
///////////////////////////////////////////////////////////////////////////////////////////////////

///////////////////////////////////////////////////////////////////////////////////////////////////    
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
//////////////////////////////////////////////////////////////////////////////////////////////////////
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
///////////////////////////////////////////////////////////////////////////////////////////////////////////////

/////////////////////////////////////////////////////////////////////////////////////////////////////////   

    }   
    
    
}
