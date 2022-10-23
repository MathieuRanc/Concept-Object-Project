package com.example.conceptobjectproject;

import Enums.ZoneTypes;
import javafx.geometry.Insets;
import javafx.geometry.Point2D;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.layout.GridPane;
import javafx.scene.shape.Rectangle;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;


public class Map extends Parent {

    private GridPane map;
    private int mapWidth;
    private int mapHeight;

    private ArrayList<Tile> mapTiles;
    public Map(int mapWidth, int mapHeight, int screenWidth, int screenHeight)
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

    public ArrayList<Tile> GetDirectNeighbours(Tile tile, int depth)
    {
        Point2D[] posTotest = new Point2D[]{
                new Point2D(tile.getPosX()-1, tile.getPosY()-1),new Point2D(tile.getPosX(), tile.getPosY()-1),new Point2D(tile.getPosX()+1, tile.getPosY()-1),
                new Point2D(tile.getPosX()-1, tile.getPosY()),new Point2D(tile.getPosX(), tile.getPosY()),new Point2D(tile.getPosX()+1, tile.getPosY()),
                new Point2D(tile.getPosX()-1, tile.getPosY()+1),new Point2D(tile.getPosX(), tile.getPosY()+1),new Point2D(tile.getPosX()+1, tile.getPosY()+1),
        };

        ArrayList<Tile> neighbours = new ArrayList<Tile>();

        for(var item: mapTiles )
        {
            Point2D itemPos= new Point2D(item.getPosX(), item.getPosY());
            for(var pos : posTotest )
            {
                if(itemPos.equals(pos))
                {
                    neighbours.add(item);
                }
            }
        }
        return neighbours;
    }
    public void OnScreenResize(float screen_width, float screen_height)
    {
        for ( var child : map.getChildren()) {
            Rectangle tile = (Rectangle) child;

            if ( tile == null) return;

            tile.setWidth(screen_width/(mapWidth+1));
            tile.setHeight(screen_height/(mapHeight+1));
        }
    }
    @Override
    public Node getStyleableNode() {
        return super.getStyleableNode();
    }
}
