/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package animation;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import org.pushingpixels.trident.Timeline;
import org.pushingpixels.trident.swing.SwingRepaintTimeline;

/**
 *
 * @author Hung
 */
public class SlowlyChangePageAnimation extends JPanel {

    public static final int NUM_ROW = 5;
    public static final int NUM_COL = 11;
    
    private Image img;
    private Image temp;
    private AlphaToZeroPanel[][] overlayPanels;
    private Timer timer[];
    private boolean clicked;

    public SlowlyChangePageAnimation() {
//        img = new ImageIcon("test1.jpg").getImage();
        img = ScreenCapturer.getScreenShot();
        timer = new Timer[NUM_COL];
        overlayPanels = new AlphaToZeroPanel[NUM_COL][NUM_ROW];

        SwingRepaintTimeline repaintTimeline = new SwingRepaintTimeline(this);
        repaintTimeline.setAutoRepaintMode(true);
        
        for (int i = 0; i < NUM_COL; i++) {
            for (int j = 0; j < NUM_ROW; j++) {
                overlayPanels[i][j] = new AlphaToZeroPanel(repaintTimeline);
            }            
            
            final int idx = i;
            
            timer[i] = new Timer(100, new ActionListener() {
                private int row = 0;
                public void actionPerformed(ActionEvent e) {
                    if(row == NUM_ROW) {
                        if(idx == NUM_COL-1) {
                            actAfterAnimcation();
                        } 
                        
                        timer[idx].stop();
                        timer[idx] = null;                 
                                               
                        return;
                    }
                    
                    overlayPanels[idx][row].playAnimation();

                    if(idx<NUM_COL-1 && row==1) {
                        timer[idx+1].start();
                    }

                    row++;
                }
            });            
        }
        
        repaintTimeline.playLoop(Timeline.RepeatBehavior.LOOP);
        
        temp = img;
//        img = new ImageIcon("Gameplay2.png").getImage();
        img = ScreenCapturer.getHiddenImg();
//        img = null;
        setBackground(Color.WHITE);

        timer[0].start();
    }

    @Override
    protected void paintComponent(Graphics g) {
        int w = getWidth() / (2*NUM_COL-2) * 2;
        int h = getHeight() / (2*NUM_ROW) * 2;
        Graphics2D g2d = (Graphics2D) g.create();
        Composite cps1 = g2d.getComposite();
        
        g2d.drawImage(img, 0, 0, getWidth(), getHeight(), null);

        for (int i = 0; i < NUM_COL; i++) {
            for (int j = 0; j < NUM_ROW; j++) {
//                AlphaToZeroPanel rect = overlayPanels[i][j];
                int x1 = i * w;
                int y1 = j * h;
                int x2 = x1 + w;
                int y2 = y1 + h;
                
                g2d.setComposite(overlayPanels[i][j].makeComposite());
                g2d.drawImage(temp, x1, y1, x2, y2,
                                    x1, y1, x2, y2, null);
            }
        }

        g2d.setComposite(cps1);
        g2d.dispose();
    }
    
    public void playAnimation() {
        timer[0].start();
    }
    
    protected void actAfterAnimcation() {
        
    }
}
