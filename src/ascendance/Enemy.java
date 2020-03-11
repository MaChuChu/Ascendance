/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ascendance;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;
import javafx.geometry.Point2D;
import javafx.scene.Group;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

/**
 *
 * @author Ranjeth
 */
class Enemy {

    Pane gameRoot;
    Group healthBarStuff;
    Rectangle enemy, healthBar, healthBarBackground;
    double x, y;
    double Health, Attack;
    boolean isAlive;

    double xVector, yVector;
    ArrayList<Drop> drops;

    Enemy() {
        Random rand = new Random();

        Health = 50;
        Attack = 3;
        isAlive = true;

        enemy = new Rectangle(rand.nextInt(1024), rand.nextInt(768), 20, 20);
        enemy.setFill(Color.CADETBLUE);

        healthBar = new Rectangle(x - (Health / 2), y - 7, Health, 5);
        healthBar.setFill(Color.DARKSEAGREEN);
    }

    Enemy(Pane gameRoot) {
        drops = new ArrayList<>();
        
        Health = 50;
        Attack = 3;
        isAlive = true;

        this.gameRoot = gameRoot;
        Random rand = new Random();
        this.x = rand.nextInt(960);
        this.y = rand.nextInt(704);

        enemy = new Rectangle(x, y, 20, 20);
        enemy.setFill(Color.CADETBLUE);

        healthBar = new Rectangle(x, y - 7, Health, 5);
        healthBarBackground = new Rectangle(x, y - 7, Health, 5);
        healthBar.setFill(Color.DARKSEAGREEN);
        healthBarBackground.setFill(Color.ROSYBROWN);

        healthBarStuff = new Group();
        healthBarStuff.getChildren().addAll(healthBarBackground, healthBar);

        gameRoot.getChildren().add(enemy);
        //gameRoot.getChildren().add(healthBar);
        gameRoot.getChildren().add(healthBarStuff);
    }

    public void onHit(Player P1, MapGeneration map) {
        P1.Bullets.forEach((b) -> {
            for (Enemy enemy : map.enemies) {
                if (enemy.enemy.getBoundsInParent().intersects(b.bullet.getBoundsInParent())) {
                    b.firing = false;
                    gameRoot.getChildren().remove(b.bullet);
                    enemy.Health = enemy.Health - P1.Attack;
                    enemy.healthBar.setWidth(enemy.Health);
                    if (enemy.Health <= 0) {
                        onDrop();
                        gameRoot.getChildren().remove(enemy.enemy);
                        map.enemies.remove(enemy);
                        gameRoot.getChildren().remove(enemy.healthBarStuff);
                    }
                }
            }
        });
        for (Enemy enemy : map.enemies) {
            if (P1.player.getBoundsInParent().intersects(enemy.enemy.getBoundsInParent())) {
                P1.Health = P1.Health - enemy.Attack;
                P1.healthText.setText(Double.toString(P1.Health));
                P1.healthBar.setWidth(P1.Health);
                if (P1.Health <= 0) {
                    P1.isAlive = false;
                    System.exit(1);
                }
            }
        }

    }

    public void onDrop() {
        Drop healthUp = new Drop(x, y, gameRoot);
        drops.add(healthUp);
    }

    public void enemyMove(double playerX, double playerY) {
        Point2D playerVector = new Point2D(playerX, playerY);
        Point2D enemyVector = new Point2D(x, y);

        Point2D direction = playerVector.subtract(enemyVector).normalize();

        xVector = direction.getX();
        yVector = direction.getY();

        x += xVector;
        y += yVector;

        enemy.setX(x);
        enemy.setY(y);

        healthBar.setX(x);
        healthBar.setY(y - 7);

        healthBarBackground.setX(x);
        healthBarBackground.setY(y - 7);

    }

}
