package com.example.conceptobjectproject;

import Enums.Direction;
import Enums.ZoneTypes;

public class Being extends SimulationObject {

    protected final Map _map;
    protected final ZoneTypes _team;
    protected Tile _actualTile;

    public String[] messages;
    int energyPoints = 100;
    int maxNumberOfMessages = 20;
    int numberOfMessages = 0;

    public Being(Map map,ZoneTypes zoneType){
        _map =map;
        _team = zoneType;
    }
    Team team;
    Direction direction;

}
