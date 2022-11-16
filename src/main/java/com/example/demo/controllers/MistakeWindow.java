package com.example.demo.controllers;

import com.example.demo.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;

public class MistakeWindow {

    public static Stage stage;
    private static String errorText;
    @FXML
    private Label errorTextField;

    public static void openMistakeWindow(String errorText)
    {
        MistakeWindow.errorText = errorText;
        Parent root;
        FXMLLoader fxmlLoader = new FXMLLoader(Application.class.getResource("mistakeWindow.fxml"));
        try {
            root = fxmlLoader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        stage = new Stage();
        stage.setTitle("ВАМ СЛІД ПОДУМАТИ НАД СВОЄЮ ПОВЕДІНКОЮ");
        stage.setScene(new Scene(root));
        stage.show();
    }

    @FXML
    void initialize()
    {
        errorTextField.setText(errorText);
    }
    @FXML
    void onOk(ActionEvent event) {
        stage.close();
    }

}
