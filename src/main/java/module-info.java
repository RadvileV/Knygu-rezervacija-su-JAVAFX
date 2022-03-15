module com.example.biblioteka {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;


    opens com.example.biblioteka to javafx.fxml;
    exports com.example.biblioteka;
}