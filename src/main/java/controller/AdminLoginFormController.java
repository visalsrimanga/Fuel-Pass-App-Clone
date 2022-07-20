package controller;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;

import javax.print.attribute.standard.Media;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

public class AdminLoginFormController {
    public AnchorPane pnaAdminForm;

    public PasswordField txtPassword;

    private static final String ADMIN_PASSWORD="dep9@admin";
    private static int attempt=3;

    public void txtPasswordOnAction(ActionEvent actionEvent) throws IOException {
        if (!txtPassword.getText().equals(ADMIN_PASSWORD)){

            if (attempt==0){
                Platform.exit();
                return;
            }

            attempt--;

            URL resorce= this.getClass().getResource("/audio/securityalarm.mp3");
//            Media media = new Media(resorce.toString());
//            MediaPlayer mediaPlayer = new MediaPlayer(media);
//            mediaPlayer.play();

            //add web dependency to work this


            Alert alert = new Alert(Alert.AlertType.ERROR, "Wrong password."+attempt+" You have attempt to try again");
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

        AnchorPane container=FXMLLoader.load(this.getClass().getResource("/view/ControlCenterForm.fxml"));
        AnchorPane pneContainer = (AnchorPane) pnaAdminForm.getParent();
        pneContainer.getChildren().clear();
        pneContainer.getChildren().add(container);
        AnchorPane.setLeftAnchor(pneContainer,0.0);
        AnchorPane.setRightAnchor(pneContainer,0.0);
        AnchorPane.setTopAnchor(pneContainer,0.0);
        AnchorPane.setBottomAnchor(pneContainer,0.0);


    }
}
