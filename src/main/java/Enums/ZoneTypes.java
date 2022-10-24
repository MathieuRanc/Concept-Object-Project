package Enums;

import javafx.scene.paint.Color;

public enum ZoneTypes {
        Neutral(Color.GRAY) ,
        SafeZoneTeam1(Color.RED),
        SafeZoneTeam2(Color.DARKVIOLET),
        SafeZoneTeam3(Color.GREEN),
        SafeZoneTeam4(Color.BLUE);

        public final Color beingsColor;
        private ZoneTypes(Color color)
        {
                this.beingsColor = color;
        }
}

