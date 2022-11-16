package com.example.demo.controllers;

import com.example.demo.Application;
import com.example.demo.DAO.QueryConstant;
import com.example.demo.Model.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;

public class DeleteMenuView {

    @FXML
    private ComboBox<Coffee> coffeeComboBox;

    @FXML
    private ComboBox<PhysicalCondition> conditionComboBox;

    @FXML
    private ComboBox<Pack> packComboBox;

    @FXML
    private ComboBox<Sort> sortComboBox;

    @FXML
    void onBack(ActionEvent event) {
        Application.setScene(Application.getDisplayMenu());
    }

    @FXML
    void onDeleteCoffee(ActionEvent event) {
        Coffee coffee = coffeeComboBox.getValue();
        if(coffee == null)
            return;
        Model.getInstance().deleteCoffee(coffee.getId());
        ShowingOptionController.instance.updateCoffees(QueryConstant.selectAllCoffee);
        coffeeComboBox.setItems(ShowingOptionController.getInstance().coffees);
    }

    @FXML
    void onDeleteCondition(ActionEvent event) {
        PhysicalCondition ph =  conditionComboBox.getValue();
        if(ph == null)
            return;
        Model.getInstance().deleteCondition(ph.getId());
        conditionComboBox.setItems(ShowingOptionController.instance.getPhysicalConditions());
    }

    @FXML
    void onDeletePack(ActionEvent event) {
        Pack pack =  packComboBox.getValue();
        if(pack == null)
            return;
        Model.getInstance().deletePack(pack.getId());
        packComboBox.setItems(ShowingOptionController.getInstance().getPacks());
    }

    @FXML
    void onDeleteSort(ActionEvent event) {
        Sort sort = sortComboBox.getValue();
        if(sort == null)
            return;
        Model.getInstance().deleteSort(sort.getId());
        sortComboBox.setItems(ShowingOptionController.instance.getSorts());
    }


    @FXML
    void initialize() {
        coffeeComboBox.setItems(ShowingOptionController.getInstance().coffees);
        packComboBox.setItems(ShowingOptionController.instance.packs);
        conditionComboBox.setItems(ShowingOptionController.instance.physicalConditions);
        sortComboBox.setItems(ShowingOptionController.instance.sorts);
    }

}
