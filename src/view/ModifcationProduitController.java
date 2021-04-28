/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import entity.Produit;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import services.ProduitService;

/**
 * FXML Controller class
 *
 * @author 21624
 */
public class ModifcationProduitController implements Initializable {

    @FXML
    private TextField nomProd;
    @FXML
    private Button Modifier;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void Modifier(ActionEvent event) {
        
         Node node = (Node) event.getSource();
  Stage stage = (Stage) node.getScene().getWindow();
  // Step 2
        Produit P= (Produit) stage.getUserData();
  // Step 3
          Produit A = new Produit(); 

        System.out.println("variable recupérer "+P.getId());
        ProduitService ps =new  ProduitService();
      A.setNom_prod(nomProd.getText());
    
       ps.modifier(A,P.getId());
    }
    }
        
        
    
    

