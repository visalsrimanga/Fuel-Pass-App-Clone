package controller;

import db.InMemoryDb;
import db.User;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.*;
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
    public TableColumn colQuota;
    public TextField txtSearch;
    public Button btnSearch;
    public Spinner<Integer> txtQuota;
    public Button btnUpdateQuota;

    public void initialize(){

        Platform.runLater(pnaControlCenter::requestFocus);
        txtQuota.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0,20,16));

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

        tblUsers.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

        /*pnaControlCenter.widthProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observableValue, Number prev, Number current) {
                if (prev.doubleValue() == 0.0) return;
                double diff= current.doubleValue()- prev.doubleValue();
                double prefWidthAddress = colAddress.getWidth()+diff/2;
                if (prefWidthAddress < 120) prefWidthAddress=120;
                colAddress.setPrefWidth(prefWidthAddress);

                double prefWidthOption = colOption.getWidth()+diff/2;
                if (prefWidthOption < 50) prefWidthOption=50;
                colOption.setPrefWidth(prefWidthOption);
            }
        });*/

        //we can chane the column sizes retained by scene builder. Don't need any coding for that

        colNIC.setCellValueFactory(new PropertyValueFactory<>("nic"));
        colQuota.setCellValueFactory(new PropertyValueFactory<>("quota"));
        colFirstName.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        colLastName.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        colAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        colOption.setCellValueFactory(new PropertyValueFactory<>("btnRemove"));

    }

    public void btnSearchOnAction(ActionEvent actionEvent) {
    }

    public void btnUpdateQuotaOnAction(ActionEvent actionEvent) {
        ObservableList<User> selectedItems = tblUsers.getSelectionModel().getSelectedItems();

        if (txtQuota.getValue()<0) return;
        for (User selectedItem : selectedItems) {
            selectedItem.setQuota(txtQuota.getValue());
        }
        tblUsers.refresh();
    }
}
