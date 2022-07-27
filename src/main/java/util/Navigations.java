package util;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;

public class Navigations {
    private static AnchorPane pneContainer;

    public static void init(AnchorPane pneContainer){
        Navigations.pneContainer=pneContainer;
    }

    public static void navigate(Route route) throws IOException {
        pneContainer.getChildren().clear();
        URL resource;
        switch (route){
            case WELCOME:
                resource=Navigations.class.getResource("/view/WelcomeForm.fxml");
                break;
            case REGISTRATION:
                resource=Navigations.class.getResource("/view/RegisterForm.fxml");
                break;
            case LOGIN:
                resource=Navigations.class.getResource("/view/LoginForm.fxml");
                break;
            case ADMIN_LOGIN:
                resource=Navigations.class.getResource("/view/AdminLoginForm.fxml");
                break;
            case USER_DASHBOARD:
                resource=Navigations.class.getResource("/view/UserDashBoard.fxml");
                break;
            default:
                resource=Navigations.class.getResource("/view/ControlCenterForm.fxml");
        }
        Parent container = FXMLLoader.load(resource);
        pneContainer.getChildren().add(container);
        AnchorPane.setLeftAnchor(container,0.0);
        AnchorPane.setRightAnchor(container,0.0);
        AnchorPane.setTopAnchor(container,0.0);
        AnchorPane.setBottomAnchor(container,0.0);
    }
}
