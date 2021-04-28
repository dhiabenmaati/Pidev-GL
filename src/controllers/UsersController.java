/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javax.swing.JOptionPane;
import Entity.Users;
import Services.mysqlconnect;
/**
 *
 * @author alabe
 */
public class UsersController implements Initializable {
    
    @FXML
    private TableView<Users> table_users;

    @FXML
    private TableColumn<Users, Integer> col_id;

    @FXML
    private TableColumn<Users, String> col_email;

    @FXML
    private TableColumn<Users, String> col_password;

    @FXML
    private TableColumn<Users, String> col_name;

    @FXML
    private TableColumn<Users, String> col_surname;
    
     @FXML
    private TextField txt_email;

    @FXML
    private TextField txt_password;

    @FXML
    private TextField txt_name;

    @FXML
    private TextField txt_surname;
        
    @FXML
    private TextField txt_id;
       
    ObservableList<Users> listM;
    
    int index = -1;
    
    Connection conn =null;
    ResultSet rs = null;
    PreparedStatement pst = null;
    
    
     
    public void Add_users (){    
        conn = mysqlconnect.ConnectDb();
        String sql = "insert into user (email,password,name,surname)values(?,?,?,? )";
        try {
            pst = conn.prepareStatement(sql);
            pst.setString(1, txt_email.getText());
            pst.setString(2, txt_password.getText());
            pst.setString(3, txt_name.getText());
            pst.setString(4, txt_surname.getText());
            
            if (txt_email.getText().length()<3 || txt_password.getText().length()<8 || txt_name.getText().length()<3 || txt_surname.getText().length()<3 ){ 
            System.out.println("Error in Form!");
            JOptionPane.showMessageDialog(null, "Error in form ... Please verify");
            }
            
            else{
                pst.execute();
            JOptionPane.showMessageDialog(null, "Users Add succes");
            UpdateTable(); }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }
    

    //////// methode select users ///////
    @FXML
    void getSelected (MouseEvent event){
    index = table_users.getSelectionModel().getSelectedIndex();
    if (index <= -1){
    
        return;
    }
    txt_id.setText(col_id.getCellData(index).toString());
    txt_email.setText(col_email.getCellData(index).toString());
    txt_password.setText(col_password.getCellData(index).toString());
    txt_name.setText(col_name.getCellData(index).toString());
    txt_surname.setText(col_surname.getCellData(index).toString());
    
    }

    public void Edit (){   
        try {
            conn = mysqlconnect.ConnectDb();
            String value1 = txt_id.getText();
            String value2 = txt_email.getText();
            String value3 = txt_password.getText();
            String value4 = txt_name.getText();
            String value5 = txt_surname.getText();
            
            if (txt_email.getText().length()<3 || txt_password.getText().length()<8 || txt_name.getText().length()<3 || txt_surname.getText().length()<3 ){ 
            System.out.println("Error in Form!");
            JOptionPane.showMessageDialog(null, "Error in form ... Please verify");
            }
            
            else {
            String sql = "update user set id= '"+value1+"',email= '"+value2+"',password= '"+
                    value3+"',name= '"+value4+"',surname= '"+value5+"' where id='"+value1+"' ";
            pst= conn.prepareStatement(sql);
            pst.execute();
            JOptionPane.showMessageDialog(null, "Update");
            UpdateTable();
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
        
    }
    
    public void Delete(){
    conn = mysqlconnect.ConnectDb();
    String sql = "delete from user where id = ?";
        try {
            pst = conn.prepareStatement(sql);
            pst.setString(1, txt_id.getText());
            pst.execute();
            JOptionPane.showMessageDialog(null, "Delete");
            UpdateTable();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    
    }

    
    public void UpdateTable(){
        col_id.setCellValueFactory(new PropertyValueFactory<Users,Integer>("id"));
        col_email.setCellValueFactory(new PropertyValueFactory<Users,String>("email"));
        col_password.setCellValueFactory(new PropertyValueFactory<Users,String>("password"));
        col_name.setCellValueFactory(new PropertyValueFactory<Users,String>("name"));
        col_surname.setCellValueFactory(new PropertyValueFactory<Users,String>("surname"));
        
        listM = mysqlconnect.getDatausers();
        table_users.setItems(listM);
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
       UpdateTable();
    }       
}