/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package view.map;

import java.awt.Graphics;
import javax.swing.JPanel;

/**
 *
 * @author HungHandsome
 */
public class MatchPanel extends JPanel {
    
    private GameMap map;
    
    public MatchPanel(GameMap m) {
        map = m;
    }

    public MatchPanel(int[][] mapArr) {
        map = new GameMap(this, mapArr);

        setLayout(null);

//        for(int i=0; i<map.getMapArrays().length; i++) {
//            for(int j=0; j<map.getMapArrays()[0].length; j++) {
//                add(map.getTilePanels()[i][j]);
//            }
//        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        map.draw(g);
    }
       
}
