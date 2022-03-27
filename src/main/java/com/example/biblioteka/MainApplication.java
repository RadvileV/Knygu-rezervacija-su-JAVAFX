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
    1)Sukurti db
    2)Susikurti libs, pridėti mysqlconnector
    3)Sukurti folderį model, jame Book, BookDAO, User, UserDao, Category, CategoryDAO, susikurti reikiamus dalykus jose (be DAO klasių)
    4)Refactorinti į main application, pakeisti rezoliuciją, pavadinimą (Prisijungimo langas)
    5)Prie login-view.fxml būtinai pridėti "com.example.biblioteka.controller.LoginController" & stylesheets="@style.css"
    6)Sukurti controller folderį, į jį patalpinti LoginController
    7)login-view.fxml sutvarkyti scene builderį, per code pasivadinti funkcijas turinčius textfieldus/mygtukus (username, password, loginStatus, login, register),
    , LoginController klasėje @FXML private Label loginStatus ir tt... metodu neleis kurt kol nebus validavimo metodų
    8)Sukurti folderį utils su klase Validation, jame regex patternus ir metodus isValidUsername, isValidPassword
    9)Sukurti RegisterController, register-view.fxml, priskirti fx:controller, sutvarkyti scene builderį, sukurti pavadinimus,
    (username, password, confirmPassword, isAdminCheckBox, register, registrationStatus) RegisterController klasėje sukurti @FXML pavadinimams
    10)utils folderyje sukurti BCrypt bei BCryptPassword klases, o model folderyje UserSingleton ir patalpinti kodą jose
    11)Sukurti UserDAO metodus create, getbcryptpassword
    12)Sutvarkyti pirma login lange registruotis mygtuka, tada registracijos langa, tada gryzti prie login

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

    /*
    CREATE TABLE Users (
    id INTEGER PRIMARY KEY AUTO_INCREMENT,
    username VARCHAR(32),
    password VARCHAR(255),
    email VARCHAR(64),
    admin BOOLEAN
    );

    CREATE TABLE Categories (
	categoryId INTEGER PRIMARY KEY AUTO_INCREMENT,
    categoryName VARCHAR(255)
    );

    CREATE TABLE Books (
    id INTEGER PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(255),
    summary VARCHAR(255),
    ISBN VARCHAR(255),
    picture BLOB,
    bookPages INTEGER,
    categoryId INTEGER,
    FOREIGN KEY (categoryId) REFERENCES Categories(categoryId)
    );
    */

