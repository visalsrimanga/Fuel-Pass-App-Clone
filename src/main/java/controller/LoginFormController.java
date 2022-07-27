package controller;

import db.InMemoryDb;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import util.Navigations;
import util.Route;

import java.io.IOException;
import java.util.Objects;

public class LoginFormController {
    public Button btnLogin;
    public TextField txtLoginNIC;
    public Label lblRegisterHere;
    public AnchorPane pnaLoginForm;

    public void btnLoginOnAction(ActionEvent actionEvent) throws IOException {
        String nic = txtLoginNIC.getText();
        if (!RegisterFormController.isValidNIC(nic)) {
            txtLoginNIC.requestFocus();
            txtLoginNIC.selectAll();
            new Alert(Alert.AlertType.INFORMATION, "Please enter valid NIC").showAndWait();
            return;
        } else if (InMemoryDb.findUser(nic) == null) {
            txtLoginNIC.requestFocus();
            txtLoginNIC.selectAll();
            new Alert(Alert.AlertType.INFORMATION, "Unregistered NIC, Please register first!").showAndWait();
            return;
        }

        Navigations.navigate(Route.USER_DASHBOARD);
    }
    public void lblRegisterHereSetOnAction(MouseEvent mouseEvent) throws IOException {
        Navigations.navigate(Route.REGISTRATION);

    }
}
