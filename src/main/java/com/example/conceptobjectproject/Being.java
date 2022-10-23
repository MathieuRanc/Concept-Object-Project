package com.example.conceptobjectproject;

import Enums.Direction;

public class Being extends SimulationObject {
    public String[] messages;
    int energyPoints = 100;
    int maxNumberOfMessages = 20;
    int numberOfMessages = 0;

    Team team;
    Direction direction;

    Team getTeam() {
        return team;
    }

    void setTeam(Team team) {

    }
    boolean isEnemyTeam() {

        return true;
    }
    void move() {

    }
    void decreasePoints() {

    }
    void increasePoints() {

    }
    Direction getLastTakenDirection() {

        return direction;
    }
    String[] getMessages() {

        return messages;
    }
}
