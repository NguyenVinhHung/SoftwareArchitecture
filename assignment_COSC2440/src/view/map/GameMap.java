/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package view.map;

import main.Main;
import model.pokemon.PokeInBattleInfo;
import model.pokemon.PokeInBattleRequest;
import model.pokemon.PokeMoveRequest;
import org.pushingpixels.trident.Timeline;
import server.Room;
import server.Services;
import server.SocketCommunicator;
import utility.FileUtility;
import utility.PathFinding;
import view.anim.AnimUtil;
import view.smallview.PokeInBattleView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;


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
    private PokeInBattleInfo movingPoke;
    private MatchPanel parent;
    private ArrayList<PathFinding.Coordinate> validSteps;
    private int[][] mapArrays;
    private int moveI;
    private int moveJ;
    private int selectedX = -MapUtil.TILE_SIZE;
    private int selectedY = -MapUtil.TILE_SIZE;
    private int hoverX = -MapUtil.TILE_SIZE;
    private int hoverY = -MapUtil.TILE_SIZE;
    private boolean myTurn;

    public GameMap(final MatchPanel parent, int[][] mapArrs) {
        this.parent = parent;
        mapArrays = mapArrs;
        tilePanels = new JPanel[mapArrs.length][mapArrs[0].length];

        for (int i = 0, tileY = 0; i < NUM_ROWS; i++) {
            for (int j = 0, tileX = 0; j < NUM_COLS; j++) {
                tilePanels[i][j] = new JPanel();
                tilePanels[i][j].setBounds(tileX, tileY, MapUtil.TILE_SIZE, MapUtil.TILE_SIZE);
                tilePanels[i][j].setOpaque(false);
                parent.add(tilePanels[i][j]);

                final int tX = tileX;
                final int tY = tileY;
                tilePanels[i][j].addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseReleased(MouseEvent e) {
                        if (!myTurn) {
                            System.out.println("Not your turn");
                            return;
                        }

                        selectedX = tX;
                        selectedY = tY;
                        parent.repaint();

                        if (!myPoke.isDead()) {
                            System.out.println("Handle click");
                            handleMouseClick();
                        }
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
        g.drawImage(FileUtility.MAP_IMG, 0, 0, WIDTH, HEIGHT, null);

        String userName = Main.getCommunicator().getUsername();

        if (pokeViews1 != null) {
            for (int i = 0; i < pokeViews1.length; i++) {
                if (!pokeModels1[i].isDead()) {
                    g.setColor(Color.BLUE);
                    g.fillOval(pokeViews1[i].getX(), pokeViews1[i].getY(), MapUtil.TILE_SIZE, MapUtil.TILE_SIZE);

                    if (pokeModels1[i].getOwner().equals(userName)) {
                        g.setColor(Color.GREEN);
                        g.fillOval(pokeViews1[i].getX() + 10, pokeViews1[i].getY() + 10,
                                MapUtil.TILE_SIZE - 20, MapUtil.TILE_SIZE - 20);
                    }

                    pokeViews1[i].draw(g);
                }
            }

        }

        if (pokeViews2 != null) {
            for (int i = 0; i < pokeViews2.length; i++) {
                if (!pokeModels2[i].isDead()) {
                    g.setColor(Color.RED);
                    g.fillOval(pokeViews2[i].getX(), pokeViews2[i].getY(), MapUtil.TILE_SIZE, MapUtil.TILE_SIZE);

                    if (pokeModels2[i].getOwner().equals(userName)) {
                        g.setColor(Color.GREEN);
                        g.fillOval(pokeViews2[i].getX() + 10, pokeViews2[i].getY() + 10,
                                MapUtil.TILE_SIZE - 20, MapUtil.TILE_SIZE - 20);
                    }

                    pokeViews2[i].draw(g);
                }
            }
        }

        if (myTurn && validSteps != null) {
            Graphics2D g2d = (Graphics2D) g.create();
            int type = AlphaComposite.SRC_OVER;
            AlphaComposite alpha = AlphaComposite.getInstance(type, 0.5f);

            g2d.setComposite(alpha);
            g2d.setColor(Color.GREEN);


            for (int i = 0; i < validSteps.size(); i++) {
                PathFinding.Coordinate c = validSteps.get(i);
                g2d.fillRect(c.getX(), c.getY(), MapUtil.TILE_SIZE, MapUtil.TILE_SIZE);
            }
        }

        // Draw selected tile
        if (hoverX >= 0) {
            g.drawImage(MapUtil.SELECTED_TILE,
                    hoverX, hoverY, MapUtil.TILE_SIZE, MapUtil.TILE_SIZE, null);
//            g.fillRect(selectedX, selectedY, MapUtil.TILE_SIZE, MapUtil.TILE_SIZE);
        }
    }

    public void init() {
        SocketCommunicator sc = Main.getCommunicator();
        myTurn = sc.getUsername().equals(parent.getCurrPlayerName());

        PokeInBattleInfo[] myTeam;
        if (parent.isTeam1()) {
            myTeam = pokeModels1;
        } else {
            myTeam = pokeModels2;
        }

        for (PokeInBattleInfo p : myTeam) {
            if (p.getOwner().equals(sc.getUsername())) {
                myPoke = p;
                break;
            }
        }

        if(validSteps == null) {
            calculateValidSteps();
        }

//        setMyTurn(sc.getUsername().equals(parent.getCurrPlayerName()));
    }

    private void handleMouseClick() {
        int request = Services.BATTLE_MOVE;
        int enemyIndex = -1;
        PokeInBattleView[] enemyTeam = (myPoke.getTeamNo() == Room.TEAM_1) ? pokeViews2 : pokeViews1;
        PokeInBattleView enemy = null;

        boolean validStep = false;
        int selectedI = selectedX / MapUtil.TILE_SIZE;
        int selectedJ = selectedY / MapUtil.TILE_SIZE;

        for (PathFinding.Coordinate coordinate : validSteps) {
            if (coordinate.getI() == selectedI && coordinate.getJ() == selectedJ) {
                validStep = true;
                break;
            }
        }

        if (!validStep) {
            return;
        }

        for (int i = 0; i < enemyTeam.length; i++) {
            if (selectedX == enemyTeam[i].getX() && selectedY == enemyTeam[i].getY()) {
                if (enemyTeam[i].getModel().isDead()) {
                    continue;
                }

                request = Services.BATTLE_ATK;
                enemyIndex = i;
                enemy = enemyTeam[i];
                break;
            }
        }

        if (request == Services.BATTLE_ATK) {
//            int teamNo = (parent.isTeam1()) ? Room.TEAM_1 : Room.TEAM_2;

            PokeInBattleRequest requestObj = new PokeInBattleRequest(enemy.getOwner(), myPoke.getTeamNo());
            requestObj.setPokeModels1(pokeModels1);
            requestObj.setPokeModels2(pokeModels2);

            parent.sendRequest(request, requestObj);
            return;
        }

        int pokeIndex = 0;
        PokeInBattleView[] myTeam = (myPoke.getTeamNo() == Room.TEAM_1) ? pokeViews1 : pokeViews2;

        for (;pokeIndex < myTeam.length; pokeIndex++) {
            if (selectedX == myTeam[pokeIndex].getX() && selectedY == myTeam[pokeIndex].getY()) {
                return;
            }
            if (myTeam[pokeIndex].getOwner().equals(myPoke.getOwner())) {
                break;
            }
        }

        PokeMoveRequest requestObj = new PokeMoveRequest(pokeIndex, myPoke.getTeamNo(),
                myPoke.getI(), myPoke.getJ(), selectedI, selectedJ, pokeModels1, pokeModels2);

        parent.sendRequest(request, requestObj);
    }

    public void calculateValidSteps() {
        if (myPoke == null ) {
            System.out.println("Poke");
        }
        if (mapArrays== null ) {
            System.out.println("map");
        }
        validSteps = PathFinding.findValidSteps(myPoke.getI(), myPoke.getJ(), mapArrays, myPoke.getType());
//                .findPath(new PathFinding.Coordinate(myPoke.getI(), myPoke.getJ()), mapArrays, 2, myPoke.getType());
    }

    public void moveAnim(int pokeIndex, int teamNo, int fromI, int fromJ, int toI, int toJ) {
        PokeInBattleInfo[] t = (teamNo == Room.TEAM_1) ? pokeModels1 : pokeModels2;
        movingPoke = t[pokeIndex];

        Timeline timeline = new Timeline(this);
        timeline.addPropertyToInterpolate("moveI", fromI, toI);
        timeline.addPropertyToInterpolate("moveJ", fromJ, toJ);
        timeline.setDuration(AnimUtil.MOVE_DURATION);
        timeline.play();

        new Thread() {
            @Override
            public void run() {
                try {
                    sleep(AnimUtil.MOVE_DURATION);
                } catch(Exception ex) {

                }
                calculateValidSteps();
            }
        }.start();
    }

    public void setMoveI(int moveI) {
        this.moveI = moveI;
        movingPoke.setI(moveI);
        System.out.println("MoveI: " + movingPoke.getI() + " - " + moveI);
        parent.repaint();
    }

    public void setMoveJ(int moveJ) {
        this.moveJ = moveJ;
        movingPoke.setJ(moveJ);
        System.out.println("MoveJ: " + movingPoke.getJ() + " - " + moveJ);
        parent.repaint();
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

        if (myTurn && myPoke.isDead()) {
            parent.handleEndTurnButton();
        }

        validSteps = PathFinding.findValidSteps(myPoke.getI(), myPoke.getJ(), mapArrays, myPoke.getType());
//                .findPath(new PathFinding.Coordinate(myPoke.getI(), myPoke.getJ()), mapArrays, 2, myPoke.getType());

        parent.repaint();
    }

    public PokeInBattleInfo[] getPokeModels1() {
        return pokeModels1;
    }

    public void setPokeModels1(PokeInBattleInfo[] pokeModels1) {
        this.pokeModels1 = pokeModels1;
        pokeViews1 = new PokeInBattleView[pokeModels1.length];

        for (int i = 0; i < pokeModels1.length; i++) {
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

        for (int i = 0; i < pokeModels2.length; i++) {
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

    public PokeInBattleInfo getMovingPoke() {
        return movingPoke;
    }

    public void setMovingPoke(PokeInBattleInfo movingPoke) {
        this.movingPoke = movingPoke;
    }
}
