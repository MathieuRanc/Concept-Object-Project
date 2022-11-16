package Enums;

import com.example.conceptobjectproject.Tile;
import javafx.geometry.Point2D;
import javafx.scene.paint.Color;

import java.util.Random;

public enum Direction {
    North(0,1),
    South(0,-1),
    East(1,0),
    West(-1,0),
    Northeast(1,1),
    NorthWest(-1,1),
    SouthEast(1,-1),
    SouthWest(-1,-1);

    public Point2D relativPos;
    private Direction(int x, int y)
    {
        this.relativPos = new Point2D(x,y);
    }
    public static  Direction GetRandomDirection()
    {
        Random random = new Random();
        return Direction.values()[random.nextInt(Direction.values().length)];
    }

    public static Direction GetOrientedDirection(Tile objectivTile, Tile actualTile) {
        double minDist = 1000000000;
        Direction directionToTake = null;

        for(var dir : Direction.values())
        {
            int newX = (int) (actualTile.getPosX()+dir.relativPos.getX());
            int newY = (int) (actualTile.getPosY()+dir.relativPos.getY());

            double distance =Math.sqrt(Math.pow((objectivTile.getPosX()-newX),2)+Math.pow((objectivTile.getPosY()-newY),2));
            if(distance<minDist)
            {
                minDist = distance;
                directionToTake = dir;
            }
        }
        return directionToTake;
    }
}
