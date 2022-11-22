package com.example.demo.controllers;

import com.example.demo.Application;
import com.example.demo.DAO.DBManager;
import com.example.demo.Model.Service;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public class MainMenuController {


    public void onShowCoffeeInfo(ActionEvent actionEvent) {
        Application.setScene(Application.getDisplayMenu());
    }

    public void onExit(ActionEvent actionEvent) {
        DBManager.getInstance().close();
        System.exit(0);
    }

    @FXML
    void initialize() {
        Service service = Service.getInstance();
    }

}
