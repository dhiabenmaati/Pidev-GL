/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import entity.Produit;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;
import services.ProduitService;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author 21624
 */
public class FrontProduitController implements Initializable {

    @FXML
    private TableView<Produit> afficher;
    @FXML
    private TableColumn<?, ?> col_nom;
    @FXML
    private TableColumn<?, ?> col_prix;
    @FXML
    private TableColumn<?, ?> col_desc;
    @FXML
    private TableColumn<?, ?> col_qte;
    @FXML
    private AnchorPane produit;
    @FXML
    private TextField rechercher;

    /**
     * Initializes the controller class.
     */
           public void Produit(){
    
        
        ProduitService ps  =new ProduitService();
        ObservableList<Produit> listeProduits= ps.readAll();
  
    this.col_nom.setCellValueFactory(new PropertyValueFactory<>("nom_prod"));
    this.col_prix.setCellValueFactory(new PropertyValueFactory<>("prix_prod"));
    this.col_desc.setCellValueFactory(new PropertyValueFactory<>("desc_prod"));
    this.col_qte.setCellValueFactory(new PropertyValueFactory<>("qte_prod"));
 

          this.afficher.setItems(listeProduits);
    }    
    
    
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         Produit();
      produit.setVisible(true);
    }
        // TODO

    @FXML
    private void rechercher(ActionEvent event) {
              String rech=rechercher.getText();
        ProduitService ps=new ProduitService();
        ps.recherche(rech);
        afficher.setItems(ps.recherche(rech));
        
    }
     
}
