/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package view.customview;

import java.awt.*;

/**
 *
 * @author HungHandsome
 */
public abstract class ImageButton extends InteractiveView {

    private static final int FONT_SIZE = 22;

    private Image normalImg;
    private Image hoverImg;
    private String text = null;
    
    public ImageButton(int x, int y, int width, int height,
            Image normalImg, Image hoverImg) {
        super(x, y, width, height);
        this.normalImg = normalImg;
        this.hoverImg = hoverImg;
    }
    
    @Override
    public void draw(Graphics g) {
        if(hover) {
            g.drawImage(hoverImg, x, y, width, height, null);
        } else {
            g.drawImage(normalImg, x, y, width, height, null);
        }

        if(text != null) {
            g.setColor(Color.WHITE);
            g.setFont(new Font("", Font.BOLD, FONT_SIZE));

            FontMetrics fm = g.getFontMetrics();
            int textX = (width - fm.stringWidth(text)) / 2 + x;
            int textY = (height - FONT_SIZE) / 2 + y + 17;

            g.drawString(text, textX, textY);
        }
    }
    
    /**
     * Handle button click event.
     */
    public abstract void onClick();

    public void setNormalImg(Image normalImg) {
        this.normalImg = normalImg;
    }

    public void setHoverImg(Image hoverImg) {
        this.hoverImg = hoverImg;
    }

    public void setText(String text) {
        this.text = text;
    }
}
