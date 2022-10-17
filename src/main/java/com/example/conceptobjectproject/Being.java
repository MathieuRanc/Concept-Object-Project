package com.example.conceptobjectproject;

public class Being {
    String[] messages;
    int energyPoints = 100;
    int maxNumberOfMessages = 20;
    int numberOfMessages = 0;

    Team getTeam() {
        
        return team;
    }

    void setTeam(Team team) {

    }
    boolean isEnemyTeam() {

        return bool;
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
