/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import art.Art;
import entity.Produit;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;


import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javax.swing.JOptionPane;
import services.ProduitService;
import utils.DataSource;

/**
 * FXML Controller class
 *
 * @author 21624
 */
public class AfficherProduitController implements Initializable {

    @FXML
    private AnchorPane produit;
    @FXML
    private TableView<Produit> afficher;
    @FXML
    private TableColumn<?, ?> col_nom;
    @FXML
    private TableColumn<?, ?> col_desc;
    @FXML
    private TableColumn<?, ?> col_prix;
    @FXML
    private TableColumn<?, ?> col_qte;
    @FXML
    private TableColumn<?, ?> col_img;
    @FXML
    private TableColumn<?, ?> col_valid;
       @FXML
    private TextField txt_nom;

    @FXML
    private TextField txt_img;

    @FXML
    private TextField txt_desc;

    @FXML
    private TextField txt_prix;

    @FXML
    private TextField txt_qte;

    @FXML
    private TextField txt_valid;

    @FXML
    private Button btn_ajout;
    
    
   
 
       @FXML
    private TextField recherche;
   
       @FXML
    private Button btn_supp;
        @FXML
    private Button btn_modifier;

    
    
  Connection connection=null;

        
   
       

       
  
 
    
     public void produit(){
         
        ProduitService ps  =new ProduitService();
        ObservableList<Produit> listeProduits= ps.readAll();
   this.col_nom.setCellValueFactory(new PropertyValueFactory<>("nom_prod"));
   this.col_desc.setCellValueFactory(new PropertyValueFactory<>("desc_prod"));
   this.col_prix.setCellValueFactory(new PropertyValueFactory<>("prix_prod"));
   this.col_qte.setCellValueFactory(new PropertyValueFactory<>("qte_prod"));
   this.col_img.setCellValueFactory(new PropertyValueFactory<>("image_prod"));
   this.col_valid.setCellValueFactory(new PropertyValueFactory<>("valid_prod"));


     this.afficher.setItems(listeProduits);
     
       
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
          
                 ProduitService ps=new ProduitService();
        
        // TODO
        btn_supp.setOnAction((ActionEvent event)-> {  
            ps.supprimer(ps.readAll()
                .get(afficher.getSelectionModel().getSelectedIndex())
                .getNom_prod());
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information");
        alert.setHeaderText(null);
        alert.setContentText("supprimer");
        alert.show();
                 });
        //////
      
                
     ///////////
        btn_ajout.setOnAction(new EventHandler<ActionEvent>() {
                     @Override
                     public void handle(ActionEvent event) {
                         Produit p =new Produit(txt_nom.getText(), txt_desc.getText(), 0, 0, txt_img.getText(), 0, 0);
                         
                         ps.ajouterProduit(p);
                         Alert alert = new Alert(Alert.AlertType.INFORMATION);
                         alert.setTitle("Produit ");
                         alert.setHeaderText(null);
                         alert.setContentText("Produit Ajoute");
                         alert.show();
                         txt_nom.setText("");
                         txt_desc.setText("");
                         txt_img.setText("");
                     }
                 });

        
              
      produit();
      produit.setVisible(true);
      
      
                
                 }
                        
    @FXML
    
    void recherche(ActionEvent event) {
        String rech=recherche.getText();
        ProduitService ps=new ProduitService();
        ps.recherche(rech);
        afficher.setItems(ps.recherche(rech));

    }
    private void modifier(ActionEvent event) {
        try {
                    Node node = (Node) event.getSource();
                    Stage stage = (Stage) node.getScene().getWindow();
                    stage.close();
                  Scene scene = new Scene(FXMLLoader.load(Art.class.getResource("/view/ModifcationProduit.fxml")));
                    stage.setScene(scene);
                    stage.show();          
        } catch (IOException ex) {
               System.err.println(ex.getMessage());
        }
    }
}


  



                

       

    

      

   

   
 
  

  

  
            
    
  
       
        
  
            
  


  

    

   

  
       

   
  

    


    
       
        
                  

          
      
    

