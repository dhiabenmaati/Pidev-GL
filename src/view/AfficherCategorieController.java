/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import art.Art;
import entity.Categorie;
import entity.Produit;
import java.io.IOException;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javax.swing.JOptionPane;
import services.CategorieService;
import utils.DataSource;

/**
 * FXML Controller class
 *
 * @author 21624
 */
public class AfficherCategorieController implements Initializable {

    @FXML
    private TableView<Categorie> categorie;
    @FXML
    private TableColumn<?, ?> col_nom;
    @FXML
    private TableColumn<?, ?> col_desc;
    @FXML
    private TableColumn<?, ?> col_conf;
     @FXML
    private AnchorPane afficher;

    @FXML
    private TextField txt_nom;

    @FXML
    private TextField txt_desc;

    @FXML
    private TextField txt_conf;

    @FXML
    private Button btn_ajout;
      @FXML
    private Button btn_supp;
          @FXML
    private Button ActiverDesactiver;
   

    


        Connection connection=null;
PreparedStatement pst=null;
    private Object log;
    @FXML
    private Button btn_modifier;
    
        
  
    

       public void categorie(){
    
         CategorieService cs  =new CategorieService();
        ObservableList<Categorie> listeCategories= cs.readAll();
  
    this.col_nom.setCellValueFactory(new PropertyValueFactory<>("nom_cat"));
    this.col_desc.setCellValueFactory(new PropertyValueFactory<>("desc_cat"));
    this.col_conf.setCellValueFactory(new PropertyValueFactory<>("confirmation"));
   


          this.categorie.setItems(listeCategories);

    /**
     * Initializes the controller class.
     */
   
       
    
     
        
    }    

    @Override
    public void initialize(URL location, ResourceBundle resources) {
         CategorieService cs  =new CategorieService();
            btn_supp.setOnAction((ActionEvent event)-> {  
            cs.supprimer(cs.readAll()
                .get(categorie.getSelectionModel().getSelectedIndex())
                .getNom_cat());
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information");
        alert.setHeaderText(null);
        alert.setContentText("supprimer");
        alert.show();
                 });
        //////
         
         
         
         
         
         
          btn_ajout.setOnAction(( ActionEvent event) -> {
            
                       Categorie c =new Categorie(txt_nom.getText(), txt_desc.getText(), txt_conf.getText());
            
                      
                       cs.ajouterCategorie(c);
                       Alert alert = new Alert(Alert.AlertType.INFORMATION);
                       alert.setTitle("Categorie ");
                       alert.setHeaderText(null);
                       alert.setContentText("Categorie Ajoute ");
                       alert.show();
                       txt_nom.setText("");
                       txt_desc.setText("");
                       txt_conf.setText("");
                          });
                  
       categorie();
       categorie.setVisible(true);
       
       
    
     
        
    }  
          CategorieService cs  =new CategorieService();

    private void ActiverDesactiver(ActionEvent event) {
       
   
       

    

    }
 


    @FXML
    private void Activer(ActionEvent event) {
         Categorie c = categorie.getSelectionModel().getSelectedItem();
        if(c.getConfirmation().equals("active")){
        cs.desactiver(c.getNom_cat());
        }else {System.out.println(c.getConfirmation());
            
           cs.activer(c.getNom_cat());}
    }

    @FXML
    private void modifier(ActionEvent event) {
        try {
                    Node node = (Node) event.getSource();
                    Stage stage = (Stage) node.getScene().getWindow();
                    stage.close();
                    Scene scene = new Scene(FXMLLoader.load(Art.class.getResource("/view/ModificationCategorie.fxml")));
                    stage.setScene(scene);
                    stage.show();          
        } catch (IOException ex) {
               System.err.println(ex.getMessage());
        }
    }
}
    

      
  



   
    
