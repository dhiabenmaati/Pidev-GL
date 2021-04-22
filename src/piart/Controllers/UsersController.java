/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package piart.Controllers;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javax.swing.JOptionPane;
import piart.Entities.Users;
import piart.Service.LivreurService;
import piart.Service.mysqlconnect;

/**
 *
 * @author alabe
 */
public class UsersController implements Initializable {
    
    @FXML
    private TableView<Users> table_users;

    @FXML
    private TableColumn<Users, String> col_email;
    
    @FXML
    private TableColumn<Users, String> col_nom;
    
    @FXML
    private TableColumn<Users, String> col_prenom;
    
    @FXML
    private TableColumn<Users, Integer> col_tel;
    
    @FXML
    private TableColumn<Users, Integer> col_id;
    
    @FXML
    private TextField txt_email;

    @FXML
    private TextField txt_password;

    @FXML
    private TextField txt_name;

    @FXML
    private TextField txt_surname;
    
    @FXML
    private TextField txt_tel;
               
    @FXML
    private Label lbId;
    
    @FXML
    private TextField tfSearch;
    
    ObservableList<Users> listM;
    
    int index = -1;
    
    Connection conn =null;
    ResultSet rs = null;
    PreparedStatement pst = null;
    
    
     
    @FXML
    public void Add_users () {    
        conn = mysqlconnect.ConnectDb();
        String sql = "insert into user (email,password,name,surname, num_tel) values (?,?,?,?,?)";
        try {
            pst = conn.prepareStatement(sql);
            pst.setString(1, txt_email.getText());
            pst.setString(2, txt_password.getText());
            pst.setString(3, txt_name.getText());
            pst.setString(4, txt_surname.getText());
            pst.setInt(5, Integer.parseInt(txt_tel.getText()));
            pst.execute();
            UpdateTable();
            JOptionPane.showMessageDialog(null, "Users Add succes");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
        
    }
    

    //////// methode select users ///////
    @FXML
    void getSelected (MouseEvent event){
        Users user = table_users.getSelectionModel().getSelectedItem();
        txt_email.setText(user.getEmail());
        txt_name.setText(user.getName());
        txt_surname.setText(user.getSurname());
        txt_tel.setText(Integer.toString(user.getNum_tel()));
        lbId.setText(Integer.toString(user.getId()));
    }

    @FXML
    public void Edit (){   
        try {
            conn = mysqlconnect.ConnectDb();
            int value1 = Integer.parseInt(txt_tel.getText());
            String value2 = txt_email.getText();
            String value3 = txt_password.getText();
            String value4 = txt_name.getText();
            String value5 = txt_surname.getText();
            int id = Integer.parseInt(lbId.getText());
            String sql = new String();
            if(!value3.isEmpty()) 
                sql = "update user set num_tel= "+value1+",email= '"+value2+"',password= '"+value3+"',name= '"+value4+"',surname= '"+value5+"' where id="+id;
            else  
                sql = "update user set num_tel= "+value1+",email= '"+value2+"',name= '"+value4+"',surname= '"+value5+"' where id="+id;
            pst= conn.prepareStatement(sql);
            pst.execute();
            UpdateTable();
            JOptionPane.showMessageDialog(null, "Updated");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
        
    }
    
    @FXML
    public void Delete(){
    conn = mysqlconnect.ConnectDb();
    String sql = "delete from user where id = ?";
        try {
            pst = conn.prepareStatement(sql);
            pst.setInt(1, Integer.parseInt(lbId.getText()));
            pst.execute();
            UpdateTable();
            JOptionPane.showMessageDialog(null, "Delete");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    
    public void UpdateTable(){
        col_email.setCellValueFactory(new PropertyValueFactory<Users,String>("email"));
        col_nom.setCellValueFactory(new PropertyValueFactory<Users,String>("name"));
        col_prenom.setCellValueFactory(new PropertyValueFactory<Users,String>("surname"));
        col_tel.setCellValueFactory(new PropertyValueFactory<Users, Integer>("num_tel"));
        col_id.setCellValueFactory(new PropertyValueFactory<Users, Integer>("id"));
        
        listM = mysqlconnect.getDatausers();
        table_users.setItems(listM);
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       UpdateTable();
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
    private void Search(KeyEvent event) {
        System.out.println(tfSearch.getText());
        LivreurService lv = new LivreurService();
        ObservableList<Users> usersList = FXCollections.observableArrayList();
        List<Users> users = new ArrayList(lv.SearchLivreurByName(tfSearch.getText()));
        for(Users u : users)
            usersList.add(u);
        col_email.setCellValueFactory(new PropertyValueFactory<Users,String>("email"));
        col_nom.setCellValueFactory(new PropertyValueFactory<Users,String>("name"));
        col_prenom.setCellValueFactory(new PropertyValueFactory<Users,String>("surname"));
        col_tel.setCellValueFactory(new PropertyValueFactory<Users, Integer>("num_tel"));
        col_id.setCellValueFactory(new PropertyValueFactory<Users, Integer>("id"));
        table_users.setItems(usersList);
    }

}