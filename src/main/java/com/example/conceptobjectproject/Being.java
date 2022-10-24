package com.example.conceptobjectproject;

import Enums.Direction;
import Enums.ZoneTypes;

public class Being extends SimulationObject {

    private final Map _map;
    private final ZoneTypes _team;

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
