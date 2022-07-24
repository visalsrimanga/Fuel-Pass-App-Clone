package controller;

import db.InMemoryDb;
import db.User;
import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;

public class ControlCenterFormController {


    public AnchorPane pnaControlCenter;
    public TableView<User> tblUsers;
    public TableColumn colNIC;
    public TableColumn colFirstName;
    public TableColumn colLastName;
    public TableColumn colAddress;
    public TableColumn colOption;

    public void initialize(){

        Platform.runLater(pnaControlCenter::requestFocus);
        ObservableList<User> olUsersList = tblUsers.getItems();
        for (User user: InMemoryDb.getUserDatabase()){
            user.getBtnRemove().setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent actionEvent) {
                    InMemoryDb.removeUserByObject(user);
                    olUsersList.remove(user);
                }
            });
            olUsersList.add(user);
        }
        colNIC.setCellValueFactory(new PropertyValueFactory<>("nic"));
        colFirstName.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        colLastName.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        colAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        colOption.setCellValueFactory(new PropertyValueFactory<>("btnRemove"));
    }
}
