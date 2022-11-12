package com.example.demo.controllers;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

import com.example.demo.Application;
import com.example.demo.DAO.ColumnConstant;
import com.example.demo.DAO.QueryConstant;
import com.example.demo.Model.*;
import javafx.beans.value.ObservableValueBase;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.util.Callback;
import javafx.util.StringConverter;

public class ShowingOptionController {

    public static ShowingOptionController instance;


    public static ShowingOptionController getInstance() {
        return instance;
    }
    public Button addNewSort;
    public Button addNewPack;
    public Button addNewCondition;
    public Button addNewCoffee;
    Model model;
    ObservableList<Coffee> coffees;
    ObservableList<Pack> packs;
    ObservableList<Sort> sorts;
    ObservableList<PhysicalCondition> physicalConditions;
    Map<Integer,Integer> coffeeConsumptionMap;

    public ObservableList<Pack> getPacks() {
        return packs;
    }

    public ObservableList<Sort> getSorts() {
        return sorts;
    }

    public ObservableList<PhysicalCondition> getPhysicalConditions() {
        return physicalConditions;
    }

    @FXML
    private ComboBox<PhysicalCondition> conditionComboBox;
    @FXML
    private ComboBox<Pack> packComboBox;
    @FXML
    private TextField priceEdit;
    @FXML
    private ComboBox<Sort> sortComboBox;
    @FXML
    private TextField weightEdit;
    @FXML
    private Label totalPrice;

    @FXML
    private Label totalWeight;
    @FXML
    private TableColumn<Coffee, Integer> idColumn;

    @FXML
    private TableView<Coffee> outputTable;
    @FXML
    private TableColumn<?, ?> packColumn;
    public TableColumn<Coffee,Integer> runOutOfColumn;
    @FXML
    private TableColumn<Coffee, String> packNameCol;

    @FXML
    private TableColumn<Coffee, Double> packVolumeCol;

    @FXML
    private TableColumn<Coffee, String> physicalConditionCol;

    @FXML
    private TableColumn<Coffee, Double> weightColumn;

    @FXML
    private TableColumn<Coffee, Double> priceColumn;

    @FXML
    private TableColumn<Coffee, String> sortColumn;

    @FXML
    void onBack(ActionEvent event) {
        Application.setScene(Application.getMainMenu());
    }

    public void updateCoffees(String query)
    {
        coffees=model.getCoffee(query);
        computeDaysToRunOut();
        String priceText  = totalPrice.getText();
        String weightText  = totalWeight.getText();
        totalPrice.setText(priceText.substring(0,priceText.indexOf(":")+1) +" "+ model.showTotalPrice());
        totalWeight.setText(weightText.substring(0,weightText.indexOf(":")+1) +" "+ model.showTotalWeight());
        outputTable.setItems(coffees);

    }
    private void computeDaysToRunOut()
    {
        coffeeConsumptionMap = model.showTimeToRunOutOfCoffee();
    }
    public void initTable()
    {
        instance = this;
        if(model==null)
            model = Model.getInstance();
        Callback<TableColumn<Coffee,Double>, TableCell<Coffee,Double>> cellFactory =
                p -> {
                    TextFieldTableCell<Coffee, Double> tx = new TextFieldTableCell<>();
                    tx.setConverter(new StringConverter<>() {
                        @Override
                        public String toString(Double aDouble) {
                            return aDouble.toString();
                        }
                        @Override
                        public Double fromString(String s) {
                            return Double.valueOf(s);
                        }
                    });
                    return tx;
                };
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        sortColumn.setCellValueFactory(cellDate-> cellDate.getValue().getSort().nameProperty());
        weightColumn.setCellValueFactory(new PropertyValueFactory<>("weight"));
        weightColumn.setCellFactory(cellFactory);
        priceColumn.setCellValueFactory(new PropertyValueFactory<>("pricePerKilogram"));
        priceColumn.setCellFactory(cellFactory);
        packNameCol.setCellValueFactory(cellData-> cellData.getValue().getPack().nameProperty());
        packVolumeCol.setCellValueFactory(cellData-> new ObservableValueBase<>() {
            @Override
            public Double getValue() {
                return cellData.getValue().getPack().getVolume();
            }
        });
        physicalConditionCol.setCellValueFactory(cellDate->
                cellDate.getValue().getPhysicalCondition().physicalConditionProperty());
        runOutOfColumn.setCellValueFactory(cellData->new ObservableValueBase<>() {
            @Override
            public Integer getValue() {
                return coffeeConsumptionMap.get(cellData.getValue().getId());
            }
        });
        updateCoffees(QueryConstant.selectAllCoffee);
        outputTable.setItems(coffees);
    }

