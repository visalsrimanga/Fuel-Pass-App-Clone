import db.InMemoryDb;
import db.User;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.File;
import java.io.IOException;

public class AppInitializer extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        Parent container = FXMLLoader.load(this.getClass().getResource("/view/SplashForm.fxml"));
        Scene scene = new Scene(container);
        primaryStage.setScene(scene);
        primaryStage.initStyle(StageStyle.TRANSPARENT);
        primaryStage.initStyle(StageStyle.UNDECORATED);
        primaryStage.setTitle("National Fuel Pass");
        primaryStage.show();

    }
}
