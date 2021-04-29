/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package piart.Controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.util.Callback;
import javax.swing.JOptionPane;
import piart.Entities.Commande;
import piart.Service.CommandeService;

/**
 * FXML Controller class
 *
 * @author Amine
 */
public class MesCommandesController implements Initializable {

    @FXML
    private TableView<Commande> tvCommandes;
    @FXML
    private TableColumn<Commande, Date> colDateExpedirer;
    @FXML
    private TableColumn<Commande, Date> colDateCreer;
    @FXML
    private TableColumn<Commande, Integer> colStatus;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        show();
    }    

    
    public void show() {
        CommandeService cs = new CommandeService();
        ObservableList<Commande> commandesList = FXCollections.observableArrayList();
        List<Commande> cmds = new ArrayList(cs.getCommandeByUserID(11)); // USER ID
        for(Commande c : cmds) {
            commandesList.add(c);
        }
        colDateExpedirer.setCellValueFactory(new PropertyValueFactory<>("date_expedirer"));
        colStatus.setCellValueFactory(new PropertyValueFactory<>("status"));
        colDateCreer.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Commande, Date>, ObservableValue<Date>>() {
            @Override
            public ObservableValue<Date> call(TableColumn.CellDataFeatures<Commande, Date> param) {
                return new ReadOnlyObjectWrapper(param.getValue().getData_creer());
            }
        });
        colDateExpedirer.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Commande, Date>, ObservableValue<Date>>() {
            @Override
            public ObservableValue<Date> call(TableColumn.CellDataFeatures<Commande, Date> param) {
                return new ReadOnlyObjectWrapper(param.getValue().getDate_expedirer());
            }
        });
        tvCommandes.setItems(commandesList);
    }
    
    
    private void goToProduit(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/piart/gui/showProduit.fxml"));
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void goToAdresse(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/piart/gui/adresse.fxml"));
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void goToPanier(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/piart/gui/panier.fxml"));
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void getDetailCommande(MouseEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("/piart/gui/DetailCommandeFront.fxml"));
            Scene scene;
            scene = new Scene(fxmlLoader.load());
            Stage stage = new Stage();
            stage.setTitle("Detail de la commande");
            stage.setScene(scene);
            // Send Parameters
            DetailCommandeFrontController controller = fxmlLoader.getController();
            Commande c = tvCommandes.getSelectionModel().getSelectedItem();
            controller.initData(c);
            stage.show();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    @FXML
    private void goToMenu(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/piart/gui/menu.fxml"));
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }
    
}
