/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ascendance;

import javafx.geometry.Point2D;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

/**
 *
 * @author Ranjeth
 */
class Bullet {

        
    Circle bullet;
    boolean firing;

    double xVector, yVector;
    
    double x,y, speed;

    public Bullet(double x, double y, double mouseX, double mouseY) {
        this.x = x;
        this.y = y;
        
        this.speed = 6;
        
        firing = true;
        
        bullet = new Circle(x, y, 5);
        bullet.setFill(Color.BURLYWOOD);

        Point2D playerVector = new Point2D(x, y);
        Point2D bulletVector = new Point2D(mouseX, mouseY);
        Point2D direction = bulletVector.subtract(playerVector).normalize();

        xVector = direction.getX();
        yVector = direction.getY();
    }

    public boolean isFiring() {
        return firing;
    }
    
    public Circle getBullet() {
        return bullet;
    }

    public void setBullet(Circle bullet) {
        this.bullet = bullet;
    }
    
    public void move(){
        if (firing) {
            x += xVector * speed;
            y += yVector * speed;
        }

        bullet.setCenterX(x);
        bullet.setCenterY(y);
    }


}
