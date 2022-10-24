package com.example.conceptobjectproject;
import Enums.Direction;

import Enums.ZoneTypes;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

public class CommonBeing extends Being {
    private int energyPoints = 100;
    private int maxNumberOfMessages = 20;
    private int numberOfMessages = 0;
    private int x = 0;
    private int y = 0;

    public CommonBeing(Map map, ZoneTypes zoneType)
    {
        super(map);

        Text t = new Text("I");
        t.setFont(Font.font(null, FontWeight.BOLD, 20));
        t.setFill(zoneType.beingsColor);
        obj = t;

        _actualTile = map.GetFreeRandomMapTileOfType(zoneType);
        _actualTile.SetTileObject(this);
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

    private final Tile _actualTile;

    
}
