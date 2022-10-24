package com.example.conceptobjectproject;
import Enums.ZoneTypes;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

public class Tile {

    private int posX;
    private int posY;
    public ZoneTypes zoneType;
    public StackPane tilePane;
    private SimulationObject object;
    public Tile(int posX, int posY,int mapWidth, int mapHeight, int screenWidth, int screenHeight)
    {
        this.posX = posX;
        this.posY = posY;
        this.zoneType = GetTileZoneType(mapWidth,mapHeight);
        this.object = null;

        tilePane = new StackPane();

        Rectangle rect = new Rectangle(screenWidth/(mapWidth+1),screenHeight/(mapHeight+1), GetTileColor());
        rect.setStyle(" -fx-stroke: grey; -fx-stroke-width: 2;");


        tilePane.getChildren().add(rect);
    }
    private ZoneTypes GetTileZoneType(int mapWidth, int mapHeight)
    {
        int zoneWidth = (int) Math.floor(mapWidth/4);
        int zoneHeight = (int) Math.floor(mapHeight/4);

        if(posX<(zoneWidth) && posY < zoneHeight)
            return ZoneTypes.SafeZoneTeam1;
        if(posX>=(mapWidth-zoneWidth) && posY < zoneHeight)
            return ZoneTypes.SafeZoneTeam2;
        if(posX<(zoneWidth) && posY >= (mapHeight-zoneHeight))
            return ZoneTypes.SafeZoneTeam3;
        if(posX>=(mapWidth-zoneWidth) && posY >= (mapHeight-zoneHeight))
            return ZoneTypes.SafeZoneTeam4;
        else
            return ZoneTypes.Neutral;
    }
    private Color GetTileColor()
    {
        if(zoneType.equals(ZoneTypes.Neutral)) return Color.LIGHTGREY;

        if(zoneType.equals(ZoneTypes.SafeZoneTeam1))
            return Color.LIGHTCORAL;
        if(zoneType.equals(ZoneTypes.SafeZoneTeam2))
            return Color.PALEVIOLETRED;
        if(zoneType.equals(ZoneTypes.SafeZoneTeam3))
            return Color.LIGHTGREEN;
        else
            return Color.LIGHTBLUE;
    }

    public SimulationObject GetTileObject()
    {
        return object;
    }
    public void SetTileObject(SimulationObject object)
    {
        tilePane.getChildren().add(object.graphObj);
        this.object = object;
    }
    public int getPosY() {
        return posY;
    }
    public int getPosX() {
        return posX;
    }

}
