package org.example.finalproject;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class LoginController {
    public LoginController() {

    }

    @FXML
    public Button sLoginButton;
    @FXML
    public Button iLoginButton;
    @FXML
    public Button aLoginButton;
    @FXML
    public TextField username;;
    @FXML
    public TextField password;

    public void studentLogin(ActionEvent event) {
        Student student = new Student();
        student.showMenu();
    }

    public void instructorLogin(ActionEvent event) {
        Instructor instructor = new Instructor();
        instructor.showMenu();
    }

    public void adminLogin(ActionEvent event) {
        Admin admin = new Admin();
        admin.showMenu();
    }

}
