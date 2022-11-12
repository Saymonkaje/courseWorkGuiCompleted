package com.example.demo.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.*;
import java.util.logging.Level;

import com.example.demo.Application;
import com.example.demo.DAO.DBManager;
import com.example.demo.Model.Model;
import com.example.demo.commands.*;
import com.example.demo.commands.searching.SearchCommand;
import com.example.demo.commands.showing.ShowCoffeeCommand;
import com.example.demo.logger.MyLogger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class MainMenuController {

    public VBox mainMenu;
    Map<Button,Command> commands;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button exit;

    @FXML
    private Button searchCoffee;

    @FXML
    private Button showCoffeeInfo;


    public void onShowCoffeeInfo(ActionEvent actionEvent) {
        Application.setScene(Application.getDisplayMenu());
    }

    public void onSearch(ActionEvent actionEvent) {
    }

    public void onExit(ActionEvent actionEvent) {
        commands.get(exit).execute();
    }

    @FXML
    void initialize() {
        Model model = Model.getInstance();
        commands = new HashMap<>();
        commands.put(showCoffeeInfo,new ShowCoffeeCommand(model));
        commands.put(searchCoffee,new SearchCommand(model));
        commands.put(exit,new ExitCommand(model));

    }


}
