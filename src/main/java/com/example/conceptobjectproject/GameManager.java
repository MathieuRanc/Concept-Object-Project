package com.example.conceptobjectproject;

import Enums.ZoneTypes;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class GameManager extends Application {

    public int screenWidth = 1000;
    public int screenHeight = 562;
    private Map _map;

    private String[] allGameMessages = {"YtgItpWJhf",
            "EBAKgrDrGd",
            "VeHWdxjzay",
            "CiMTQiYRpi",
            "vhkzTYnBoQ",
            "maBIeeyYtx",
            "igZSHLSRZH",
            "SRbxdxxAZs",
            "qnlAMmWYHs",
            "DneagCzVaF",
            "FdalJWtwMa",
            "tjaqCKmisk",};

    private ArrayList<MasterBeing> _masters;
    @Override
    public void start(Stage stage) throws Exception {
        stage.setTitle("SMA");

        _map = new Map(20,12,screenWidth,screenHeight);
        Scene gameWindow = new Scene(_map.GetMap(), screenWidth, screenHeight);
        stage.setScene(gameWindow);

        SetupListeners(gameWindow);
        InitGame();

        new AnimationTimer()
        {
            private long lastUpdate = 0 ;
            public void handle(long now)
            {
                if (now - lastUpdate >= 1000_000_000) {

                    Collections.shuffle(_masters);
                    for(var master : _masters)
                    {

                        for(var common : master.GetCommonBeings())
                        {
                            common.move();
                        }
                    }

                    lastUpdate = now ;
                }

            }
        }.start();

        stage.show();
    }

    private void InitGame() {
        String[] messagesToGive = allGameMessages;

        _masters = new ArrayList<>();
        _masters.add(new MasterBeing(_map, ZoneTypes.SafeZoneTeam1, Arrays.copyOfRange(allGameMessages, 0, 3)));
        _masters.add(new MasterBeing(_map, ZoneTypes.SafeZoneTeam2, Arrays.copyOfRange(allGameMessages, 3, 6)));
        _masters.add(new MasterBeing(_map, ZoneTypes.SafeZoneTeam3, Arrays.copyOfRange(allGameMessages, 6, 9)));
        _masters.add(new MasterBeing(_map, ZoneTypes.SafeZoneTeam4, Arrays.copyOfRange(allGameMessages, 9, 12)));

        GenerateObstacles(10);
    }

    private void GenerateObstacles(int i) {
        for (int j = 0; j < i ; j++) {
            new Obstacle(_map);
        }
    }

    private void SetupListeners(Scene gameWindow)
    {
        gameWindow.widthProperty().addListener((observableValue, oldSceneWidth, newSceneWidth) -> {
            screenWidth = newSceneWidth.intValue();
            _map.OnScreenResize(screenWidth, screenHeight);
        });

        gameWindow.heightProperty().addListener((observableValue, oldSceneHeight, newSceneHeight) -> {
            screenHeight = newSceneHeight.intValue();
            _map.OnScreenResize(screenWidth,screenHeight);
        });
    }
    public static void main(String[] args) {
        launch(args);
    }

}
