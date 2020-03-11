/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ascendance;

import java.util.ArrayList;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

/**
 *
 * @author 9087
 */
class Drop {
    Pane gameRoot;
    Circle drop;

    Drop(double x, double y, Pane gameRoot, Color colour) {
        this.gameRoot = gameRoot;
        drop = new Circle(x, y, 5);
        drop.setFill(colour);
        gameRoot.getChildren().add(drop);
    }
 
}
