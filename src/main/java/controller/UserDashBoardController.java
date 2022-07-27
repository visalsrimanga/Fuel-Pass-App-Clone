package controller;

import javafx.application.Platform;
import javafx.scene.layout.AnchorPane;

public class UserDashBoardController {
    public AnchorPane pneUserProfile;

    public void initialize(){
        Platform.runLater(pneUserProfile::requestFocus);

    }
}
