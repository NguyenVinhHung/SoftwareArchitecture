/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package view.custombutton;

import java.awt.Color;
import java.awt.Graphics;

/**
 *
 * @author HungHandsome
 */
public abstract class RawButton extends AbsCusButton {
    
    public static final Color DEFAULT_NORMAL_COL = Color.BLACK;
    public static final Color DEFAULT_HOVER_COL = Color.YELLOW;
    public static final Color DEFAULT_BORDER_COL = Color.WHITE;

    private Color normalCol;
    private Color hoverCol;
    private Color borderCol;
    private String text;
    
    public RawButton(String text) {
        this.text = text;
        normalCol = DEFAULT_NORMAL_COL;
        hoverCol = DEFAULT_HOVER_COL;
        borderCol = DEFAULT_BORDER_COL;
    }
    
    public RawButton(String text,
            Color normalColor, Color hoverColor, Color borderColor) {
        this.text = text;
        normalCol = normalColor;
        hoverCol = hoverColor;
        borderCol = borderColor;
    }
    
    @Override
    public void draw(Graphics g) {
        if(hover) {
            g.setColor(hoverCol);
        } else {
            g.setColor(normalCol);
        }
        g.fillRect(x, y, width, height);
        
        g.setColor(borderCol);
        g.drawRect(x, y, width, height);
    }
    
    /**
     * Handle button click event.
     */
    public abstract void onClick();
}
