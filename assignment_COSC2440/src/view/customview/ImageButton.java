/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package view.customview;

import java.awt.Graphics;
import java.awt.Image;

/**
 *
 * @author HungHandsome
 */
public abstract class ImageButton extends InteractiveView {

    private Image normalImg;
    private Image hoverImg;
    
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
    
}
