/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package view.custombutton;

import java.awt.Graphics;
import java.awt.Image;

/**
 *
 * @author HungHandsome
 */
public abstract class ImageButton extends AbsCusButton {

    private Image normalImg;
    private Image hoverImg;
    
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
}
