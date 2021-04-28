/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package art;

import java.io.IOException;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import utils.DataSource;

/**
 *
 * @author 21624
 */
public class Art extends Application {

    private Stage primaryStage;
    
    @Override
    public void start(Stage primaryStage) throws IOException {
     this.primaryStage = primaryStage;
        this.primaryStage.setTitle("PIART");
        
       //Object parentPage = FXMLLoader.load(getClass().getResource("/view/AfficherProduit.fxml"));
         //Object parentPage = FXMLLoader.load(getClass().getResource("/view/AfficherCategorie.fxml"));
       // Object parentPage = FXMLLoader.load(getClass().getResource("/view/FrontProduit.fxml"));
         Object parentPage = FXMLLoader.load(getClass().getResource("/view/Image.fxml"));
        Scene scene = new Scene((Parent) parentPage);
        this.primaryStage.setScene(scene);
        this.primaryStage.show();
    }

       
        StackPane root = new StackPane();
        
       // root.getChildren().add(btn);
        
        Scene scene = new Scene(root, 300, 250);
        
       // primaryStage.setTitle("Hello World!");
       // primaryStage.setScene(scene);
       // primaryStage.show();
    
        

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
          DataSource ds1=DataSource.getInstance();
         
    
    
}
}
