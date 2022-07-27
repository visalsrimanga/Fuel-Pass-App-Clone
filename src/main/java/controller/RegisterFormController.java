package controller;

import db.InMemoryDb;
import db.User;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventType;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import util.Navigations;
import util.Route;

import java.io.IOException;

public class RegisterFormController {
    public Button btnRegister;
    public TextField txtNIC;
    public TextField txtFirstName;
    public TextField txtLastName;
    public TextField txtAddress;
    public Label lblNicErrorMessage;
    public Label lblLoginHere;
    public AnchorPane pnaRegistration;
    public Label lblStarNicNumber;
    public Label lblStarFirstName;
    public Label lblStarAddress;

    public static boolean isValidNIC(String input) {
        if (input.length() != 10) return false;
        if (!(input.endsWith("v") || input.endsWith("V"))) return false;
        if (!input.substring(0, 9).matches("\\d+")) return false;
        return true;
    }
    private void setDisable(boolean disable){
        txtFirstName.setDisable(disable);
        txtLastName.setDisable(disable);
        txtAddress.setDisable(disable);
    }
    private void setEnableRegisterButton(){
        if (txtNIC.getText().isBlank() || txtFirstName.getText().isBlank() || txtAddress.getText().isBlank()){
            btnRegister.setDisable(true);
        } else {
            btnRegister.setDisable(false);
        }
    }
    public boolean isName(String input){
        char[] chars=input.toCharArray();
        for (char aLetter:chars){
            if (!Character.isLetter(aLetter) && aLetter != ' ') return false;
        }
        return true;
    }

    public void btnRegisterOnAction(ActionEvent actionEvent) throws IOException {
        if (!isName(txtFirstName.getText())) {
            txtFirstName.requestFocus();
            txtFirstName.selectAll();
            new Alert(Alert.AlertType.INFORMATION,"Invalid Name!").showAndWait();
            return;
        } else if (!txtLastName.getText().isBlank() && !isName(txtLastName.getText())) {
            txtLastName.requestFocus();
            txtLastName.selectAll();
            new Alert(Alert.AlertType.INFORMATION,"Invalid Name!").showAndWait();
            return;
        } else if (InMemoryDb.isRegisteredUser(txtNIC.getText())) {
            txtNIC.requestFocus();
            txtNIC.selectAll();
            new Alert(Alert.AlertType.INFORMATION,"Already registered user, Please check your NIC and try again!").showAndWait();
            return;
        }

        InMemoryDb.registerUser(new User(txtNIC.getText(), txtFirstName.getText(),
                txtLastName.getText(), txtAddress.getText(), new Button("Remove")));
        txtNIC.clear();
        txtFirstName.clear();
        txtLastName.clear();
        txtAddress.clear();
        new Alert(Alert.AlertType.INFORMATION,"Registration Successful! ✅ You will direct to the login screen!").showAndWait();

        Navigations.navigate(Route.LOGIN);

    }

    public void lblLoginHereOnMouseClicked(MouseEvent mouseEvent) throws IOException {
        AnchorPane loginContainer= FXMLLoader.load(this.getClass().getResource("/view/LoginForm.fxml"));
        AnchorPane container = (AnchorPane) pnaRegistration.getParent();
        container.getChildren().clear();
        container.getChildren().add(loginContainer);
        AnchorPane.setRightAnchor(loginContainer,0.0);
        AnchorPane.setLeftAnchor(loginContainer,0.0);
        AnchorPane.setTopAnchor(loginContainer,0.0);
        AnchorPane.setBottomAnchor(loginContainer,0.0);

    }

    public void initialize(){
        txtNIC.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue,
                                String oldText, String currentText) {
                if (currentText.isBlank()){
                    lblNicErrorMessage.setText("Please Enter a Valid NIC number to proceed.");
                    lblNicErrorMessage.setTextFill(Color.BLUE);
                    lblStarNicNumber.setVisible(true);
                    setDisable(true);
                    btnRegister.setDisable(true);
                }else {
                    if (isValidNIC(currentText)) {
                        lblNicErrorMessage.setText("Valid NIC. ✅");
                        lblNicErrorMessage.setTextFill(Color.GREEN);
                        lblStarNicNumber.setVisible(false);
                        setDisable(false);
                        setEnableRegisterButton();
                    } else {
                        lblNicErrorMessage.setText("Invalid NIC. ❌");
                        lblNicErrorMessage.setTextFill(Color.RED);
                        lblStarNicNumber.setVisible(false);
                        setDisable(true);
                        btnRegister.setDisable(true);

                    }
                }
            }
        });

        txtFirstName.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String s, String t1) {
                if (!(txtFirstName.getText().isBlank())){
                    lblStarFirstName.setVisible(false);
                } else {
                    lblStarFirstName.setVisible(true);
                }
                setEnableRegisterButton();
            }
        });

        txtAddress.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String s, String t1) {
                if (!(txtAddress.getText().isBlank())){
                    lblStarAddress.setVisible(false);
                } else {
                    lblStarAddress.setVisible(true);
                }
                setEnableRegisterButton();
            }
        });
    }


}
