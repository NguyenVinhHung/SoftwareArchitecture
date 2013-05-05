/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package view.map;

import main.Main;
import model.pokemon.PokeInBattleInfo;
import model.pokemon.PokeInBattleRequest;
import server.Room;
import server.Services;
import server.SocketCommunicator;
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
    private int hoverX = -MapUtil.TILE_SIZE;
    private int hoverY = -MapUtil.TILE_SIZE;
    private boolean myTurn;
    
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
                        if(!myTurn) {
                            System.out.println("Not your turn");
                            return;
                        }

                        selectedX = tX;
                        selectedY = tY;
                        parent.repaint();

                        handleMouseClick();
                    }

                    @Override
                    public void mouseEntered(MouseEvent e) {
                        hoverX = tX;
                        hoverY = tY;
                        parent.repaint();
//                        System.out.println("tX: " + tX + " - tY" + tY);
                    }
                });

                tileX += MapUtil.TILE_SIZE;
            }
            tileY += MapUtil.TILE_SIZE;
        }

        ////
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

        if(pokeViews1 != null) {
            for(int i=0; i<pokeViews1.length; i++) {
                pokeViews1[i].draw(g);
//                System.out.println("pokeViews1 " + i + " - " + pokeViews1[i]);
            }

        }

        if(pokeViews2 != null) {
            for(int i=0; i<pokeViews2.length; i++) {
                pokeViews2[i].draw(g);
//                System.out.println("pokeViews2 " + i + " - " + pokeViews2[i]);
            }
        }

        if(myTurn) {

        }

        // Draw selected tile
        if(hoverX >= 0) {
            g.drawImage(MapUtil.SELECTED_TILE,
                    hoverX, hoverY, MapUtil.TILE_SIZE, MapUtil.TILE_SIZE, null);
//            g.fillRect(selectedX, selectedY, MapUtil.TILE_SIZE, MapUtil.TILE_SIZE);
        }
    }

    public void init() {
        SocketCommunicator sc = Main.getCommunicator();
        myTurn = sc.getUsername().equals(parent.getCurrPlayerName());

        PokeInBattleInfo[] myTeam;
        if(parent.isTeam1()) {
            myTeam = pokeModels1;
        } else {
            myTeam = pokeModels2;
        }

        for(PokeInBattleInfo p : myTeam) {
            if(p.getOwner().equals(sc.getUsername())) {
                myPoke = p;
                break;
            }
        }
    }

    private void handleMouseClick() {
        int request = Services.BATTLE_MOVE;
        int enemyIndex = -1;
        PokeInBattleView[] enemyTeam = (myPoke.getTeamNo()==Room.TEAM_1) ? pokeViews2 : pokeViews1;
        PokeInBattleView enemy = null;

        for(int i=0; i<enemyTeam.length; i++) {
            if(selectedX==enemyTeam[i].getX() && selectedY==enemyTeam[i].getY()) {
                request = Services.BATTLE_ATK;
                enemyIndex = i;
                enemy = enemyTeam[i];
                break;
            }
        }

        if(request == Services.BATTLE_ATK) {
            int teamNo = (parent.isTeam1()) ? Room.TEAM_1 : Room.TEAM_2;

            PokeInBattleRequest requestObj = new PokeInBattleRequest(enemy.getOwner(), teamNo);
            requestObj.setPokeModels1(pokeModels1);
            requestObj.setPokeModels2(pokeModels2);

            parent.sendRequest(request, requestObj);
            return;
        }

        myPoke.setI(selectedX / MapUtil.TILE_SIZE);
        myPoke.setJ(selectedY / MapUtil.TILE_SIZE);

        parent.sendRequest(request, new PokeInBattleRequest(enemyIndex, pokeModels1, pokeModels2));
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
        return myTurn;
    }

    public void setMyTurn(boolean myTurn) {
        this.myTurn = myTurn;
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
//        if(pokeModels2 == null) {
//            return;
//        }

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
