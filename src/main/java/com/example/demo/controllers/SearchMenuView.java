package com.example.demo.controllers;

import com.example.demo.Application;
import com.example.demo.DAO.QueryConstant;
import com.example.demo.Model.Model;
import com.example.demo.Model.Pack;
import com.example.demo.Model.PhysicalCondition;
import com.example.demo.Model.Sort;
import com.example.demo.customCells.PromptCell;
import com.example.demo.logger.MyLogger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.logging.Level;

public class SearchMenuView {

    private  static Stage stage;
    ObservableList<String> comparisonSymbol;
    private static SearchMenuView instance;

    public static SearchMenuView getInstance() {
        if(instance==null)
            openSearchMenu();
        return instance;
    }

    public static void openSearchMenu()
    {
        Parent root;
        FXMLLoader fxmlLoader = new FXMLLoader(Application.class.getResource("searchMenuView.fxml"));
        try {
            root = fxmlLoader.load();
        } catch (IOException e) {
            MyLogger.getLogger().log(Level.SEVERE,"error loading searching scene");
            throw new RuntimeException(e);
        }
        stage = new Stage();
        stage.setTitle("Searching menu");
        stage.setScene(new Scene(root));
        stage.show();
    }
    @FXML
    void onCloseMenu(ActionEvent event) {
        stage.close();
    }
    @FXML
    void onDoSearch(ActionEvent event) {
        Sort sort = sortComboBox.getValue();
        Pack pack = packComboBox.getValue();
        PhysicalCondition physicalCondition = conditionComboBox.getValue();
        String weightComparison = weightComboBox.getValue();
        String priceComparison = priceComboBox.getValue();
        String weight = weightTextField.getText();
        String price = priceTextField.getText();
        String query;
        if(weight.equals(""))
            weightComparison=null;
        else if(weightComparison!=null)
            weightComparison+=weight;
        if(price.equals(""))
            priceComparison=null;
        else if(priceComparison!=null)
            priceComparison += price;
        if(sort==null && pack==null&&physicalCondition==null&&priceComparison==null&&weightComparison==null)
            query = QueryConstant.selectAllCoffee;
        else
            query =  Model.getInstance().searchCoffee(sort,pack,physicalCondition,weightComparison,priceComparison);
        ShowingOptionController.getInstance().updateCoffees(query);
        clearAllTextFields();
    }

    private void clearSelection()
    {
        clearSelectionSingleBox(sortComboBox);
        clearSelectionSingleBox(conditionComboBox);
        clearSelectionSingleBox(packComboBox);
        clearSelectionSingleBox(weightComboBox);
        clearSelectionSingleBox(priceComboBox);
        clearAllTextFields();
    }

    private void clearAllTextFields()
    {
        clearSingleTextField(priceTextField);
        clearSingleTextField(weightTextField);
    }

    private void clearSelectionSingleBox(ComboBox<?> comboBox)
    {
        comboBox.setValue(null);
    }
    private void clearSingleTextField(TextField textField)
    {
        textField.clear();
    }
    @FXML
    private ComboBox<PhysicalCondition> conditionComboBox;

    @FXML
    private ComboBox<Pack> packComboBox;

    @FXML
    private ComboBox<String> priceComboBox;

    @FXML
    private TextField priceTextField;

    @FXML
    private ComboBox<Sort> sortComboBox;

    @FXML
    private ComboBox<String> weightComboBox;

    @FXML
    private TextField weightTextField;


    @FXML
    void initialize() {
        instance = this;
        ObservableList<Sort> sorts =ShowingOptionController.getInstance().getSorts();
        sortComboBox.setButtonCell(new PromptCell<>(sortComboBox.getPromptText()));
        sortComboBox.setItems(sorts);
        packComboBox.setButtonCell(new PromptCell<>(packComboBox.getPromptText()));
        packComboBox.setItems(ShowingOptionController.getInstance().getPacks());
        conditionComboBox.setButtonCell(new PromptCell<>(conditionComboBox.getPromptText()));
        conditionComboBox.setItems(ShowingOptionController.getInstance().getPhysicalConditions());
        comparisonSymbol = FXCollections.observableArrayList();
        comparisonSymbol.addAll("=","<",">","<=",">=");
        weightComboBox.setButtonCell(new PromptCell<>(weightComboBox.getPromptText()));
        weightComboBox.setItems(comparisonSymbol);
        priceComboBox.setButtonCell(new PromptCell<>(priceComboBox.getPromptText()));
        priceComboBox.setItems(comparisonSymbol);
    }

    public void onClearSelection(ActionEvent actionEvent) {
        clearSelection();
    }
}
