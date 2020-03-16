/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ascendance;

import java.util.Arrays;
import java.util.Iterator;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import mapGeneration.LevelGeneration;


/**
 *
 * @author Ranjeth
 *
 */
public class Ascendance extends Application {
    
    int width = 1024;
    int height = 768;
   
    Scene scene;
    final Pane appRoot = new Pane();
    final Pane gameRoot = new Pane();
    final Pane uiRoot = new Pane();
    
    MapGeneration map;
    
    Player P1; 
    final int bullet_maxTime = 7;
    private int bullet_timeRemain;
    
    final int enemy_maxTime = 100;
    private int enemy_timeRemain;
    
    int timeTotal = 300;

    private void initContent() {
        Rectangle bg = new Rectangle(width, height);
        bg.setFill(Color.BLACK);
        
        map = new MapGeneration(gameRoot);
        map.createRoom();
        
        P1 = new Player(gameRoot, uiRoot, map);

        appRoot.getChildren().addAll(bg, gameRoot, uiRoot);
        
        LevelGeneration test = new LevelGeneration();
        test.output();
    }
   
    private void gameLoop() {
        scene.setOnKeyPressed(new EventHandler<KeyEvent>(){
            @Override
            public void handle(KeyEvent event) {
                P1.keyPressed(event);
            }     
        });
        
        scene.setOnKeyReleased(new EventHandler<KeyEvent>(){
            @Override
            public void handle(KeyEvent event) {
                P1.keyReleased(event);
            }
        });
        
        bullet_timeRemain = 0; 
        enemy_timeRemain = 0;
        scene.setOnMouseDragged(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if (event.isPrimaryButtonDown() && bullet_timeRemain == 0) {
                    P1.shootBullet((int)event.getX(),(int)event.getY());
                    bullet_timeRemain = bullet_maxTime;
                }
            }
        });
        
        AnimationTimer timer = new AnimationTimer(){
            @Override
            public void handle(long now) {
                if (P1.isAlive) {
                    P1.movePlayer();
                }

                for (Enemy e: map.enemies) {
                    e.enemyMove(P1.x, P1.y);
                    e.onHit(P1, map);
                    enemy_timeRemain = enemy_maxTime;
                }

                if(bullet_timeRemain>0){
                    bullet_timeRemain--;
                }
                
                Iterator <Bullet>it = P1.Bullets.iterator();
                while(it.hasNext()){
                    Bullet bullet = it.next();
                    if(bullet.isFiring()){
                        bullet.move();
                    }else{
                        gameRoot.getChildren().remove(bullet.bullet);
                        P1.Bullets.remove(bullet);
                    }
                }
                
                map.dropPickUp(P1);
                map.exit(P1);
            }
        };
        timer.start();
    }
    
    @Override
    public void start(Stage primaryStage){
        scene = new Scene(appRoot);
        
        initContent();
        gameLoop();
        
        primaryStage.setTitle("Ascendance");
        primaryStage.setScene(scene);
        primaryStage.show();

        
    }
    

    public static void main(String[] args) {
        launch(args);
    }

}