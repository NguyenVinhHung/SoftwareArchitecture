/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package view.map;

import java.awt.Color;
import java.awt.Graphics;

/**
 *
 * @author HungHandsome
 */
public class GameMap {
    
    private int[][] mapArrays;
    
    public GameMap(int[][] mapArrs) {
        mapArrays = mapArrs;
    }
    
    public void draw(Graphics g) {
        for(int i=0, tileY=0; i<mapArrays.length; i++) {
            for(int j=0, tileX=0; j<mapArrays[i].length; j++) {
                g.setColor(MapUtil.getColor(mapArrays[i][j]));
                g.fillRect(tileX, tileY, MapUtil.TILE_SIZE, MapUtil.TILE_SIZE);
                tileX += MapUtil.TILE_SIZE;
            }            
            tileY += MapUtil.TILE_SIZE;
        }
        
        g.setColor(Color.white);
        for(int i=0, x=MapUtil.TILE_SIZE; i<mapArrays[0].length-1; i++) {
            g.drawLine(x, 0, x, MapUtil.MAP_H);
            x += MapUtil.TILE_SIZE;
        }
        for(int i=0, y=MapUtil.TILE_SIZE; i<mapArrays.length-1; i++) {
            g.drawLine(0, y, MapUtil.MAP_W, y);
            y += MapUtil.TILE_SIZE;
        }
    }
}
