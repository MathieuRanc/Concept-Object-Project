package com.example.conceptobjectproject;

import Enums.ZoneTypes;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Obstacle extends SimulationObject{

    private final Tile _actualTile;
    public Obstacle(Map map, ZoneTypes zoneType)
    {
        Rectangle rect = new Rectangle();
        StackPane child = (StackPane) map.GetMap().getChildren().get(0);
        rect.widthProperty().bind(child.widthProperty().subtract(5));
        rect.heightProperty().bind(child.heightProperty().subtract(5));
        rect.setFill(Color.BLACK);
        obj = rect;

        _actualTile = map.GetFreeRandomMapTileOfType(zoneType);
        _actualTile.SetTileObject(this);
    }
}
