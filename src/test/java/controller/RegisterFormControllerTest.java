package controller;

//import static org.junit.jupiter.api.Assertions.*;

class RegisterFormControllerTest {
    public static void main(String[] args) {
        RegisterFormController ctrl = new RegisterFormController();
        assert (ctrl.isName("kasub dfa"));
        assert (ctrl.isName("Kasub123")) == false;
    }

}