package Services;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class AdminServices extends Application {
    private double x, y;

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/views/AdminHome.fxml"));
        primaryStage.setScene(new Scene(root));
        //set stage borderless
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
