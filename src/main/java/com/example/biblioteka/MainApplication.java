package com.example.biblioteka;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

    /*
    Repozitorijos sukūrimas: 1)Sukurti githube, nusikopijuoti linką 2)Gitbash desktope 3)git clone linkas 4)Nusikopijuoti .git failą i projekto folderį
    ,ištrinti folderį iš desktopo 5)Projekto folderyje gitbash 6)git add . 7)git commit -m "žinutė" 8)git push

    Toliau:
    1)Refactorinti į main application, pakeisti rezoliuciją, pavadinimą (Prisijungimo langas)
    2)Prie login-view.fxml būtinai pridėti "com.example.biblioteka.controller.LoginController" & stylesheets="@style.css"
    3)Sukurti controller folderį, į jį patalpinti LoginController
    4)login-view.fxml sutvarkyti scene builderį, per code pasivadinti funkcijas turinčius textfieldus/mygtukus (username, password, loginStatus, login, register),
    , LoginController klasėje @FXML private Label loginStatus ir tt... metodu neleis kurt kol nebus validavimo metodų
    5)Sukurti folderį utils su klase Validation, jame regex patternus ir metodus isValidUsername, isValidPassword
    6)Sukurti RegisterController, register-view.fxml, priskirti fx:controller, sutvarkyti scene builderį, sukurti pavadinimus, RegisterController klasėje sukurti @FXML pavadinimams
    */


public class MainApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("login-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 400);
        stage.setTitle("Prisijungimo langas");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}