package com.example.biblioteka.controller;

import com.example.biblioteka.utils.Validation;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class LoginController {

    @FXML
    private Label loginStatus;
    @FXML
    private TextField username;
    @FXML
    private PasswordField password;

    @FXML
    public void onLoginButtonClick() {
        String username2 = username.getText();
        String password2 = password.getText();
    }
}