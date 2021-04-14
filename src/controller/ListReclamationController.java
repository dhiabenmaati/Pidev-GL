/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import com.sun.org.apache.xerces.internal.impl.dv.xs.ListDV;
import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author houssem
 */
public class ListReclamationController implements Initializable {

    @FXML
    private AnchorPane reclamation;
    @FXML
    private TableColumn<?, Integer> id;
    @FXML
    private TableColumn<?, String> desc_rec;
    @FXML
    private TableColumn<?, String> status_rec;
    @FXML
    private TableColumn<?, Date> progress_at;
    @FXML
    private TableColumn<?, Date> valid_at;
    @FXML
    private TableColumn<?, Date> reclmation_at;
    @FXML
    private TableColumn<?, String> type;
    @FXML
    private TableColumn<?, Integer> user_id;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
