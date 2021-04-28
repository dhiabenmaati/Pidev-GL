/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.stage.FileChooser;

/**
 * FXML Controller class
 *
 * @author 21624
 */
public class ImageController implements Initializable {

    @FXML
    private Button btn1;
    @FXML
    private Button btn2;
    @FXML
    private ListView listview;
    
    public void Button1Action(ActionEvent event){
        FileChooser fc= new FileChooser();
        File selectedFile=fc.showOpenDialog(null);
        if(selectedFile!=null){
            listview.getItems().add(selectedFile.getAbsolutePath());
              
      }else{
            System.out.println("file is not valid");
        }
        
        
        
    
    
    
    }
    public void Button2Action(ActionEvent event){
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
