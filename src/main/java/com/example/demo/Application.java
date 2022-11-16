package com.example.demo;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Application extends javafx.application.Application {
   private static Stage mainStage;
   private static Scene mainMenu;
   private static Scene displayMenu;
   private static Scene sortScene;
   private static Scene conditionScene;
   private static Scene packScene;
    @Override
    public void start(Stage stage) throws IOException {
        mainStage = stage;
        FXMLLoader fxmlLoader = new FXMLLoader
                (Application.class.getResource
                        ("hello-view.fxml"));
        mainMenu = new Scene(fxmlLoader.load());
        stage.setTitle("Фургон кави");
        stage.setScene(mainMenu);
        stage.show();
        displayMenu = getSceneFromFXML("showingOptionView.fxml");
        sortScene = getSceneFromFXML("addNewSortView.fxml");
        conditionScene =  getSceneFromFXML("addNewConditionView.fxml");
        packScene = getSceneFromFXML("addNewPackView.fxml");
    }

    private Scene getSceneFromFXML (String fxmlFile) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Application.class.getResource(fxmlFile));
        return new Scene(fxmlLoader.load());
    }

    public static Scene getMainMenu() {
        return mainMenu;
    }

    public static Scene getPackScene() {
        return packScene;
    }

    public static Scene getDisplayMenu() {
        return displayMenu;
    }

    public static Scene getSortScene() {
        return sortScene;
    }

    public static Scene getConditionScene() {
        return conditionScene;
    }


    public static void setScene(Scene scene)
    {
        mainStage.setScene(scene);
    }

    public static void main(String[] args) {
        launch();
    }
}