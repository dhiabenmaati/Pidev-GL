/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package piart.Controllers;

import com.stripe.exception.CardException;
import com.stripe.exception.StripeException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import piart.Entities.Users;
import piart.Service.StripeService;
import piart.Service.UserService;

/**
 * FXML Controller class
 *
 * @author Amine
 */
public class PaymentFormController implements Initializable {

    @FXML
    private TextField tfNumber;
    @FXML
    private TextField tfName;
    @FXML
    private TextField tfMonth;
    @FXML
    private TextField tfYear;
    @FXML
    private TextField tfCVC;

    double amount;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }    
    
    public void initData(Double totale) {
        amount = totale;
        System.out.println(totale);

    }

    @FXML
    private void payer(ActionEvent event) throws StripeException {
        Map<String, Object> cardParam = new HashMap<>();
        cardParam.put("number", tfNumber.getText());
        cardParam.put("exp_month", tfMonth.getText());
        cardParam.put("exp_year", tfYear.getText());
        cardParam.put("cvc", tfCVC.getText());
        cardParam.put("name", tfName.getText());
        StripeService ss = new StripeService();
        //ss.addCard(user, cardParam);ss.addClient(user);
        UserService us = new UserService();
        Users user = us.getUserByID(20);
        try {
            ss.addClient(user);
            ss.addCard(user, cardParam);
            ss.chargeClient(user, 100*(int)(amount));
        } catch(StripeException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Dialog");
            alert.setHeaderText("Erreur" + e.getCode());
            alert.setContentText(e.getMessage());
            alert.showAndWait();
        }
    }
    
}
