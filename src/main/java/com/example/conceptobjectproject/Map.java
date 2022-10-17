package com.example.conceptobjectproject;

import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class Map extends Parent {

    private GridPane map;

    public Map(int mapWidth, int mapHeight, Stage stage)
    {
        map = new GridPane();

        map.setPadding(new Insets(20));
        map.setHgap(1);
        map.setVgap(1);

        for (int i = 0; i < mapWidth; i++) {
            for (int j = 0; j < mapHeight; j++) {
                map.add(new Rectangle(stage.widthProperty().doubleValue(),stage.heightProperty().doubleValue(),(i+j) %2 ==0 ? Color.RED : Color.GREEN),i,j);
            }
        }

    }

    public GridPane GetMap()
    {
        return map;
    }
    public void OnScreenResize(float width, float height)
    {

    }
    @Override
    public Node getStyleableNode() {
        return super.getStyleableNode();
    }
}
