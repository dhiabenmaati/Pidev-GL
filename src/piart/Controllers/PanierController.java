/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package piart.Controllers;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import piart.Entities.Adresse;
import piart.Entities.Commande;
import piart.Entities.Panier;
import piart.Entities.Users;
import piart.Service.AdresseService;
import piart.Service.CommandeService;
import piart.Service.PanierService;
import piart.Service.UserService;

/**
 * FXML Controller class
 *
 * @author Amine
 */
public class PanierController {
    private PreparedStatement pstmt;
    private Connection cnx;
    @FXML
    private Label lbTotale;
    @FXML
    private TableView<Panier> tvPanier;
    @FXML
    private TableColumn<Panier, String> colNom;
    @FXML
    private TableColumn<Panier, Double> colPrix;
    @FXML
    private TableColumn<Panier, Integer> colQte;
    @FXML
    private TableColumn<Panier, Double> colTotal;
    @FXML
    private Button btnCommander;
    @FXML
    private Label lbNom;
    @FXML
    private Label lbPrenom;
    @FXML
    private Label lbAdresse;
    @FXML
    private Label lbCP;
    @FXML
    private Label lbVille;
    @FXML
    private Label lbTel;
    @FXML
    private RadioButton rdCB;
    @FXML
    private RadioButton rdLiv;
    
    public void initialize() {
        showPanier();
        final ToggleGroup group = new ToggleGroup();
        rdCB.setToggleGroup(group);
        rdLiv.setToggleGroup(group);
        showInfo();
    }
    
    @FXML
    private void Commander(ActionEvent event) throws IOException  {
        AdresseService as = new AdresseService();
        if(as.getAdressebyUserID(11) == null) { // USER ID
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText("Aucune adresse Trouvée");
            alert.setContentText("Veuillez ajouter une adresse de livraison svp !");
            alert.showAndWait();
            return ;
        }
        if(rdCB.isSelected()) {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("/piart/gui/PaymentForm.fxml"));
            Scene scene;
            scene = new Scene(fxmlLoader.load());
            Stage stage = new Stage();
            stage.setTitle("Paiement");
            stage.setScene(scene);
            PaymentFormController controller = fxmlLoader.getController();
            Double totale = Double.parseDouble(lbTotale.getText());
            controller.initData(totale);
            stage.show();
            
            return ;
        }
        if(!rdCB.isSelected() && !rdLiv.isSelected()) {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Erreur");
            alert.setContentText("Veuillez selection le mode de paiement svp !");
            alert.showAndWait();
            return ;
        }
        PanierService ps = new PanierService();
        if(ps.getPanierItems().isEmpty()) {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Erreur");
            alert.setContentText("Le panier est vide !");
            alert.showAndWait();
            return ;
        }
        CommandeService cs = new CommandeService();
        long millis = System.currentTimeMillis();  
        java.sql.Date date = new java.sql.Date(millis);
        Commande c = new Commande(11, date, 0); // USER ID
        cs.ajouterCommande(c);
        cs.ajouterDetailCommade();
    }
    
    public void showPanier() {
        PanierService ps = new PanierService();
        ObservableList<Panier> panierItems = FXCollections.observableArrayList();
        List<Panier> items = new ArrayList(ps.getPanierItems());
        int totale = 0;
        for(Panier item : items) {
            panierItems.add(item);
            totale += item.getTotale();
        }
        colNom.setCellValueFactory(new PropertyValueFactory<Panier, String>("nom"));
        colPrix.setCellValueFactory(new PropertyValueFactory<Panier, Double>("prix"));
        colQte.setCellValueFactory(new PropertyValueFactory<Panier, Integer>("qte"));
        colTotal.setCellValueFactory(new PropertyValueFactory<Panier, Double>("totale"));
        tvPanier.setItems(panierItems);
        
        lbTotale.setText(Double.toString(totale));
    }
    
    public void showInfo() {
        UserService us = new UserService();
        AdresseService as = new AdresseService();
        Users user = us.getUserByID(11); // USER ID
        lbNom.setText(user.getName());
        lbPrenom.setText(user.getSurname());
        Adresse adr = as.getAdressebyUserID(11);// USER ID
        if(adr == null) return ; 
        lbAdresse.setText(adr.getAdresse());
        lbCP.setText(Integer.toString(adr.getCodepostal()));
        lbVille.setText(adr.getVille());
        lbTel.setText(Integer.toString(adr.getNum_tel()));
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
    private void goToMesCommandes(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/piart/gui/MesCommandes.fxml"));
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
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
