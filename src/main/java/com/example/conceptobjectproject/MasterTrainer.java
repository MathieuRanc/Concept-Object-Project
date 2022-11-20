package com.example.conceptobjectproject;

import Enums.ZoneTypes;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class MasterTrainer extends Trainer {
    private final ArrayList<CommonTrainer> commonTrainers = new ArrayList<>();

    protected Tile _actualTile;

    public MasterTrainer(String name, ZoneTypes zoneType, String[] messageOfCommons) {
        super(zoneType);

        Text t = new Text("X");
        t.setFont(Font.font(null, FontWeight.BOLD, 20));
        t.setFill(zoneType.objectColor);

        // Setting simulationobject data
        graphObj = t;
        objectType = zoneType;
        Name = name;

        _actualTile = Map.GetInstance().GetFreeRandomMapTileOfType(zoneType);
        _actualTile.SetTileObject(this, zoneType);

        for (int i = 0; i < 7; i += 2) {
            commonTrainers.add(new CommonTrainer(name + "_CommonTrainer" + i, this, zoneType,
                    Arrays.copyOfRange(messageOfCommons, i, i + 2)));
        }
    }

    public ArrayList<CommonTrainer> GetCommonTrainers() {
        Collections.shuffle(commonTrainers);
        return commonTrainers;
    }

    public void ChangeCommonTrainerToObstacle(CommonTrainer commonTrainer, Tile tile) {
        // print message to console that common trainer is now an obstacle in color of
        // the common trainer
        System.out.println(commonTrainer.Name + " is out of energy and transform into an obstacle..");
        commonTrainers.remove(commonTrainer);
        new Obstacle(tile, _map);
    }
}
