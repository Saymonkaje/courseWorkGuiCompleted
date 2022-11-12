package com.example.demo.controllers;

import com.example.demo.Application;
import com.example.demo.Model.Model;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.ResourceBundle;

public class MainMenuController {


    public void onShowCoffeeInfo(ActionEvent actionEvent) {
        Application.setScene(Application.getDisplayMenu());
    }

    public void onExit(ActionEvent actionEvent) {
        System.exit(0);
    }

    @FXML
    void initialize() {
        Model model = Model.getInstance();
    }

}
