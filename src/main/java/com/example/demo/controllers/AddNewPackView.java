package com.example.demo.controllers;

import com.example.demo.Application;
import com.example.demo.Model.Model;
import com.example.demo.logger.MyLogger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.util.logging.Level;

public class AddNewPackView {

    @FXML
    private Button addNewSort;

    @FXML
    private Button back;

    @FXML
    private TextField packNameField;

    @FXML
    private TextField packVolumeField;

    @FXML
    void onAddNewSort(ActionEvent event) {
        String packName = packNameField.getText();
        double volume;
        try {
            volume = Double.parseDouble( packVolumeField.getText());
        }
        catch (NumberFormatException e)
        {
            MyLogger.getLogger().log(Level.SEVERE,"Was input wrong volume of pack",e);
            packVolumeField.setText("ВИ ПОМИЛИЛИСЯ В ЖИТТІ");
            return;
        }
        if(packName.equals(""))
        {
            packNameField.setText("ВИ ПОМИЛИЛИСЯ В ЖИТТІ");
            return;
        }
        Model model = Model.getInstance();
        model.insertPack(packName,volume);
        ShowingOptionController.getInstance().initPackBox();
        packVolumeField.setText("");
        packNameField.setText("");
    }

    @FXML
    void onBack(ActionEvent event) {
        Application.setScene(Application.getDisplayMenu());
    }

}
