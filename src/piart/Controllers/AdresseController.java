/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package piart.Controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import piart.Entities.Adresse;
import piart.Service.AdresseService;

/**
 * FXML Controller class
 *
 * @author Amine
 */
public class AdresseController implements Initializable{

    @FXML
    private TextField tfAdresse;
    @FXML
    private TextField tfVille;
    @FXML
    private TextField tfCP;
    @FXML
    private TextField tfNum;
    @FXML
    private TableView<Adresse> tvAdresses;
    @FXML
    private TableColumn<Adresse, String> colAdresse;
    @FXML
    private TableColumn<Adresse, String> colVille;
    @FXML
    private TableColumn<Adresse, Integer> colCP;
    @FXML
    private TableColumn<Adresse, Integer> colNum;
    @FXML
    private Button btnBack;
    @FXML
    private Button btnAdd;
    @FXML
    private Button btnEdit;
    @FXML
    private Button btnDelete;
    @FXML
    private Label lbId;
    @FXML
    private Label lbSuccess;
    @FXML
    private Label lbError;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        showAdresses();
    }     

    @FXML
    private void goToMenu(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/piart/gui/menu.fxml"));
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void add(ActionEvent event) {
        if(tfAdresse.getText().isEmpty() || tfCP.getText().isEmpty() || tfNum.getText().isEmpty() || tfVille.getText().isEmpty()) {
            lbError.setText("Le champs adresse est obligatoire !");
        } else {
            AdresseService as = new AdresseService();
            Adresse adr = new Adresse(tfAdresse.getText(), Integer.parseInt(tfCP.getText()), tfVille.getText(), Integer.parseInt(tfCP.getText()), 11);
            as.ajouterAdresse(adr);
            lbSuccess.setText("L'adresse a été ajoutée avec succée !");
            lbError.setText("");
        }
        showAdresses();
    }

    @FXML
    private void edit(ActionEvent event) {
        AdresseService as = new AdresseService();
        Adresse adr = new Adresse(Integer.parseInt(lbId.getText()), tfAdresse.getText(), Integer.parseInt(tfCP.getText()), tfVille.getText(), Integer.parseInt(tfCP.getText()), 11);
        as.modifierAdresse(adr);
        showAdresses();
        lbSuccess.setText("L'adresse a été modifiée avec succée !");
    }

    @FXML
    private void delete(ActionEvent event) {
        AdresseService as = new AdresseService();
        Adresse adr = new Adresse(Integer.parseInt(lbId.getText()), tfAdresse.getText(), Integer.parseInt(tfCP.getText()), tfVille.getText(), Integer.parseInt(tfCP.getText()), 11);
        as.supprimerAdresse(adr);
        showAdresses();
        lbSuccess.setText("L'adresse a été supprimée avec succée !");
    }
    
    public void showAdresses() {
        AdresseService as = new AdresseService();
        ObservableList<Adresse> adresseList = FXCollections.observableArrayList();
        List<Adresse> adrs = new ArrayList(as.getAllAdresses());
        for(Adresse a : adrs)
            adresseList.add(a);
        colAdresse.setCellValueFactory(new PropertyValueFactory<Adresse, String>("adresse"));
        colCP.setCellValueFactory(new PropertyValueFactory<Adresse, Integer>("codepostal"));
        colVille.setCellValueFactory(new PropertyValueFactory<Adresse, String>("ville"));
        colNum.setCellValueFactory(new PropertyValueFactory<Adresse, Integer>("num_tel"));
        tvAdresses.setItems(adresseList);
    }

    @FXML
    private void handleMouseClick(MouseEvent event) {
        Adresse adr = tvAdresses.getSelectionModel().getSelectedItem();
        lbId.setText(Integer.toString(adr.getId()));
        tfAdresse.setText(adr.getAdresse());
        tfCP.setText(Integer.toString(adr.getCodepostal()));
        tfNum.setText(Integer.toString(adr.getNum_tel()));
        tfVille.setText(adr.getVille());
    }
    
}
