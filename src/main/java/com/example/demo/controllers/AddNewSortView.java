package com.example.demo.controllers;

import java.net.URL;
import java.util.ResourceBundle;

import com.example.demo.Application;
import com.example.demo.Model.Model;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class AddNewSortView {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button addNewSort;

    @FXML
    private Button back;

    @FXML
    private TextField textField;

    @FXML
    void onAddNewSort(ActionEvent event) {
        String newSort = textField.getText();
        if(newSort.equals(""))
        {
            textField.setText("ВИ ПОМИЛИЛИСЯ В ЖИТТІ");
            return;
        }
        Model model = Model.getInstance();
        model.insertSort(newSort);
        ShowingOptionController.getInstance().initSortBox();
        textField.setText("");
    }

    @FXML
    void onBack(ActionEvent event) {
        Application.setScene(Application.getDisplayMenu());
    }

    @FXML
    void initialize() {
    }

}
