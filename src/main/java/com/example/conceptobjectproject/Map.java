package com.example.conceptobjectproject;

import javafx.geometry.Insets;
import javafx.geometry.Point2D;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.util.ArrayList;


public class Map extends Parent {

    private GridPane map;
    private int mapWidth;
    private int mapHeight;

    private ArrayList<Tile> mapTiles = new ArrayList<Tile>();
    public Map(int mapWidth, int mapHeight, int screenWidth, int screenHeight)
    {
        this.mapWidth = mapWidth;
        this.mapHeight = mapHeight;

        map = new GridPane();

        map.setPadding(new Insets(10));
        map.setHgap(0);
        map.setVgap(0);

        for (int i = 0; i < mapWidth; i++) {
            for (int j = 0; j < mapHeight; j++) {
                Tile newTile=new Tile(i,j, mapWidth,mapHeight,screenWidth,screenHeight);
                mapTiles.add(newTile);
                map.add(newTile.tile,i,j);
            }
        }
    }

    public GridPane GetMap()
    {
        return map;
    }

    public ArrayList<Tile> GetDirectNeighbours(Tile tile, int depth)
    {
        Point2D[] posTotest = new Point2D[]{
                new Point2D(tile.posX-1, tile.posY-1),new Point2D(tile.posX, tile.posY-1),new Point2D(tile.posX+1, tile.posY-1),
                new Point2D(tile.posX-1, tile.posY),new Point2D(tile.posX, tile.posY),new Point2D(tile.posX+1, tile.posY),
                new Point2D(tile.posX-1, tile.posY+1),new Point2D(tile.posX, tile.posY+1),new Point2D(tile.posX+1, tile.posY+1),
        };

        ArrayList<Tile> neighbours = new ArrayList<Tile>();

        for(var item: mapTiles )
        {
            Point2D itemPos= new Point2D(item.posX, item.posY);
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
