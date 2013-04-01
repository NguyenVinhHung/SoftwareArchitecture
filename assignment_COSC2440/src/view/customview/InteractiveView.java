/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package view.customview;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JPanel;
import main.Main;

/**
 * 
 * @author HungHandsome
 */
public abstract class InteractiveView extends JPanel {
    
    protected boolean hover;
    protected int x;
    protected int y;
    protected int width;
    protected int height;
    
    public InteractiveView(int x, int y, int width, int height) {
        setOpaque(false);
        setViewBound(x, y, width, height);
        
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                onClick();
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                hover = true;
                Main.getInstance().getCurrPanel().repaint();
            }

            @Override
            public void mouseExited(MouseEvent e) {
                hover = false;
                Main.getInstance().getCurrPanel().repaint();
            }
            
        });
    }
    
    public abstract void draw(Graphics g);
    
    /**
     * Handle button click event.
     */
    public abstract void onClick();

    public void setViewBound(int x, int y, int width, int height) {
        setBounds(x, y, width, height);
        setPreferredSize(new Dimension(width, height));
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    public void setX(int x) {
        setBounds(x, y, width, height);
    }
    
    public void setY(int y) {
        setBounds(x, y, width, height);
    }
    
    public void setWidth(int width) {
        setBounds(x, y, width, height);
    }
    
    public void setHeight(int height) {
        setBounds(x, y, width, height);
    }

    public boolean isHover() {
        return hover;
    }

    public void setHover(boolean hover) {
        this.hover = hover;
    }
}
