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
class Drop {
    Pane gameRoot;
    Circle drop;

    Drop(double x, double y, Pane gameRoot) {
        this.gameRoot = gameRoot;
        drop = new Circle(x, y, 3);
        drop.setFill(Color.CHARTREUSE);
        gameRoot.getChildren().add(drop);
    }
    
    public void healthPickUp(Player P1){
        if (P1.player.getBoundsInParent().intersects(drop.getBoundsInParent())) {
            gameRoot.getChildren().remove(drop);
            P1.Health = P1.Health + 5;
        }
    }
}
