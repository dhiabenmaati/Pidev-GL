/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import entity.blog;
import entity.reclamation;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.embed.swing.SwingFXUtils;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javax.imageio.ImageIO;
import service.blogService;
import service.reclamationService;

/**
 * FXML Controller class
 *
 * @author houssem
 */
public class AddPublicationController implements Initializable {

    @FXML
    private TextArea title;
    @FXML
    private TextArea description;
    @FXML
    private Button ajouter;
    @FXML
    private Button blog;
    @FXML
    private Button reclamation;
    @FXML
    private Button choose_image;
    @FXML
    private Label file;
    @FXML
    private ImageView img;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
            long millisecond=System.currentTimeMillis();  
            java.sql.Timestamp date=new java.sql.Timestamp(millisecond);  

    blog.setOnAction(event -> {

            try {
                Parent page1 = FXMLLoader.load(getClass().getResource("/view/AccueilBlog.fxml"));
                Scene scene = new Scene(page1);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
            } catch (IOException ex) {
                Logger.getLogger(AccueilController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
    reclamation.setOnAction(event -> {

            try {
                Parent page1 = FXMLLoader.load(getClass().getResource("/view/AddReclamation.fxml"));
                Scene scene = new Scene(page1);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
            } catch (IOException ex) {
                Logger.getLogger(AccueilController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }); 
    choose_image.setOnAction(event -> {
            FileChooser fc = new FileChooser();
            File selectedFile = fc.showOpenDialog(null);
                    try {
            BufferedImage bufferedImage = ImageIO.read(selectedFile);
            Image image = SwingFXUtils.toFXImage(bufferedImage, null);
            img.setImage(image);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
            if (selectedFile != null) {
                file.setText(selectedFile.getName());
            
        }
        });

    ajouter.setOnAction(event -> {

                            Image image1 = img.getImage();
            blog p = null;
                try {
                    p = new blog(title.getText(), description.getText(), ConvertFileImage(image1), 5);
                } catch (IOException ex) {
                    Logger.getLogger(AddPublicationController.class.getName()).log(Level.SEVERE, null, ex);
                }
            blogService rService=new blogService();
            rService.ajouterBlog(p);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information");
        alert.setHeaderText(null);
        alert.setContentText("Publication Ajouter");
        alert.show();
        title.setText("");
        description.setText("");
        file.setText("");


    });
    }
        public String ConvertFileImage(Image image) throws IOException{

            File outputFile = new File("D:/MY folder/tsmm317/Cours");
            BufferedImage bImage = SwingFXUtils.fromFXImage(image, null);
            ImageIO.write(bImage, "png", outputFile);

                    return file.getText();
        }


    
}
