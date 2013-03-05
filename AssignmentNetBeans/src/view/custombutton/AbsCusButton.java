/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package view.custombutton;

import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JPanel;

/**
 * Abstract custom button
 * @author HungHandsome
 */
public abstract class AbsCusButton extends JPanel {
    
    protected boolean hover;
    protected int x;
    protected int y;
    protected int width;
    protected int height;
    
    public AbsCusButton() {
        setOpaque(false);
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                onClick();
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                hover = true;
            }

            @Override
            public void mouseExited(MouseEvent e) {
                hover = false;
            }
            
        });
    }
    
    public abstract void draw(Graphics g);
    
    /**
     * Handle button click event.
     */
    public abstract void onClick();
}
