package com.example.conceptobjectproject;

import Enums.ZoneTypes;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Obstacle extends SimulationObject{

    private final Tile _actualTile;
    public Obstacle(Map map)
    {
        Rectangle rect = new Rectangle();
        StackPane child = (StackPane) map.GetMap().getChildren().get(0);
        rect.widthProperty().bind(child.widthProperty().subtract(10));
        rect.heightProperty().bind(child.heightProperty().subtract(5));
        rect.setFill(Color.BLACK);

        //SimulationObject data
        graphObj = rect;
        objectType = ZoneTypes.Obstacle;

        _actualTile = map.GetFreeRandomMapTileOfType(ZoneTypes.Neutral);
        _actualTile.SetTileObject(this, ZoneTypes.Obstacle);
    }
}
