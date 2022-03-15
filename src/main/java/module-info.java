module com.example.biblioteka {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;


    opens com.example.biblioteka to javafx.fxml;
    exports com.example.biblioteka;
    exports com.example.biblioteka.controller;
    opens com.example.biblioteka.controller to javafx.fxml;
}