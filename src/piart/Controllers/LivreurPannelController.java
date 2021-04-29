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
import javafx.scene.control.CheckBox;
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
public class LivreurPannelController implements Initializable {

    @FXML
    private TableView<Commande> tvCommandes;
    @FXML
    private TableColumn<Commande, Integer> colClient;
    @FXML
    private TableColumn<Commande, Date> colDateExpedirer;
    @FXML
    private TableColumn<Commande, Date> colDateCreer;
    @FXML
    private TableColumn<Commande, Integer> colLivreur;
    @FXML
    private TableColumn<Commande, Integer> colStatus;
    @FXML
    private CheckBox cbLivrer;
    @FXML
    private CheckBox cbNonLivrer;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        showCommandes();
    }
    
    public void showCommandes() {
        CommandeService cs = new CommandeService();
        ObservableList<Commande> commandesList = FXCollections.observableArrayList();
        List<Commande> cmds = new ArrayList(cs.getCommandeByLivreurID(21)); // LIVREUR ID
        for(Commande c : cmds) {
            commandesList.add(c);
        }
        colClient.setCellValueFactory(new PropertyValueFactory<>("livreur_id"));
        colLivreur.setCellValueFactory(new PropertyValueFactory<>("user_id"));
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
    
    @FXML
    private void goToCommandes(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/piart/gui/showCommande.fxml"));
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }
    
    @FXML
    private void goToLivreurs(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/piart/gui/UserView.fxml"));
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }
    
    @FXML
    private void goToLivraison(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/piart/gui/LivreurPannel.fxml"));
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void getDetailCommande(MouseEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("/piart/gui/DetailCommandeLiv.fxml"));
            Scene scene;
            scene = new Scene(fxmlLoader.load());
            Stage stage = new Stage();
            stage.setTitle("Detail de la commande");
            stage.setScene(scene);
            // Send Parameters
            DetailCommandeLivController controller = fxmlLoader.getController();
            Commande c = tvCommandes.getSelectionModel().getSelectedItem();
            controller.initData(c);
            stage.show();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }


    @FXML
    private void LivrerOnly(ActionEvent event) {
    }

    @FXML
    private void NonLivrerOnly(ActionEvent event) {
    }
    
}
