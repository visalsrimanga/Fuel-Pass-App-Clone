package controller;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;

public class SplashFormController {
    public Label lblLoading;

    public void initialize(){
        Timeline timeline = new Timeline();
        KeyFrame k1=new KeyFrame(Duration.millis(250), new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                lblLoading.setText("Loading data..");
            }
        });
        KeyFrame k2=new KeyFrame(Duration.millis(500), new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                lblLoading.setText("Loading plugins..");
            }
        });
        KeyFrame k3=new KeyFrame(Duration.millis(750), new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                lblLoading.setText("Connecting with database..");
            }
        });

        KeyFrame k4=new KeyFrame(Duration.millis(1000), new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                lblLoading.setText("Setting up UI..");
                try {
                    Parent container=FXMLLoader.load(this.getClass().getResource("/view/HomeForm.fxml"));
                    Stage stage = new Stage();
                    Scene scene = new Scene(container);
                    stage.setTitle("National Fuel Pass App");
                    stage.setScene(scene);
                    stage.show();
                    lblLoading.getScene().getWindow().hide();

                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        timeline.getKeyFrames().addAll(k1,k2,k3,k4);
        timeline.playFromStart();
    }
}
