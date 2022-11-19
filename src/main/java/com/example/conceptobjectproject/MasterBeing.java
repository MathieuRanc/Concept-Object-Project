package com.example.conceptobjectproject;

import Enums.ZoneTypes;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

import java.util.ArrayList;
import java.util.Collections;

public class MasterBeing extends Being {
    private final ArrayList<CommonBeing> commonBeings = new ArrayList<>();

    protected Tile _actualTile;
    public MasterBeing(String name,Map map, ZoneTypes zoneType, String[] messageOfCommons)
    {
        super(map,zoneType);

        Text t = new Text("X");
        t.setFont(Font.font(null, FontWeight.BOLD, 20));
        t.setFill(zoneType.objectColor);

        //Setting simulationobject data
        graphObj = t;
        objectType= zoneType;
        Name = name;

        _actualTile = map.GetFreeRandomMapTileOfType(zoneType);
        _actualTile.SetTileObject(this,zoneType);

        for (int i = 0; i < 3; i++) {
            commonBeings.add(new CommonBeing(name+"_CommonBeing"+ i,this,map,zoneType,messageOfCommons[i]));
        }
    }

    public ArrayList<CommonBeing> GetCommonBeings()
    {
        Collections.shuffle(commonBeings);
        return commonBeings;
    }

    public void ChangeCommonBeingToObstacle(CommonBeing commonBeing, Tile tile)
    {
        commonBeings.remove(commonBeing);
        new Obstacle(tile,_map);
    }
}
