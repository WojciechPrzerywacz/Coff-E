package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Ekspres do kawy Coff-E Ultra Max");
        Scene scene = new Scene(root, 683, 683);
        primaryStage.setScene(scene);

        // For no frame and background
        //primaryStage.initStyle(StageStyle.TRANSPARENT);
        scene.setFill(Color.BLACK);

        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
