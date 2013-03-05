/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package view.customview;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;

/**
 *
 * @author HungHandsome
 */
public abstract class RawButton extends InteractiveView {
    
    public static final Color DEFAULT_NORMAL_COL = Color.BLACK;
    public static final Color DEFAULT_HOVER_COL = Color.YELLOW;
    public static final Color DEFAULT_BORDER_COL = Color.WHITE;
    public static final int DEFAULT_WIDTH = 100;
    public static final int DEFAULT_HEIGHT = 30;

    private Color normalCol;
    private Color hoverCol;
    private Color borderCol;
    private String text;
    
    public RawButton(String text, int x, int y, int width, int height) {
        super(x, y, width, height);
        this.text = text;
        normalCol = DEFAULT_NORMAL_COL;
        hoverCol = DEFAULT_HOVER_COL;
        borderCol = DEFAULT_BORDER_COL;
    }
    
    public RawButton(String text, int x, int y, int width, int height,
                     Color normalColor, Color hoverColor, Color borderColor) {
        super(x, y, width, height);
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
        
        g.setColor(borderCol);
        
        Font f = new Font("", Font.BOLD, 20);
        FontMetrics fm = g.getFontMetrics(f);
        int textX = x + (width - fm.stringWidth(text)) / 2;
        int textY = y + height - (height - f.getSize()) / 2;
        g.setFont(f);
        g.drawString(text, textX, textY);
//        System.out.println("X: " + x + " - Y: " + y);
    }
    
    /**
     * Handle button click event.
     */
    public abstract void onClick();

    public Color getNormalCol() {
        return normalCol;
    }

    public void setNormalCol(Color normalCol) {
        this.normalCol = normalCol;
    }

    public Color getHoverCol() {
        return hoverCol;
    }

    public void setHoverCol(Color hoverCol) {
        this.hoverCol = hoverCol;
    }

    public Color getBorderCol() {
        return borderCol;
    }

    public void setBorderCol(Color borderCol) {
        this.borderCol = borderCol;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
    
}
