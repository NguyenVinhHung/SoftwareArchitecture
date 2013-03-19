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

    @Override
    protected void paintComponent(Graphics g) {
        map.draw(g);
    }
       
}
