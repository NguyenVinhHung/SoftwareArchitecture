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

//    public static final int BROWN = 0;
//    public static final int GRASS = 1;
//    public static final int DIRT = 2;
//    public static final int WATER = 3;
//    public static final int MAGMA = 4;
//    public static final int TREE_SHORT = 5;
//    public static final int TREE_TALL = 6;
//    public static final int TREE_UGLY = 7;
//    public static final int BLACK_BLASTER = 8;
//    public static final int RED_BLASTER = 9;

//    public static final Image TILES[] = {
//        new ImageIcon(FileUtility.TILES[BROWN]).getImageURL(),
//        new ImageIcon(FileUtility.TILES[GRASS]).getImageURL(),
//        new ImageIcon(FileUtility.TILES[DIRT]).getImageURL(),
//        new ImageIcon(FileUtility.TILES[WATER]).getImageURL(),
//        new ImageIcon(FileUtility.TILES[MAGMA]).getImageURL(),
//        new ImageIcon(FileUtility.TILES[TREE_SHORT]).getImageURL(),
//        new ImageIcon(FileUtility.TILES[TREE_TALL]).getImageURL(),
//        new ImageIcon(FileUtility.TILES[TREE_UGLY]).getImageURL(),
//        new ImageIcon(FileUtility.TILES[BLACK_BLASTER]).getImageURL(),
//        new ImageIcon(FileUtility.TILES[RED_BLASTER]).getImageURL()
//    };

    public static final Image SELECTED_TILE = new ImageIcon(FileUtility.SELECTED_TILE_URL).getImage();

    public static final int MAP_ARRS[][][] = {
//        {
//            {BROWN, BROWN, BROWN, BROWN, BROWN, BROWN, BROWN, BROWN, BROWN, BROWN, BROWN},
//            {BROWN, BROWN, DIRT, DIRT, DIRT, DIRT, DIRT, DIRT, DIRT, BROWN, BROWN},
//            {BROWN, GRASS, WATER, WATER, WATER, DIRT, DIRT, BROWN, BROWN, DIRT, BROWN},
//            {BROWN, GRASS, WATER, WATER, WATER, BROWN, BROWN, BROWN, BROWN, DIRT, BROWN},
//            {BROWN, GRASS, GRASS, GRASS, BROWN, BROWN, BROWN, DIRT, DIRT, DIRT, BROWN},
//            {BROWN, GRASS, BROWN, BROWN, BROWN, BROWN, WATER, WATER, WATER, DIRT, BROWN},
//            {BROWN, GRASS, BROWN, BROWN, GRASS, GRASS, WATER, WATER, WATER, DIRT, BROWN},
//            {BROWN, BROWN, GRASS, GRASS, GRASS, GRASS, GRASS, GRASS, GRASS, BROWN, BROWN},
//            {BROWN, BROWN, BROWN, BROWN, BROWN, BROWN, BROWN, BROWN, BROWN, BROWN, BROWN}
//        },

        /*
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
        },*/

        {
            {2,2,2,2,2,2,2,2,2,2,34},
            {2,2,12,28,4,12,4,4,28,2,2},
            {2,11,1,1,1,20,4,2,2,28,2},
            {2,23,1,1,13,2,2,2,2,4,2},
            {2,3,3,11,2,2,2,12,4,4,2},
            {2,3,2,2,2,2,13,1,1,28,2},
            {2,23,2,2,3,19,1,1,1,12,2},
            {2,2,23,3,3,11,3,23,11,2,2},
            {30,2,2,2,2,2,2,2,2,2,2}
        }
    };


//    public int[] findPath(int [] src, int numOfSteps, int po) {
//
//    }

//    public static final int[][] START_POSTION_TEAM1 = {
//
//    };
}
