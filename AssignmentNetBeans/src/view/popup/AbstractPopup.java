package view.popup;

import animation.ScreenCapturer;
import main.Main;
import utility.FileUtility;

import javax.swing.*;
import java.awt.*;

/**
 * Created with IntelliJ IDEA.
 * User: HungHandsome
 * Date: 3/30/13
 * Time: 10:16 PM
 * To change this template use File | Settings | File Templates.
 */
public class AbstractPopup extends JPanel {

    public AbstractPopup() {
        ScreenCapturer.captureScreen(Main.getInstance());
        setLayout(null);
    }

    @Override
    protected void paintComponent(Graphics g) {
        g.drawImage(ScreenCapturer.getScreenShot(), 0, 0, getWidth(), getHeight(), null);
        g.drawImage(FileUtility.POPUP_IMG, 0, 0, getWidth(), getHeight(), null);
    }
}
