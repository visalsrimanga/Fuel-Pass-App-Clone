package controller;

import com.sun.media.jfxmedia.track.AudioTrack;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.media.AudioClip;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import util.Navigations;
import util.Route;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

public class AdminLoginFormController {
    public AnchorPane pnaAdminLoginForm;

    public PasswordField txtPassword;

    private static final String ADMIN_PASSWORD="dep9";
    private static int attempt=3;

    public void txtPasswordOnAction(ActionEvent actionEvent) throws IOException {
        if (!txtPassword.getText().equals(ADMIN_PASSWORD)){

            if (attempt==0){
                Platform.exit();
                return;
            }

            attempt--;

            URL resorce= getClass().getResource("/audio/Security-breach.mp3");
            Media media = new Media(resorce.toString());
            MediaPlayer mediaPlayer = new MediaPlayer(media);
            mediaPlayer.play();

            Alert alert = new Alert(Alert.AlertType.ERROR, "Wrong password. You have "+attempt+" more attempt to try again");
//            attempt--;

            InputStream resourceStream=this.getClass().getResourceAsStream("/images/login.png");
            Image image = new Image(resourceStream);
            ImageView imageView = new ImageView(image);
            imageView.setFitHeight(48);
            imageView.setFitWidth(48);
            alert.setGraphic(imageView);

            alert.setHeaderText("Invalid login Credential");
            alert.setTitle("Access Denied");

            alert.showAndWait();

            txtPassword.requestFocus();
            txtPassword.clear();
            return;
        }

        Navigations.navigate(Route.CONTROL_CENTER);

    }
}
