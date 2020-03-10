/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ascendance;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

/**
 *
 * @author 9087
 */
class Drops {
    Pane gameRoot;
    Circle drop;

    Drops(double x, double y, Pane gameRoot) {
        this.gameRoot = gameRoot;
        drop = new Circle(x, y, 3);
        drop.setFill(Color.CHARTREUSE);
        gameRoot.getChildren().add(drop);
    }
    
}
