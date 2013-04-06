/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package view.map;

import model.pokemon.PokeInBattleInfo;
import utility.FileUtility;
import view.smallview.PokeInBattleView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 *
 * @author HungHandsome
 */
public class GameMap {

    public static final int WIDTH = 803;
    public static final int HEIGHT = 657;
    public static final int NUM_ROWS = 9;
    public static final int NUM_COLS = 11;

    private PokeInBattleInfo[] pokeModels1;
    private PokeInBattleInfo[] pokeModels2;
    private PokeInBattleView[] pokeViews1;
    private PokeInBattleView[] pokeViews2;
    private JPanel[][] tilePanels;
    private PokeInBattleInfo myPoke;
    private MatchPanel parent;
    private int[][] mapArrays;
    private int selectedX = -MapUtil.TILE_SIZE;
    private int selectedY = -MapUtil.TILE_SIZE;
    private boolean isMyTurn;
    
    public GameMap(final MatchPanel parent, int[][] mapArrs) {
        this.parent = parent;
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
                        if(!isMyTurn) {
                            return;
                        }

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

        if(isMyTurn) {

        }

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


    public boolean isMyTurn() {
        return isMyTurn;
    }

    public void setMyTurn(boolean myTurn) {
        isMyTurn = myTurn;
    }

    public PokeInBattleInfo[] getPokeModels1() {
        return pokeModels1;
    }

    public void setPokeModels1(PokeInBattleInfo[] pokeModels1) {
        this.pokeModels1 = pokeModels1;
        pokeViews1 = new PokeInBattleView[pokeModels1.length];

        for(int i=0; i<pokeModels1.length; i++) {
            pokeViews1[i] = new PokeInBattleView(pokeModels1[i]);
        }
        parent.repaint();
    }

    public PokeInBattleInfo[] getPokeModels2() {
        return pokeModels2;
    }

    public void setPokeModels2(PokeInBattleInfo[] pokeModels2) {
        this.pokeModels2 = pokeModels2;
        pokeViews2 = new PokeInBattleView[pokeModels2.length];

        for(int i=0; i<pokeModels2.length; i++) {
            pokeViews2[i] = new PokeInBattleView(pokeModels2[i]);
        }
        parent.repaint();
    }

    public PokeInBattleInfo getMyPoke() {
        return myPoke;
    }

    public void setMyPoke(PokeInBattleInfo myPoke) {
        this.myPoke = myPoke;
    }
}
