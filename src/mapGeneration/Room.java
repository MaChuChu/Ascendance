/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mapGeneration;

/**
 *
 * @author 9087
 */
public class Room {
    
    Boolean visited;
    int x, y;
    
    Room() {
        visited = false;
    }
    
    Room(int x, int y) {
        visited = false;
        
        this.x = x;
        this.y = y;
    }
    
}
