package com.example.conceptobjectproject;

import Enums.ZoneTypes;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class GameManager extends Application {

    private int screenWidth = 1000;
    private int screenHeight = 562;

    private int _maxNumbreOfGameSteps = 600;

    private final String[] allGamePokemons = {
            "Charmander", "Charmeleon", "Charizard", "Camerupt", "Emboar", "Vulpix", "Ninetales", "Ponyta", "Magmar",
            "Squirtle", "Wartotle", "Blastoise", "Psyduck", "Golduck", "Seel", "Crabby", "Horsea", "Seadra",
            "Bulbasaur", "Victreebel", "Bellossom", "Cacturne", "Tropius", "Lileep", "Cherrim", "Carnivine", "Sunflora",
            "Pikachu","Raichu", "Jolteon", "Mareep", "Flaaffy", "Ampharos", "Elekid", "Minun", "Shinx"
    };

    private ArrayList<MasterTrainer> _masters;
    @Override
    public void start(Stage stage) {
        stage.setTitle("Pokemon SMA");

        Map.GetInstance(20,12,screenWidth,screenHeight);
        Scene gameWindow = new Scene(Map.GetInstance().GetMap(), screenWidth, screenHeight);
        stage.setScene(gameWindow);

        SetupListeners(gameWindow);
        InitGame();


        new AnimationTimer()
        {
            private long lastUpdate = 0 ;
            int gameStep =0;
            public void handle(long now)
            {
                if (now - lastUpdate >= 1000_000_000) {

                    Collections.shuffle(_masters);
                    for(var master : _masters)
                    {
                        if(  master.pokemons.containsAll(new ArrayList<>(Arrays.asList(allGamePokemons)))) // test end of the game
                            stage.close();


                        ArrayList<CommonTrainer> commons = new ArrayList<>(master.GetCommonTrainers());
                        for(var common:commons)
                        {
                            common.move();
                        }

                    }

                    gameStep++;
                    if(gameStep>_maxNumbreOfGameSteps)//If game trop dure à finir on fini de force
                        stage.close();

                    lastUpdate = now ;
                }

            }
        }.start();

        stage.show();
    }

    private void InitGame() {

        _masters = new ArrayList<>();
        _masters.add(new MasterTrainer("Master1", ZoneTypes.SafeZoneTeam1, Arrays.copyOfRange(allGamePokemons, 0, 9)));
        _masters.add(new MasterTrainer("Master2", ZoneTypes.SafeZoneTeam2, Arrays.copyOfRange(allGamePokemons, 9, 18)));
        _masters.add(new MasterTrainer("Master3", ZoneTypes.SafeZoneTeam3, Arrays.copyOfRange(allGamePokemons, 18, 27)));
        _masters.add(new MasterTrainer("Master4", ZoneTypes.SafeZoneTeam4, Arrays.copyOfRange(allGamePokemons, 27, 36)));

        GenerateObstacles(10);
    }

    private void GenerateObstacles(int i) {
        for (int j = 0; j < i ; j++) {
            new Obstacle();
        }
    }

    private void SetupListeners(Scene gameWindow)
    {
        gameWindow.widthProperty().addListener((observableValue, oldSceneWidth, newSceneWidth) -> {
            screenWidth = newSceneWidth.intValue();
            Map.GetInstance().OnScreenResize(screenWidth, screenHeight);
        });

        gameWindow.heightProperty().addListener((observableValue, oldSceneHeight, newSceneHeight) -> {
            screenHeight = newSceneHeight.intValue();
            Map.GetInstance().OnScreenResize(screenWidth,screenHeight);
        });
    }
    public static void main(String[] args) {
        launch(args);
    }

}
