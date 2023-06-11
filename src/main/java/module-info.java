module com.example.atminterface {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.logging;
    requires java.sql;
    requires java.desktop;

    opens com.example.atminterface to javafx.fxml;
    exports com.example.atminterface;
    opens com.example.atminterface.controller to javafx.fxml;
    exports com.example.atminterface.controller;

}