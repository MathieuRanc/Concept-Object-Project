package com.example.conceptobjectproject;

import javafx.application.Application;
import javafx.beans.property.DoubleProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class GameManager extends Application {

    public GameManager Instance;

    private Map _map;

    public float screenWidth = 1000;
    public float screenHeight = 562;
    @Override
    public void start(Stage stage) throws Exception {

        if(Instance !=null)
            Instance=this;

        _map = new Map(16,8,stage);
        Scene gameWindow = new Scene(_map.GetMap(), screenWidth, screenHeight);

        SetupListener(gameWindow);

        stage.setTitle("SMA");
        stage.setScene(gameWindow);
        stage.show();
    }

    private void SetupListener(Scene gameWindow)
    {
        gameWindow.widthProperty().addListener(new ChangeListener<Number>() {
            @Override public void changed(ObservableValue<? extends Number> observableValue, Number oldSceneWidth, Number newSceneWidth) {
                screenWidth=newSceneWidth.floatValue();
                _map.OnScreenResize(screenWidth,screenHeight);
            }
        });
        gameWindow.heightProperty().addListener(new ChangeListener<Number>() {
            @Override public void changed(ObservableValue<? extends Number> observableValue, Number oldSceneHeight, Number newSceneHeight) {
                screenHeight = newSceneHeight.floatValue();
                _map.OnScreenResize(screenWidth,screenHeight);
            }
        });
    }
    public static void main(String[] args) {
        launch(args);
    }
}
