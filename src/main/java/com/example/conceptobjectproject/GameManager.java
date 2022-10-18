package com.example.conceptobjectproject;

import javafx.application.Application;
import javafx.beans.property.DoubleProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class GameManager extends Application {

    public GameManager Instance;
    public int screenWidth = 1000;
    public int screenHeight = 562;
    private Map _map;
    @Override
    public void start(Stage stage) throws Exception {
        if(Instance !=null)
            Instance=this;

        _map = new Map(16,9,screenWidth,screenHeight);
        Scene gameWindow = new Scene(_map.GetMap(), screenWidth, screenHeight);

        SetupListeners(gameWindow);

        stage.setTitle("SMA");
        stage.setScene(gameWindow);
        stage.show();
    }

    private void SetupListeners(Scene gameWindow)
    {
        gameWindow.widthProperty().addListener(new ChangeListener<Number>() {
            @Override public void changed(ObservableValue<? extends Number> observableValue, Number oldSceneWidth, Number newSceneWidth) {
                screenWidth=newSceneWidth.intValue();
                _map.OnScreenResize(screenWidth,screenHeight);
            }
        });
        gameWindow.heightProperty().addListener(new ChangeListener<Number>() {
            @Override public void changed(ObservableValue<? extends Number> observableValue, Number oldSceneHeight, Number newSceneHeight) {
                screenHeight = newSceneHeight.intValue();
                _map.OnScreenResize(screenWidth,screenHeight);
            }
        });
    }
    public static void main(String[] args) {
        launch(args);
    }
}
