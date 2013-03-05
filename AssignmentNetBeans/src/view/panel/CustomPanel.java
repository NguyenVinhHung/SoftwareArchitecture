/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package view.panel;

import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JPanel;

/**
 *
 * @author HungHandsome
 */
public abstract class CustomPanel extends JPanel {
    
    protected Image bg;
    
    public CustomPanel() {
        addMouseMotionListener(new MouseAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                System.out.println(e.getX() + " - " + e.getY());
            }            
        });
    }
    
}
