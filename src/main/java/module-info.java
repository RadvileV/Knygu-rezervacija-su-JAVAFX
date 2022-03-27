module com.example.biblioteka {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;
    requires java.sql;
    requires java.naming;


    opens com.example.biblioteka to javafx.fxml;
    exports com.example.biblioteka;
    exports com.example.biblioteka.controller;
    opens com.example.biblioteka.controller to javafx.fxml;
    exports com.example.biblioteka.model;
    opens  com.example.biblioteka.model to javafx.fxml;
}