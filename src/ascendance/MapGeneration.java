/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ascendance;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import javafx.scene.Node;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

/**
 *
 * @author Ranjeth
 */
class MapGeneration {
    Pane gameRoot;
    ArrayList<Node> edges;
    ArrayList<Node> exits;
    ArrayList<Node> objects;
    ArrayList<Enemy> enemies;
    ArrayList<Drop> healthUp;
    
    public String[][][] layout;
    String[] currentLevel;
    
    MapGeneration(Pane gameRoot) {
        this.gameRoot = gameRoot;
        
        edges = new ArrayList<>();
        exits = new ArrayList<>();
        enemies = new ArrayList<>();
        objects = new ArrayList<>();
        healthUp = new ArrayList<>();
        
        currentLevel = LevelData.L;
        
        Random rand = new Random();
        //3d array
        //2d map layout, each element contains a 1D array of the level
        // by not specifying final dimension, can have a ragged array
        // therefore rooms can be of different sizes
        layout = new String[4][4][];
        layout[0][0]= LevelData.L;
        for (int i = 0; i < 10; i++) {
            int max = layout[0][0].length;
            int x = rand.nextInt(max-2)+2;
            int y = rand.nextInt(max-2)+2;
            layout[0][0][y] = layout[0][0][y].substring(0,x+1) + Integer.toString(rand.nextInt(3)+2) + layout[0][0][y].substring(x+2);
        }
        System.out.println(Arrays.toString(layout[0][0]));
        
    }
    
    public void createRoom(){
        for (int i = 0; i < LevelData.noExit.length; i++) {
            String line = currentLevel[i];
            for (int j = 0; j < line.length(); j++) {
                switch (line.charAt(j)) {
                    case '0':
                        //floor
                        break;
                    case '1':
                        //edge
                        Node edge = createEntity(j * 64, i * 64, 64, 64, Color.BROWN);
                        edges.add(edge);
                        break;
                    case '2':
                        //Exit
                        Node exit = createEntity(j * 64, i * 64, 64, 64, Color.GOLD);
                        exits.add(exit);
                        break;
                    case '3':
                        //objectOne
                        Node objectOne = createEntity(j * 64, i * 64, 64, 64, Color.BLUEVIOLET);
                        objects.add(objectOne);
                        break;
                    case '4':
                        //objectTwo
                        Node objectTwo = createEntity(j * 64, i * 64, 64, 64, Color.BEIGE);
                        objects.add(objectTwo);
                        break;
                                            
                }
            }
        }
        
        for (int i = 0; i < 10; i++) {
            Enemy e = new Enemy(gameRoot);
            enemies.add(e);
        }
        
        
    }
    
    private Node createEntity(int x, int y, int w, int h, Color color) {
        
        Rectangle entity = new Rectangle(x, y, w, h);
        entity.setFill(color);

        gameRoot.getChildren().add(entity);
        return entity;
    }
    
    public void createLayout(){
        
    }

    public void dropPickUp(Player P1) {
        for (Drop drop: healthUp) {
            if (drop.drop.getBoundsInParent().intersects(P1.player.getBoundsInParent())) {
                P1.Health+=5;
                P1.UpdateStats();
                gameRoot.getChildren().remove(drop.drop);
                healthUp.remove(drop);
            }
        }
    }
    
}
