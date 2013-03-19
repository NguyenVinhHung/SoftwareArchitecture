/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package view.map;

import java.awt.Color;
import java.awt.Image;
import javax.swing.ImageIcon;
import utility.FileUtility;

/**
 *
 * @author HungHandsome
 */
public class MapUtil {
    
    public static final int MAP_W = 1024;
    public static final int MAP_H = 768;
    public static final int TILE_SIZE = 73;
//    public static final int TILE_H = 30;
    
    public static final int GROUND = 0;
    public static final int GRASS = 1;
    public static final int WATER = 2;
    public static final int MAGMA = 3;
    public static final int SAND = 4;
    
    public static final Color[] TILE_COLORS = {
        Color.BLACK, Color.GREEN, Color.BLUE, Color.RED, Color.cyan
    };
    
    public static final Image TILES[] = {
        new ImageIcon(FileUtility.GROUND_TILE).getImage(),
        new ImageIcon(FileUtility.GRASS_TILE).getImage(),
        new ImageIcon(FileUtility.WATER_TILE).getImage(),
        new ImageIcon(FileUtility.MAGMA_TILE).getImage(),
        new ImageIcon(FileUtility.SAND_TILE).getImage()
    };
    
    public static final int MAP_ARRAYS[][][] = {
        {
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 1, 2, 2, 2, 0, 0, 0, 0, 0, 0},
            {0, 1, 2, 2, 2, 0, 0, 0, 0, 0, 0},
            {0, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0},
            {0, 1, 0, 0, 0, 0, 2, 2, 2, 0, 0},
            {0, 1, 0, 0, 1, 1, 2, 2, 2, 0, 0},
            {0, 0, 1, 1, 1, 1, 1, 1, 1, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}
        }
    };
    
    public static Color getColor(int colorIndex) {
        return TILE_COLORS[colorIndex];
    }
}
