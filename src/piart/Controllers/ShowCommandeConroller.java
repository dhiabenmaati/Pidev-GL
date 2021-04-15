/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package piart.Controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import piart.Entities.Commande;
import piart.Service.CommandeService;

/**
 * FXML Controller class
 *
 * @author Amine
 */
public class ShowCommandeConroller {

    @FXML
    private TableView<Commande> tvCommandes;
    @FXML
    private TableColumn<Commande, Integer> colID;
    @FXML
    private TableColumn<Commande, Date> colDateCreer;
    @FXML
    private TableColumn<Commande, Date> colDateExpedirer;
    @FXML
    private TableColumn<Commande, Integer> colStatus;
    @FXML
    private Button btnBack;

    
    public void initialize() {
        showCommandes();
    }    

    @FXML
    private void goToMenu(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/piart/gui/menu.fxml"));
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }
    
    public void showCommandes() {
        CommandeService cs = new CommandeService();
        ObservableList<Commande> commandesList = FXCollections.observableArrayList();
        List<Commande> cmds = new ArrayList(cs.getAllCommandes());
        for(Commande c : cmds)
            commandesList.add(c);
        
        colID.setCellValueFactory(new PropertyValueFactory<Commande, Integer>("id"));
        colDateCreer.setCellValueFactory(new PropertyValueFactory<Commande, Date>("date_creer"));
        colDateExpedirer.setCellValueFactory(new PropertyValueFactory<Commande, Date>("date_expedirer"));
        colStatus.setCellValueFactory(new PropertyValueFactory<Commande, Integer>("status"));
        tvCommandes.setItems(commandesList);
    }
    
}