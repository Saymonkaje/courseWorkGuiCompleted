module com.example.demo {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.logging;
    requires java.sql;

    opens com.example.demo to javafx.fxml;
        exports com.example.demo;
    exports com.example.demo.Model;
    opens com.example.demo.Model to javafx.fxml;
    exports com.example.demo.logger;
    opens com.example.demo.logger to javafx.fxml;
    exports com.example.demo.controllers;
    opens com.example.demo.controllers to javafx.fxml;
    exports com.example.demo.customCells;
    opens com.example.demo.customCells to javafx.fxml;
}