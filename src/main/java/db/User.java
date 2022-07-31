package db;

import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;

import java.io.Serializable;

public class User implements Serializable {
    private String nic;
    private String firstName;
    private String lastName;
    private String address;
    private double quota;
    private Button btnRemove;

    public double getQuota() {
        return quota;
    }

    public void setQuota(int quota) {
        this.quota = quota;
    }

    public User() {
    }

    public User(String nic, String firstName, String lastName, String address, int quota, Button btnRemove) {
        this.nic = nic;
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.quota = quota;
        this.btnRemove = btnRemove;
    }

    public String getNic() {
        return nic;
    }

    public void setNic(String nic) {
        this.nic = nic;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Button getBtnRemove() {
        return btnRemove;
    }

    public void setBtnRemove(Button btnRemove) {
        this.btnRemove = btnRemove;
    }
}
