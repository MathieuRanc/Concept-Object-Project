package com.example.conceptobjectproject;

import Enums.ZoneTypes;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

public class CommonBeing extends Being {

    private final Tile _actualTile;

    public CommonBeing(Map map, ZoneTypes zoneType)
    {
        super(map);

        Text t = new Text("I");
        t.setFont(Font.font(null, FontWeight.BOLD, 20));
        t.setFill(zoneType.beingsColor);
        obj = t;

        _actualTile = map.GetFreeRandomMapTileOfType(zoneType);
        _actualTile.SetTileObject(this);
    }
}
