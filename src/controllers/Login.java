/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;


import java.io.IOException;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import java.sql.SQLException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javax.swing.JOptionPane;
import Services.mysqlconnect;
import java.awt.RenderingHints.Key;
import java.util.Base64;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.util.prefs.Preferences;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

/**
 *
 * @author alabe
 */
public class Login  {
   

	@FXML private TextField emaill;
	@FXML private PasswordField passwordd;
        @FXML private TextField name;
	@FXML private TextField surname;
	@FXML private Button loginBtn;
        @FXML private Button SignUpBtn;
       @FXML private Button GoToSignUpBtn;
        
        // account verif 
        @FXML private TextField Verif;
        @FXML private Button VerifBtn;
        @FXML private Button btnVerifPage;
        
        Connection conn =null;
        ResultSet rs = null;
        PreparedStatement pst = null;
        MailDeliveryController mail = new MailDeliveryController();
        
        public Preferences prefs;
        String defaultValue = "default string";
        
        private static final String ALGO = "AES";
        private static final byte[] keyValue = new byte[16];

        
        // login
        
	public void Login() throws IOException, SQLException
	{
                conn = mysqlconnect.ConnectDb();
		String user = this.emaill.getText();
		String password = this.passwordd.getText();
             
		
		if(user == null || user.trim().equals("")) 
		{
			JOptionPane.showMessageDialog(null, "Please Type Email");
		}
		if(password == null || password.trim().equals("")) 
		{
			JOptionPane.showMessageDialog(null, "Please Type Password");
		}
		
                String sql = "Select * from user where email=? and password=?";
		PreparedStatement pst;
                
		try
		{
                    pst = conn.prepareStatement(sql);
                    pst.setString(1, user);
                    pst.setString(2, encrypt(password));
                    rs = pst.executeQuery();
			if( rs.next() )
			{       
                             if(   rs.getString("status").equals("active")){JOptionPane.showMessageDialog(null, "Welcome " + rs.getString("name")); 
                             prefs = Preferences.userRoot().node(this.getClass().getName());
                             prefs.putInt("user", rs.getInt("id"));
                             System.out.println(prefs.get("user", defaultValue));
                    
                             try {
            Parent page = FXMLLoader.load(getClass().getResource("/views/AdminHome.fxml"));
            Scene scene = new Scene(page);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.show();
            } catch (IOException ex) {
            Logger.getLogger(controllers.UsersController.class.getName()).log(Level.SEVERE, null, ex); };
                             
                             }
                             else{ JOptionPane.showMessageDialog(null, "Please Verify Your Email Before signIn"); }
                        }
                        else
			{
				JOptionPane.showMessageDialog(null, "No User Found");
			}
                }      
                catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }

}
        
        //account creation and send mail
        public void SignUp (){    
        conn = mysqlconnect.ConnectDb();
        String sql = "insert into user (email,password,name,surname,status,random)values(?,?,?,?,?,? )";
        try {

            String Random = Random_String() ;
            pst = conn.prepareStatement(sql);
            pst.setString(1, this.emaill.getText());
            pst.setString(2, encrypt(this.passwordd.getText()));
            pst.setString(3, this.name.getText());
            pst.setString(4, this.surname.getText());
            pst.setString(5, "inactive");
            pst.setString(6, Random);
            
            
            if (emaill.getText().length()<3 || passwordd.getText().length()<8 || name.getText().length()<3 || surname.getText().length()<3 ){ 
            JOptionPane.showMessageDialog(null, "Error in form ... Please verify");
            }
            
            else{
                pst.execute();
                mail.SendMail(Random, this.emaill.getText());  // send mail
            JOptionPane.showMessageDialog(null, "Account Created successfully");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }
           
        // account verification
        
        public void Verif() throws IOException, SQLException
	{
                conn = mysqlconnect.ConnectDb();
		String VerifCode = this.Verif.getText();

		if(VerifCode == null || VerifCode.trim().equals("")) 
		{ JOptionPane.showMessageDialog(null, "Please Type Verification code recived by mail"); }
		
                String sql = "Select * from user where random=?";
		PreparedStatement pst;
                
		try
		{
                    pst = conn.prepareStatement(sql);
                    pst.setString(1, VerifCode);
                    rs = pst.executeQuery();

			if(	rs.next())
			{
                                String sql1 = "update user set status='active' where random='"+VerifCode+"' ";
                                pst= conn.prepareStatement(sql1);
                                pst.execute();
				JOptionPane.showMessageDialog(null, "Your account is now verified");
			}
                        else
			{
				JOptionPane.showMessageDialog(null, "Please Check your verification code");
			}
                }      
                catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
            }
        }
        
        // get random string for account verif
               public String Random_String() {
        String uuid = UUID.randomUUID().toString();
        return uuid;
    }
               
               
        // Go To Si
        public void GoToSignUp() {
        try {
    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/views/SignUp.fxml"));
            Parent root = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));  
            stage.show();
    } catch(Exception e) {
       e.printStackTrace();
      }
 }
        
        public void handleClicks(ActionEvent actionEvent) 
        {
        btnVerifPage.setOnAction(event -> {
        try {
            Parent page1 = FXMLLoader.load(getClass().getResource("/views/SignUp.fxml"));
            Scene scene = new Scene(page1);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
            } catch (IOException ex) {
            Logger.getLogger(controllers.UsersController.class.getName()).log(Level.SEVERE, null, ex); }});
}
        
        public static String encrypt(String pwd) {
    String encodedPwd = "";
    try {
        SecretKeySpec key = generateKey();
        Cipher c = Cipher.getInstance(ALGO);
        c.init(Cipher.ENCRYPT_MODE, key);
        byte[] encVal = c.doFinal(pwd.getBytes());
        encodedPwd = Base64.getEncoder().encodeToString(encVal);

    } catch (Exception e) {

        e.printStackTrace();
    }
    return encodedPwd;

}
        
        private static SecretKeySpec generateKey() {
    SecretKeySpec key = new SecretKeySpec(keyValue, ALGO);
    return key;
}

        }