/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mapGeneration;

import java.util.Arrays;

/**
 *
 * @author 9087
 */
public class LevelGeneration {
    
    Room[][] map;
    int size;
    boolean visited;
    
    
    public LevelGeneration() {
        size = 5;
        map = new Room[size][size];
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[0].length; j++) {
                map[i][j] = null;
            }
        }
        
        map[size/2][size/2] = new Room();
        
    }
    
    public void generateMap(){
        
    }

    public void output() {
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[0].length; j++) {
                System.out.print(map[i][j]+" ");
            }
            System.out.println(" ");
        }
    } 
}
