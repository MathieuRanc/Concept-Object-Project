package Enums;

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

        Direction[] As = Direction.values();

        return As[random.nextInt(Direction.values().length)];
    }
}
