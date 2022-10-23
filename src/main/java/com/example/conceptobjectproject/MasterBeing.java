package com.example.conceptobjectproject;

import Enums.ZoneTypes;

public class MasterBeing extends Being {

private Tile _actualTile;
    public MasterBeing(Map map, ZoneTypes zoneType)
    {
        _actualTile = map.GetFreeRandomMapTileOfType(zoneType);
        _actualTile.SetTileObject(this,"X");

    }
}
