package com.example.conceptobjectproject;

import javafx.application.Application;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class GameManager extends Application {

    private int mapWidth =16;
    private int mapHeight = 8;

    @Override
    public void start(Stage stage) throws Exception {

        Scene scene = new Scene(new Map(mapWidth,mapHeight).map, 1000, 562);

        stage.setTitle("SMA");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
