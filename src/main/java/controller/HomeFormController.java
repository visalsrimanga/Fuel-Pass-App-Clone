package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import util.Navigations;
import util.Route;

import java.io.IOException;
import java.net.URL;

public class HomeFormController {
    public ImageView imgSysLogin;
    public AnchorPane imgContainer;
    public AnchorPane pneContainer;

    public void initialize() throws IOException {

        URL resource=this.getClass().getResource("/view/WelcomeForm.fxml");
        AnchorPane welcomeForm = FXMLLoader.load(resource);
        pneContainer.getChildren().add(welcomeForm);
        AnchorPane.setLeftAnchor(welcomeForm,0.0);
        AnchorPane.setRightAnchor(welcomeForm,0.0);
        AnchorPane.setTopAnchor(welcomeForm,0.0);
        AnchorPane.setBottomAnchor(welcomeForm,0.0);
    }

    public void imgLogoOnMouseClick(MouseEvent mouseEvent) throws IOException {
        pneContainer.getChildren().clear();
        initialize();
    }

    public void imgLoginOnMouseClick(MouseEvent mouseEvent) throws IOException {
        Navigations.navigate(Route.ADMIN_LOGIN);
        /*URL resource=this.getClass().getResource("/view/AdminLoginForm.fxml");
        AnchorPane welcomeForm = FXMLLoader.load(resource);
        pneContainer.getChildren().clear();
        pneContainer.getChildren().add(welcomeForm);
        AnchorPane.setLeftAnchor(welcomeForm,0.0);
        AnchorPane.setRightAnchor(welcomeForm,0.0);
        AnchorPane.setTopAnchor(welcomeForm,0.0);
        AnchorPane.setBottomAnchor(welcomeForm,0.0);*/
    }

    public void imgLoginOnKeyRelease(KeyEvent keyEvent) throws IOException {
        if (keyEvent.getCode()== KeyCode.ENTER||keyEvent.getCode()==KeyCode.SPACE){
            Navigations.navigate(Route.ADMIN_LOGIN);
            /*URL resource=this.getClass().getResource("/view/AdminLoginForm.fxml");
            AnchorPane welcomeForm = FXMLLoader.load(resource);
            pneContainer.getChildren().clear();
            pneContainer.getChildren().add(welcomeForm);
            AnchorPane.setLeftAnchor(welcomeForm,0.0);
            AnchorPane.setRightAnchor(welcomeForm,0.0);
            AnchorPane.setTopAnchor(welcomeForm,0.0);
            AnchorPane.setBottomAnchor(welcomeForm,0.0);*/
        }
    }
}
