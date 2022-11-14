package Enums;

import javafx.scene.paint.Color;

public enum ZoneTypes {
        Neutral(Color.GRAY) ,
        Obstacle(Color.BLACK),
        SafeZoneTeam1(Color.RED),
        SafeZoneTeam2(Color.DARKVIOLET),
        SafeZoneTeam3(Color.GREEN),
        SafeZoneTeam4(Color.BLUE);

        public final Color objectColor;
        private ZoneTypes(Color color)
        {
                this.objectColor = color;
        }
}

