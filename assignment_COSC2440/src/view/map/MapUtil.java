/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package view.map;

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

    public static final int BROWN = 0;
    public static final int GRASS = 1;
    public static final int DIRT = 2;
    public static final int WATER = 3;
    public static final int MAGMA = 4;
    public static final int TREE_SHORT = 5;
    public static final int TREE_TALL = 6;
    public static final int TREE_UGLY = 7;
    public static final int BLACK_BLASTER = 8;
    public static final int RED_BLASTER = 9;

    public static final Image TILES[] = {
        new ImageIcon(FileUtility.TILES[BROWN]).getImage(),
        new ImageIcon(FileUtility.TILES[GRASS]).getImage(),
        new ImageIcon(FileUtility.TILES[DIRT]).getImage(),
        new ImageIcon(FileUtility.TILES[WATER]).getImage(),
        new ImageIcon(FileUtility.TILES[MAGMA]).getImage(),
        new ImageIcon(FileUtility.TILES[TREE_SHORT]).getImage(),
        new ImageIcon(FileUtility.TILES[TREE_TALL]).getImage(),
        new ImageIcon(FileUtility.TILES[TREE_UGLY]).getImage(),
        new ImageIcon(FileUtility.TILES[BLACK_BLASTER]).getImage(),
        new ImageIcon(FileUtility.TILES[RED_BLASTER]).getImage()
    };

    public static final Image SELECTED_TILE = new ImageIcon(FileUtility.SELECTED_TILE).getImage();

    public static final int MAP_ARRS[][][] = {
        {
            {BROWN, BROWN, BROWN, BROWN, BROWN, BROWN, BROWN, BROWN, BROWN, BROWN, BROWN},
            {BROWN, BROWN, DIRT, DIRT, DIRT, DIRT, DIRT, DIRT, DIRT, BROWN, BROWN},
            {BROWN, GRASS, WATER, WATER, WATER, DIRT, DIRT, BROWN, BROWN, DIRT, BROWN},
            {BROWN, GRASS, WATER, WATER, WATER, BROWN, BROWN, BROWN, BROWN, DIRT, BROWN},
            {BROWN, GRASS, GRASS, GRASS, BROWN, BROWN, BROWN, DIRT, DIRT, DIRT, BROWN},
            {BROWN, GRASS, BROWN, BROWN, BROWN, BROWN, WATER, WATER, WATER, DIRT, BROWN},
            {BROWN, GRASS, BROWN, BROWN, GRASS, GRASS, WATER, WATER, WATER, DIRT, BROWN},
            {BROWN, BROWN, GRASS, GRASS, GRASS, GRASS, GRASS, GRASS, GRASS, BROWN, BROWN},
            {BROWN, BROWN, BROWN, BROWN, BROWN, BROWN, BROWN, BROWN, BROWN, BROWN, BROWN}
        },

        {
            {BROWN, BROWN, BROWN, BROWN, BROWN, BROWN, BROWN, BROWN, BROWN, BROWN, BROWN, BROWN, BROWN, BROWN, BROWN, BROWN, BROWN, BROWN, BROWN},
            {BROWN, BROWN, BROWN, BROWN, 0, 0, 0, 0, 0, 0, 0},
            {0, 1, 2, 2, 2, 0, 0, 0, 0, 0, 0},
            {0, 1, 2, 2, 2, 0, 0, 0, 0, 0, 0},
            {0, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0},
            {0, 1, 0, 0, 0, 0, 2, 2, 2, 0, 0},
            {0, 1, 0, 0, 1, 1, 2, 2, 2, 0, 0},
            {0, 0, 1, 1, 1, 1, 1, 1, 1, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
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

}
