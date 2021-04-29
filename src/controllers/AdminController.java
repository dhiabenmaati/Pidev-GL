package controllers;

import static Services.mysqlconnect.ConnectDb;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class AdminController implements Initializable {

    @FXML
    private VBox pnItems = null;
    @FXML
    private Button btnOverview;

    @FXML
    private Button btnOrders;

    @FXML
    private Button btnCustomers;

    @FXML
    private Button btnMenus;

    @FXML
    private Button btnPackages;

    @FXML
    private Button btnSettings;

    @FXML
    private Button btnSignout;
    
    @FXML
    private Text tot_user , tot_cat , tot_prod , tot_bid;
    
    @FXML
    private PieChart pieChart;
 
    @Override
    public void initialize(URL location, ResourceBundle resources ) {
        try {
            tot_user.setText("" + TotalUsers());
            tot_cat.setText("" + TotalCategory());
            tot_prod.setText("" + TotalProduit());
            tot_bid.setText("" + TotalEnchere());
            int NonVerified = TotalUsers() - VerifiedUsers() ;
            ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList(
            new PieChart.Data("Verified User", VerifiedUsers()),
            new PieChart.Data("Unverified", NonVerified ));
            pieChart.setData(pieChartData);
           
            } 
        catch (Exception e) { }
            }
    
    
    public void handleClicks(ActionEvent actionEvent) {
        btnCustomers.setOnAction(event -> {
        try {
            Parent page1 = FXMLLoader.load(getClass().getResource("/views/UserView.fxml"));
            Scene scene = new Scene(page1);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
            } catch (IOException ex) {
            Logger.getLogger(controllers.UsersController.class.getName()).log(Level.SEVERE, null, ex); }});
        
        btnSignout.setOnAction(event -> {
            closeButtonAction() ;});
}
    
    
    private void closeButtonAction(){
    // get a handle to the stage
    Stage stage = (Stage) btnSignout.getScene().getWindow();
    // do what you have to do
    stage.close();
}
    
    
// get users data from base
    public int TotalUsers(){
        Connection conn = ConnectDb();
        int TotalUsers = 0 ;
        try {
            PreparedStatement ps = conn.prepareStatement("select * from user");
            ResultSet rs = ps.executeQuery();
            while (rs.next()){   TotalUsers++ ;             }
        } catch (Exception e) { }
        return TotalUsers;
    }
    
        public int VerifiedUsers(){
        Connection conn = ConnectDb();
        int TotalUsers = 0 ;
        try {
            PreparedStatement ps = conn.prepareStatement("select * from user");
            ResultSet rs = ps.executeQuery(); 
            while (rs.next()){   
                if (rs.getString("status").equals("active"))  {TotalUsers++ ;}           }
        } catch (Exception e) { }
        return TotalUsers;
    }
    
        // get category data from base
    public int TotalCategory(){
        Connection conn = ConnectDb();
        int TotalCategory = 0 ;
        try {
            PreparedStatement ps = conn.prepareStatement("select * from categorie");
            ResultSet rs = ps.executeQuery();
            while (rs.next()){   TotalCategory++ ;             }
        } catch (Exception e) { }
        return TotalCategory;
    }
    
            // get Produits data from base
    public int TotalProduit(){
        Connection conn = ConnectDb();
        int TotalProduit = 0 ;
        try {
            PreparedStatement ps = conn.prepareStatement("select * from produit");
            ResultSet rs = ps.executeQuery();
            while (rs.next()){   TotalProduit++ ;             }
        } catch (Exception e) { }
        return TotalProduit;
    }
    
            // get Enchere data from base
    public int TotalEnchere(){
        Connection conn = ConnectDb();
        int TotalEnchere = 0 ;
        try {
            PreparedStatement ps = conn.prepareStatement("select * from enchere");
            ResultSet rs = ps.executeQuery();
            while (rs.next()){   TotalEnchere++ ;             }
        } catch (Exception e) { }
        return TotalEnchere;
    }
    
    
}
