package com.example.conceptobjectproject;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Tile {

    public int posX;
    public int posY;
    public Enums.ZoneTypes zoneType;

    public Rectangle tile;

    public Tile(int posX, int posY,int mapWidth, int mapHeight, int screenWidth, int screenHeight)
    {
        this.posX = posX;
        this.posY = posY;
        this.zoneType = GetTileZoneType(mapWidth,mapHeight);

        tile=new Rectangle(screenWidth/(mapWidth+1),screenHeight/(mapHeight+1), GetTileColor());
        tile.setStyle(" -fx-stroke: grey; -fx-stroke-width: 2;");
    }
    private Enums.ZoneTypes GetTileZoneType(int mapWidth, int mapHeight)
    {
        int zoneWidth = (int) Math.floor(mapWidth/4);
        int zoneHeight = (int) Math.floor(mapHeight/4);

        if(posX<(zoneWidth) && posY < zoneHeight)
            return Enums.ZoneTypes.SafeZoneTeam1;
        if(posX>=(mapWidth-zoneWidth) && posY < zoneHeight)
            return Enums.ZoneTypes.SafeZoneTeam2;
        if(posX<(zoneWidth) && posY >= (mapHeight-zoneHeight))
            return Enums.ZoneTypes.SafeZoneTeam3;
        if(posX>=(mapWidth-zoneWidth) && posY >= (mapHeight-zoneHeight))
            return Enums.ZoneTypes.SafeZoneTeam4;
        else
            return Enums.ZoneTypes.Neutral;
    }
    private Color GetTileColor()
    {
        if(zoneType.equals(Enums.ZoneTypes.Neutral)) return Color.LIGHTGREY;

        if(zoneType.equals(Enums.ZoneTypes.SafeZoneTeam1))
            return Color.INDIANRED;
        if(zoneType.equals(Enums.ZoneTypes.SafeZoneTeam2))
            return Color.PALEVIOLETRED;
        if(zoneType.equals(Enums.ZoneTypes.SafeZoneTeam3))
            return Color.LIGHTGREEN;
        else
            return Color.LIGHTBLUE;
    }

    public int getVerticalIndex() {
        return verticalIndex;
    }
    public int getHorizontalIndex() {
        return horizontalIndex;
    }
    public SimulationObject getObject() {
        return obj;
    }
    public void SimulationObject setObject(obj SimulationObject) {}
}
