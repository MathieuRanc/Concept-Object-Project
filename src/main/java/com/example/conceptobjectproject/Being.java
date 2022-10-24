package com.example.conceptobjectproject;

import Enums.Direction;
import Enums.ZoneTypes;

public class Being extends SimulationObject {

    protected final Map _map;
    public String[] messages;
    int energyPoints = 100;
    int maxNumberOfMessages = 20;
    int numberOfMessages = 0;

    public Being(Map map){
        _map =map;
    }
    Team team;
    Direction direction;

}
