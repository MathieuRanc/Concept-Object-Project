package com.example.conceptobjectproject;

import Enums.ZoneTypes;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

import java.util.ArrayList;

public class MasterBeing extends Being {

    private ArrayList<CommonBeing> commonBeings = new ArrayList<>();

    protected Tile _actualTile;
    public MasterBeing(Map map, ZoneTypes zoneType)
    {
        super(map);

        Text t = new Text("X");
        t.setFont(Font.font(null, FontWeight.BOLD, 20));
        t.setFill(zoneType.beingsColor);
        obj = t;

        _actualTile = map.GetFreeRandomMapTileOfType(zoneType);
        _actualTile.SetTileObject(this);

        for (int i = 0; i < 3; i++) {
            commonBeings.add(new CommonBeing(map,zoneType));
        }

    }
}
