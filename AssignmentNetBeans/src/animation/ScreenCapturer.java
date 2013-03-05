/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package animation;

import java.awt.Image;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * A utility class used to capture image.
 * @author Hung
 */
public class ScreenCapturer {

    private static Image screenShot;
    private static Image hiddenImg;

    /**
     * Capture an image of the current view of the game.
     * @param x The x of the dialog
     * @param y The y of the dialog
     * @param w The width of the dialog
     * @param h The height of the dialog
     * @return The image of the current view of the game.
     */
//    public static BufferedImage captureScreen(int x, int y, int w, int h) {
//        try {
//            Robot robot = new Robot();
//
//            screenShot = robot.createScreenCapture(
//                                           new Rectangle(x + 3, y + 25, w, h));
//        } catch (Exception ex) {
//            screenShot = null;
//        }
//        
//        return screenShot;
//    }
    
    /**
     * Capture an image of the current view of the game.
     * @param frame The main frame containing the view.
     * @return 
     */
    public static Image captureScreen(JFrame frame) {
        screenShot = null;
        screenShot = frame.createImage(
                                    frame.getContentPane().getSize().width, 
                                    frame.getContentPane().getSize().height);
        frame.getContentPane().paint(screenShot.getGraphics());
        
        return screenShot;
    }
    
    public static Image captureScreen(JPanel panel) {
        screenShot = panel.createImage(
                                   (int)(panel.getPreferredSize().getWidth()), 
                                   (int)(panel.getPreferredSize().getHeight()));
        panel.paint(screenShot.getGraphics());
        
        return screenShot;
    }
    
    /**
     * Capture an image of a hidden panel of the game.
     * @param panel The hidden panel.
     * @return 
     */
    public static Image captureHiddenPanel(JPanel panel) {
        hiddenImg = panel.createImage(panel.getWidth(), panel.getHeight());
        panel.paint(hiddenImg.getGraphics());
        
        return hiddenImg;
    }

    public static void resetScreenShot() {
        System.out.println("Reset ScreenShot");
        screenShot = null;
    }
    
    public static Image getScreenShot() {
        return screenShot;
    }

    public static Image getHiddenImg() {
        return hiddenImg;
    }
    
}