package com.example.conceptobjectproject;

import Enums.Direction;
import Enums.ZoneTypes;
import javafx.geometry.Point2D;

import java.util.ArrayList;
import java.util.Random;

public class Being extends SimulationObject {

    protected final Map _map;
    protected final ZoneTypes _team;
    protected Tile _actualTile;

    public ArrayList<String> messages;

    private int maxNumberOfMessages = 20;
    private int numberOfMessages = 0;

    Direction direction;
    public Being(Map map,ZoneTypes zoneType){
        _map =map;
        _team = zoneType;
        messages = new ArrayList<>();
    }

    public boolean isEnnemy(Being otherBeing)
    {
        switch (objectType)
        {
            case SafeZoneTeam1:
            case SafeZoneTeam2:
                if(otherBeing.objectType == ZoneTypes.SafeZoneTeam2 || otherBeing.objectType == ZoneTypes.SafeZoneTeam1)
                   return false;

                return true;

            case SafeZoneTeam3:
            case SafeZoneTeam4:
                if(otherBeing.objectType == ZoneTypes.SafeZoneTeam3 || otherBeing.objectType == ZoneTypes.SafeZoneTeam4)
                    return false;

                return true;

            default:
                return false;
        }

    }
    public void Move()
    {

    }


}