    public void initComboBox()
    {
        if(model==null)
            model = Model.getInstance();
        initPackBox();
        initConditionBox();
        initSortBox();
    }

    public void initPackBox()
    {
        packs = FXCollections.observableArrayList(model.showPacks());
        packComboBox.setItems(packs);
    }

    public void initSortBox()
    {
        sorts = FXCollections.observableArrayList(model.getSortList());
        sortComboBox.setItems(sorts);
    }

    public void initConditionBox()
    {
        physicalConditions = FXCollections.observableArrayList(model.getConditionList());
        conditionComboBox.setItems(physicalConditions);
    }
    @FXML
    void initialize() {
        model = Model.getInstance();
        coffees = FXCollections.observableArrayList();
        coffeeConsumptionMap = FXCollections.observableMap(new HashMap<>());
        initTable();
        initComboBox();
    }

    public void onAddNewSort(ActionEvent actionEvent) {
        Application.setScene(Application.getSortScene());
    }

    public void onAddNewPack(ActionEvent actionEvent) {
        Application.setScene(Application.getPackScene());
    }

    private boolean validateInputCoffee(String weight ,String price,Sort sort,Pack pack, PhysicalCondition physicalCondition)
    {
        if(weight.equals("")||price.equals("")
                ||sort.getName().equals("")||pack.getName().equals("")
                ||physicalCondition.getPhysicalCondition().equals(""))
            return false;
        return true;
    }
    public void onAddNewCoffee(ActionEvent actionEvent) {
        Sort sort = sortComboBox.getValue();
        String stWeight = weightEdit.getText();
        String stPrice = priceEdit.getText();
        Pack pack = packComboBox.getValue();
        PhysicalCondition physicalCondition = conditionComboBox.getValue();
        if(!validateInputCoffee(stWeight,stPrice,sort,pack,physicalCondition))
            return;
        double weight = Double.parseDouble(stWeight);
        double price = Double.parseDouble(stPrice);
        model.loadNewCoffee(pack.getId(),sort.getId(), physicalCondition.getId(), weight,price);
        updateCoffees(QueryConstant.selectAllCoffee);
        computeDaysToRunOut();
        outputTable.refresh();
    }

    public void addNewCondition(ActionEvent actionEvent) {
        Application.setScene(Application.getConditionScene());
    }

    public void onWeightEditCommit(TableColumn.CellEditEvent<Coffee, Double> coffeeDoubleCellEditEvent) {
        Double weight = coffeeDoubleCellEditEvent.getNewValue();
        if(weight<0)
        {
            System.out.println("wrong INput");
            return;
        }
        model.consumptionCoffee(weight,coffeeDoubleCellEditEvent.getRowValue());
        coffees.get(coffees.indexOf(coffeeDoubleCellEditEvent.getRowValue())).setWeight(weight);
        updateCoffees(QueryConstant.selectAllCoffee);
    }

    public void onPriceEditCommit(TableColumn.CellEditEvent<Coffee, Double> coffeeDoubleCellEditEvent) {
        Double price = coffeeDoubleCellEditEvent.getNewValue();
        if(price<0)
        {
            System.out.println("wrong INput");
            return;
        }
        model.setNewPrice(price,coffeeDoubleCellEditEvent.getRowValue());
        coffees.get(coffees.indexOf(coffeeDoubleCellEditEvent.getRowValue())).setPricePerKilogram(price);
        updateCoffees(QueryConstant.selectAllCoffee);
    }

    public void onSearchMenuButton(ActionEvent actionEvent) {
        SearchMenuView.openSearchMenu();
    }

    public void onShowSoonRunOutCoffee(ActionEvent actionEvent) {
        double criticalWeight = 125;
        updateCoffees(QueryConstant.selectAllCoffee+" where weight < "+criticalWeight);
    }

    public void onShowAllCoffee(ActionEvent actionEvent) {
        updateCoffees(QueryConstant.selectAllCoffee);
    }
}
