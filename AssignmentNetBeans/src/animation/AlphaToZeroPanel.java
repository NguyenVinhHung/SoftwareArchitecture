/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package animation;

import org.pushingpixels.trident.Timeline;
import java.awt.*;
import org.pushingpixels.trident.swing.SwingRepaintTimeline;

/**
 *
 * @author Hung
 */
public class AlphaToZeroPanel {
    
    protected Timeline timeline;
    protected SwingRepaintTimeline repaintTimeline;
    protected float alpha;

    public AlphaToZeroPanel(SwingRepaintTimeline repaintTimeline) {
        timeline = new Timeline(this);        
        alpha = 1.0f;
        
        timeline.addPropertyToInterpolate("alpha", 1.0f, 0.0f);
        timeline.setDuration(400);
        this.repaintTimeline = repaintTimeline;
    }

    public AlphaComposite makeComposite() {
        int type = AlphaComposite.SRC_OVER;
        return (AlphaComposite.getInstance(type, alpha));
    }
    
    public void playAnimation() {
        timeline.replay();
    }
    
    public void reverseAnimation() {
        timeline.replayReverse();
    }

    public float getAlpha() {
        return alpha;
    }

    public void setAlpha(float alpha) {
        this.alpha = alpha;
//        this.repaintTimeline.forceRepaintOnNextPulse(); 
    }

    public Timeline getTimeline() {
        return timeline;
    }
    
}
