package com.example.conceptobjectproject;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class GameManager extends Application {

    public GameManager Instance;

    private Map _map;

    @Override
    public void start(Stage stage) throws Exception {

        if(Instance !=null)
            Instance=this;

        _map = new Map(16,8,stage);
        Scene gameWindow = new Scene(_map.GetMap(), 1000, 562);

        SetupListener(gameWindow);

        stage.setTitle("SMA");
        stage.setScene(gameWindow);
        stage.show();
    }

    private void SetupListener(Scene gameWindow)
    {
        gameWindow.widthProperty().addListener(new ChangeListener<Number>() {
            @Override public void changed(ObservableValue<? extends Number> observableValue, Number oldSceneWidth, Number newSceneWidth) {
                _map.OnScreenResize(10,10);
            }
        });
        gameWindow.heightProperty().addListener(new ChangeListener<Number>() {
            @Override public void changed(ObservableValue<? extends Number> observableValue, Number oldSceneHeight, Number newSceneHeight) {
                System.out.println("Height: " + newSceneHeight);
            }
        });
    }
    public static void main(String[] args) {
        launch(args);
    }
}
