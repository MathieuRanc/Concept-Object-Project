package com.example.conceptobjectproject;

import Enums.ZoneTypes;
import javafx.application.Application;
import javafx.beans.property.DoubleProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.ArrayList;

public class GameManager extends Application {

    public static GameManager Instance;
    public int screenWidth = 1000;
    public int screenHeight = 562;
    private Map _map;

    @Override
    public void start(Stage stage) throws Exception {

        if(Instance !=null)
            Instance=this;

        _map = new Map(20,12,screenWidth,screenHeight);
        Scene gameWindow = new Scene(_map.GetMap(), screenWidth, screenHeight);

        SetupListeners(gameWindow);
        InitGame();

        stage.setTitle("SMA");
        stage.setScene(gameWindow);
        stage.show();
    }

    private ArrayList<MasterBeing> _masters;
    private void InitGame() {
        _masters = new ArrayList<>();
        _masters.add(new MasterBeing(_map, ZoneTypes.SafeZoneTeam1));
        _masters.add(new MasterBeing(_map, ZoneTypes.SafeZoneTeam2));
        _masters.add(new MasterBeing(_map, ZoneTypes.SafeZoneTeam3));
        _masters.add(new MasterBeing(_map, ZoneTypes.SafeZoneTeam4));

        GenerateObstacles(10);
    }

    private void GenerateObstacles(int i) {
        for (int j = 0; j < i ; j++) {
            new Obstacle(_map,ZoneTypes.Neutral);
        }
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
