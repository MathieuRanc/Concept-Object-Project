package com.example.conceptobjectproject;

import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Map extends Parent {

    public GridPane map;

    public Map(int mapWidth, int mapHeight)
    {
        map = new GridPane();

        map.setPadding(new Insets(20));
        map.setHgap(1);
        map.setVgap(1);

        for (int i = 0; i < mapWidth; i++) {
            for (int j = 0; j < mapHeight; j++) {
                map.add(new Rectangle( 50,50,(i+j) %2 ==0 ? Color.RED : Color.GREEN),i,j);
            }
        }

    }
    @Override
    public Node getStyleableNode() {
        return super.getStyleableNode();
    }
}
