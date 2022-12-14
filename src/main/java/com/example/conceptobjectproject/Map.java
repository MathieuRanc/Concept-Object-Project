package com.example.conceptobjectproject;

import Enums.ZoneTypes;
import javafx.geometry.Insets;
import javafx.geometry.Point2D;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Rectangle;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;


public class Map extends Parent {

    private static Map Instance;

    public static Map GetInstance(int mapWidth, int mapHeight, int screenWidth, int screenHeight)
    {
        if(Instance == null)
        {
            Instance=new Map(mapWidth, mapHeight, screenWidth, screenHeight);
            return Instance;
        }

        return Instance;
    }
    public static Map GetInstance()
    {
        if(Instance == null)
        {
            System.out.println("Error: no instance of the map existing");
            return null;
        }

        return Instance;
    }
    private final GridPane map;
    private final int mapWidth;
    private final int mapHeight;

    private final ArrayList<Tile> mapTiles;
    private Map(int mapWidth, int mapHeight, int screenWidth, int screenHeight)
    {
        this.mapWidth = mapWidth;
        this.mapHeight = mapHeight;

        map = new GridPane();

        map.setPadding(new Insets(10));
        map.setHgap(0);
        map.setVgap(0);

        mapTiles = new ArrayList<>();
        for (int i = 0; i < mapWidth; i++) {
            for (int j = 0; j < mapHeight; j++) {
                Tile newTile=new Tile(i,j, mapWidth,mapHeight,screenWidth,screenHeight);
                mapTiles.add(newTile);
                map.add(newTile.tilePane,i,j);
            }
        }
    }

    public GridPane GetMap()
    {
        return map;
    }
    public Tile GetFreeRandomMapTileOfType(ZoneTypes zonetype)
    {
        Random random = new Random();

        List<Tile> arr =mapTiles.stream().filter(x -> x.zoneType == zonetype && x.GetTileObject() == null).collect(Collectors.toList());

        return arr.get(random.nextInt(arr.size())) ;
    }

    public Tile GetDirectNeighbour(Tile tile, Point2D relativNeighbour)
    {
        Point2D posToTest = new Point2D(tile.getPosX()+relativNeighbour.getX(), tile.getPosY()+relativNeighbour.getY());

        for(var item: mapTiles )
        {
            Point2D itemPos= new Point2D(item.getPosX(), item.getPosY());

            if(itemPos.equals(posToTest))
            {
                return item;
            }
        }
        return null;
    }
    public void OnScreenResize(float screen_width, float screen_height)
    {
        for ( var child : map.getChildren()) {
            StackPane tile = (StackPane) child;

            Rectangle rect = (Rectangle) tile.getChildren().get(0);

            if ( rect == null) return;

            rect.setWidth(screen_width/(mapWidth+1));
            rect.setHeight(screen_height/(mapHeight+1));
        }
    }
    @Override
    public Node getStyleableNode() {
        return super.getStyleableNode();
    }
}
