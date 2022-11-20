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
            "Pikachu", "Raichu", "Jolteon", "Mareep", "Flaaffy", "Ampharos", "Elekid", "Minun", "Shinx"
    };

    private ArrayList<MasterTrainer> _masters;

    @Override
    public void start(Stage stage) {
        stage.setTitle("Pokemon SMA");

        Map.GetInstance(20, 12, screenWidth, screenHeight);
        Scene gameWindow = new Scene(Map.GetInstance().GetMap(), screenWidth, screenHeight);
        stage.setScene(gameWindow);

        SetupListeners(gameWindow);
        InitGame();

        new AnimationTimer() {
            private long lastUpdate = 0;
            int gameStep = 0;

            public void handle(long now) {
                if (now - lastUpdate >= 1000_000_000) {

                    // System.out.print("\033[H\033[2J");
                    System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");

                    // Collections.shuffle(_masters);
                    for (var master : _masters) {
                        if (master.pokemons.containsAll(new ArrayList<>(Arrays.asList(allGamePokemons)))) // test end of
                                                                                                          // the game
                            stage.close();

                        ArrayList<CommonTrainer> commons = new ArrayList<>(master.GetCommonTrainers());
                        for (var common : commons) {
                            common.move();
                        }

                    }
                    // foreach master order by name
                    var masters = new ArrayList<>(_masters);
                    Collections.sort(masters, (o1, o2) -> o1.Name.compareTo(o2.Name));
                    System.out.println("");
                    for (var master : masters) {
                        // print number of pokemons of each master and reinitialise color
                        System.out.println("\u001B[0m-------------------------------");
                        // if master contain Fire print in red
                        if (master.Name.contains("Fire")) {
                            System.out.print("\u001B[31m");
                        }
                        // if master contain Water print in blue
                        if (master.Name.contains("Water")) {
                            System.out.print("\u001B[34m");
                        }
                        // if master contain Plant print in green
                        if (master.Name.contains("Plant")) {
                            System.out.print("\u001B[32m");
                        }
                        // if master contain Electric print in yellow
                        if (master.Name.contains("Electric")) {
                            System.out.print("\u001B[33m");
                        }
                        System.out.println(master.Name + " has " + master.pokemons.size() + " pokemons");
                    }
                    System.out.println("\u001B[0m-------------------------------");

                    // if amount > 10 and Traner is a master trainer
                    for (var master : masters) {
                        if (master.pokemons.size() > 30) {
                            if (master.Name.contains("Fire")) {
                                System.out.print("\u001B[31m");
                            }
                            // if master contain Water print in blue
                            if (master.Name.contains("Water")) {
                                System.out.print("\u001B[34m");
                            }
                            // if master contain Plant print in green
                            if (master.Name.contains("Plant")) {
                                System.out.print("\u001B[32m");
                            }
                            // if master contain Electric print in yellow
                            if (master.Name.contains("Electric")) {
                                System.out.print("\u001B[33m");
                            }
                            var message = master.Name + " win the game";
                            System.out.println(
                                    "\n\n");
                            // print message and add stars at the start and end of the message to have a 30
                            // caracters line
                            var messageLength = message.length();
                            var stars = "";
                            for (int i = 0; i < 30 - messageLength; i++) {
                                stars += "*";
                            }
                            System.out.println(stars + " " + message + " " + stars);

                            System.exit(0);
                        }
                    }

                    gameStep++;
                    if (gameStep > _maxNumbreOfGameSteps)// If game trop dure à finir on fini de force
                        stage.close();

                    lastUpdate = now;
                }

            }
        }.start();

        stage.show();
    }

    private void InitGame() {

        _masters = new ArrayList<>();
        _masters.add(
                new MasterTrainer("Fire_master", ZoneTypes.SafeZoneTeam1, Arrays.copyOfRange(allGamePokemons, 0, 9)));
        _masters.add(new MasterTrainer("Electric_master", ZoneTypes.SafeZoneTeam2,
                Arrays.copyOfRange(allGamePokemons, 9, 18)));
        _masters.add(
                new MasterTrainer("Plant_master", ZoneTypes.SafeZoneTeam3,
                        Arrays.copyOfRange(allGamePokemons, 18, 27)));
        _masters.add(
                new MasterTrainer("Water_master", ZoneTypes.SafeZoneTeam4,
                        Arrays.copyOfRange(allGamePokemons, 27, 36)));

        GenerateObstacles(10);
    }

    private void GenerateObstacles(int i) {
        for (int j = 0; j < i; j++) {
            new Obstacle();
        }
    }

    private void SetupListeners(Scene gameWindow) {
        gameWindow.widthProperty().addListener((observableValue, oldSceneWidth, newSceneWidth) -> {
            screenWidth = newSceneWidth.intValue();
            Map.GetInstance().OnScreenResize(screenWidth, screenHeight);
        });

        gameWindow.heightProperty().addListener((observableValue, oldSceneHeight, newSceneHeight) -> {
            screenHeight = newSceneHeight.intValue();
            Map.GetInstance().OnScreenResize(screenWidth, screenHeight);
        });
    }

    public static void main(String[] args) {
        launch(args);
    }

}
