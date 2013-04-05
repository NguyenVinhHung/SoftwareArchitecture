/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package view.map;

import model.pokemon.PokeInBattleInfo;
import server.Server;
import server.SocketCommunicator;
import utility.FileUtility;
import view.smallview.PokeInBattleView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.Socket;

/**
 *
 * @author HungHandsome
 */
public class GameMap {

    public static final int WIDTH = 803;
    public static final int HEIGHT = 657;
    public static final int NUM_ROWS = 9;
    public static final int NUM_COLS = 11;

    private PokeInBattleInfo[] pokeModels;
    private PokeInBattleView[] pokeViews;
    private JPanel[][] tilePanels;
    private int[][] mapArrays;
    private int selectedX = -MapUtil.TILE_SIZE;
    private int selectedY = -MapUtil.TILE_SIZE;
    
    public GameMap(final MatchPanel parent, int[][] mapArrs) {
        mapArrays = mapArrs;
        tilePanels = new JPanel[mapArrs.length][mapArrs[0].length];

        for(int i=0, tileY=0; i<NUM_ROWS; i++) {
            for(int j=0, tileX=0; j<NUM_COLS; j++) {
                tilePanels[i][j] = new JPanel();
                tilePanels[i][j].setBounds(tileX, tileY, MapUtil.TILE_SIZE, MapUtil.TILE_SIZE);
                tilePanels[i][j].setOpaque(false);
                parent.add(tilePanels[i][j]);

                final int tX = tileX;
                final int tY = tileY;
                tilePanels[i][j].addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseReleased(MouseEvent e) {
                        selectedX = tX;
                        selectedY = tY;
                        parent.repaint();
                    }
                });

                tileX += MapUtil.TILE_SIZE;
            }
            tileY += MapUtil.TILE_SIZE;
        }
    }
    
    public void draw(Graphics g) {
        int  tileX = 0;
        int  tileY = 0;

        g.drawImage(FileUtility.MAP_IMG, 0, 0, WIDTH, HEIGHT, null);

        // Draw tiles
//        for(int i=0; i<mapArrays.length; i++) {
//            tileX = 0;
//            for(int j=0; j<mapArrays[i].length; j++) {
//                g.drawImage(MapUtil.TILES[mapArrays[i][j]],
//                      tileX, tileY, MapUtil.TILE_SIZE, MapUtil.TILE_SIZE, null);
//
//                tileX += MapUtil.TILE_SIZE;
//            }
//            tileY += MapUtil.TILE_SIZE;
//        }

        // Draw lines to separate tiles
//        g.setColor(Color.white);
//        for(int i=0, x=MapUtil.TILE_SIZE; i<mapArrays[0].length-1; i++) {
//            g.drawLine(x, 0, x, tileY);
//            x += MapUtil.TILE_SIZE;
//        }
//        for(int i=0, y=MapUtil.TILE_SIZE; i<mapArrays.length-1; i++) {
//            g.drawLine(0, y, tileX, y);
//            y += MapUtil.TILE_SIZE;
//        }

        // Draw selected tile
        if(selectedX >= 0) {
            g.drawImage(MapUtil.SELECTED_TILE,
                    selectedX, selectedY, MapUtil.TILE_SIZE, MapUtil.TILE_SIZE, null);
        }
    }

    public JPanel[][] getTilePanels() {
        return tilePanels;
    }

    public int[][] getMapArrays() {
        return mapArrays;
    }

//    class TileListener extends MouseAdapter {
//        @Override
//        public void mouseReleased(MouseEvent event) {
//
//        }
//    }
}
