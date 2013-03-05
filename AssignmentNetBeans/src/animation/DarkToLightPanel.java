/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package animation;

import java.awt.*;
import org.pushingpixels.trident.Timeline;
import org.pushingpixels.trident.swing.SwingRepaintTimeline;

/**
 *
 * @author Hung
 */
public class DarkToLightPanel {

//    public static final int MIN_ALPHA = 30;
//    public static final int MAX_ALPHA = 110;
    
    private Color backgroundColor;
    private Timeline timeLine;
    private SwingRepaintTimeline repaintTimeline;
    private Color fromColor;
    private Color toColor;
    private int fromAlpha;
    private int toAlpha;

    public DarkToLightPanel(SwingRepaintTimeline repaintTimeline,
                                  Color baseColor, int fromAlpha, int toAlpha) {
        Color c = baseColor;
        this.fromAlpha = fromAlpha;
        this.toAlpha = toAlpha;
        fromColor = new Color(c.getRed(), c.getGreen(), c.getBlue(), fromAlpha); 
        toColor = new Color(c.getRed(), c.getGreen(), c.getBlue(), toAlpha);
        
        backgroundColor = fromColor;
        timeLine = new Timeline(this);
        timeLine.addPropertyToInterpolate("backgroundColor", fromColor, toColor);
        timeLine.setDuration(1500);
        this.repaintTimeline = repaintTimeline;
    }

    public void drawPanel(Graphics g, int width, int height) {        
        g.setColor(backgroundColor);
        g.fillRect(0, 0, width, height);
        
//        if(backgroundColor.getAlpha() == fromAlpha) {
//            timeLine.replay();   
//        } else if (backgroundColor.getAlpha() == toAlpha) {
//            timeLine.replayReverse();
//        }
    }
    
    public void drawPanel(Graphics g, int x, int y, int width, int height) {        
        g.setColor(backgroundColor);
        g.fillRect(x, y, width, height);
        
//        if(backgroundColor.getAlpha() == fromAlpha) {
//            timeLine.replay();   
//        } else if (backgroundColor.getAlpha() == toAlpha) {
//            timeLine.replayReverse();
//        }
    }
    
    public void playAnimation() {        
        timeLine.replay();    
    }
    
    public void reverseAnimation() {
        timeLine.replayReverse();       
    }
    
    public void setBackgroundColor(Color backgroundColor) {
        this.backgroundColor = backgroundColor;
//        repaintTimeline.forceRepaintOnNextPulse();
    }

    public Color getBackgroundColor() {
        return backgroundColor;
    }

    public Timeline getTimeLine() {
        return timeLine;
    }

    public Color getFromColor() {
        return fromColor;
    }

    public void setFromColor(Color fromColor) {
        this.fromColor = fromColor;
    }

    public Color getToColor() {
        return toColor;
    }

    public void setToColor(Color toColor) {
        this.toColor = toColor;
    }

    public int getFromAlpha() {
        return fromAlpha;
    }

    public void setFromAlpha(int fromAlpha) {
        this.fromAlpha = fromAlpha;
    }

    public int getToAlpha() {
        return toAlpha;
    }

    public void setToAlpha(int toAlpha) {
        this.toAlpha = toAlpha;
    }
    
}
