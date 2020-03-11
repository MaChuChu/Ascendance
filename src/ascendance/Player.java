/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ascendance;

import java.util.ArrayList;
import javafx.scene.Node;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

/**
 *
 * @author Ranjeth
 */
public class Player {
    
    Pane gameRoot, uiRoot;
    double x,y, radius;
    double Health, Attack;
    Text healthText;
    Rectangle healthBar, healthBarBackground;
    boolean isAlive;
    Circle player;
    
    private boolean up, down, left, right;
    double xSpeed, ySpeed;
    
    ArrayList<Bullet> Bullets;
    MapGeneration map;
    String[] currentLevel;
    
    Player(Pane gameRoot, Pane uiRoot, MapGeneration map) {
        radius = 20;
        x = 1024/2 - radius; 
        y = 768/2 - radius;
        player = new Circle(x, y, radius);
        player.setFill(Color.AQUA);
        Bullets = new ArrayList();
        Health = 100;
        Attack = 5;
        xSpeed = ySpeed = 7;
        isAlive = true;
        currentLevel = map.currentLevel;
        
        this.gameRoot = gameRoot;
        this.uiRoot = uiRoot;
        gameRoot.getChildren().add(player);
        
        healthBar = new Rectangle(20, 20, Health, 20);
        healthBar.setFill(Color.DARKSEAGREEN);
        healthBarBackground = new Rectangle(20, 20, Health, 20);
        healthBarBackground.setFill(Color.AZURE);
        healthBarBackground.setStroke(Color.AZURE);
        healthText = new Text(30, 37, Double.toString(Health));
        healthText.setFill(Color.AQUA);
        healthText.setFont(Font.font(null, FontWeight.BOLD, 20));
        uiRoot.getChildren().addAll(healthBarBackground, healthBar, healthText);
        
    }
    
    public void keyPressed(KeyEvent code) {
        KeyCode kc = code.getCode();
        switch(kc){
            case W:
                up = true;
                break;
            case S:
                down = true;
                break;
            case A:
                left = true;
                break;
            case D:
                right = true;
                break; 
        }
        
    }
    
    public void keyReleased(KeyEvent code) {
        KeyCode kc = code.getCode();
        switch(kc){
            case W:
                up = false;
                break;
            case S:
                down = false;
                break;
            case A:
                left = false;
                break;
            case D:
                right = false;
                break;

        }
    } 
    
    
    public void movePlayer(ArrayList<Node> edges) {
        int playerTileX = (int)x/64;
        int playerTileY = (int)y/64;
        
        if(right && currentLevel[playerTileY].charAt(playerTileX + 1) != '1'){
            x+=xSpeed;
        }
        if(left && currentLevel[playerTileY].charAt(playerTileX - 1) != '1'){
            x -= xSpeed;
        }
        if(up && currentLevel[playerTileY - 1].charAt(playerTileX) != '1'){
            y -= ySpeed;
        }
        if(down && currentLevel[playerTileY + 1].charAt(playerTileX) != '1'){
            y += ySpeed;
        }
        
        
        player.setCenterX(x);
        player.setCenterY(y);
    }

    public void shootBullet(double mouseX, double mouseY) {
        Bullet newBullet = new Bullet(x, y, mouseX, mouseY);
        Bullets.add(newBullet);
        gameRoot.getChildren().add(newBullet.bullet);
    }
           
}
