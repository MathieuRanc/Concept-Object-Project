package com.example.conceptobjectproject;

import Enums.ZoneTypes;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Obstacle extends SimulationObject{

    private final Tile _actualTile;
    public Obstacle(Map map, ZoneTypes zoneType)
    {
        Rectangle t = new Rectangle();
        t.setFill(Color.BLACK);
        obj = t;

        _actualTile = map.GetFreeRandomMapTileOfType(zoneType);
        _actualTile.SetTileObject(this);
    }
}
