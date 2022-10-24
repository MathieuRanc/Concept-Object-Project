package com.example.conceptobjectproject;
import Enums.Direction;

public class CommonBeing extends Being {
    private int energyPoints = 100;
    private int maxNumberOfMessages = 20;
    private int numberOfMessages = 0;
    private int x = 0;
    private int y = 0;

    public CommonBeing(Map map) {
        super(map);
    }
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
    void move(Direction direction) {
        if (energyPoints > 0) {
            energyPoints--;
            switch (direction) {
                case North:
                    if (y > 0) {
                        y--;
                    }
                    break;
                case South:
                    if (y < _map.GetMap().getWidth() - 1) {
                        y++;
                    }
                    break;
                case East:
                    if (x < _map.GetMap().getHeight() - 1) {
                        x++;
                    }
                    break;
                case West:
                    if (x > 0) {
                        x--;
                    }
                    break;
            }
        }
    }

    private Direction getLastTakenDirection() {

        return direction;
    }
}
