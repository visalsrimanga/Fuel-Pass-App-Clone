package controller;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import util.Navigations;
import util.Route;

import java.io.IOException;

public class WelcomeFormController {
    public Button btnLogin;
    public Button btnRegister;
    public AnchorPane pneWelcome;

    public void btnRegisterOnAction(ActionEvent actionEvent) throws IOException {
        Navigations.navigate(Route.REGISTRATION);
        /*AnchorPane registerForm= FXMLLoader.load(this.getClass().getResource("/view/RegisterForm.fxml"));
        AnchorPane pneContainer=(AnchorPane) pneWelcome.getParent();
        pneContainer.getChildren().clear();
        pneContainer.getChildren().add(registerForm);
        AnchorPane.setLeftAnchor(registerForm,0.0);
        AnchorPane.setRightAnchor(registerForm,0.0);
        AnchorPane.setTopAnchor(registerForm,0.0);
        AnchorPane.setBottomAnchor(registerForm,0.0);*/

    }

    public void btnLoginOnAction(ActionEvent actionEvent) throws IOException {
        Navigations.navigate(Route.LOGIN);
        /*AnchorPane loginForm = FXMLLoader.load(this.getClass().getResource("/view/LoginForm.fxml"));
        AnchorPane pneContainer =(AnchorPane) pneWelcome.getParent();
        pneContainer.getChildren().clear();
        pneContainer.getChildren().add(loginForm);
        AnchorPane.setLeftAnchor(loginForm,0.0);
        AnchorPane.setRightAnchor(loginForm,0.0);
        AnchorPane.setTopAnchor(loginForm,0.0);
        AnchorPane.setBottomAnchor(loginForm,0.0);*/
    }
}
