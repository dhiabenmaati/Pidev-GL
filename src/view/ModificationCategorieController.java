/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import entity.Categorie;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import services.CategorieService;

/**
 * FXML Controller class
 *
 * @author 21624
 */
public class ModificationCategorieController implements Initializable {

    @FXML
    private TextField nomCat;
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
        Categorie c = (Categorie) stage.getUserData();
  // Step 3
          Categorie A = new Categorie(); 

        System.out.println("variable recupérer "+c.getId());
        CategorieService cs =new  CategorieService();
      A.setNom_cat(nomCat.getText());
    
       cs.modifier(A,c.getId());
    }
    }
    

