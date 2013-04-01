/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package view.panel;

import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import main.Main;
import utility.FileUtility;
import view.customview.RawButton;

/**
 *
 * @author HungHandsome
 */
public class WelcomeView extends CustomPanel {

    public static final int PLAY_X = 433;
    public static final int REG_X = 1;
    public static final int BTN_Y = 630;
    public static final int BTN_W = 131;
    public static final int BTN_H = 50;

    private JPanel playBtn;
    private JPanel regBtn;
    
    public WelcomeView() {
//        bg = new ImageIcon(FileUtility.WELCOME_IMG_URL).getImage();
        playBtn = new JPanel();
        regBtn = new JPanel();
        
        setLayout(null);
        
        playBtn.setOpaque(false);
        playBtn.setBounds(PLAY_X, BTN_Y, BTN_W, BTN_H);
        playBtn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                playBtnClicked();
            }            
        });

        regBtn.setOpaque(false);
        regBtn.setBounds(REG_X, BTN_Y, BTN_W, BTN_H);
        regBtn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                regBtnClicked();
            }
        });

        add(playBtn);
        add(regBtn);
    }

    @Override
    protected void paintComponent(Graphics g) {
        g.drawImage(FileUtility.WELCOME_IMG, 0, 0, getWidth(), getHeight(), null);
    }
    
    private void playBtnClicked() {
        Main.getInstance().toLoginView();
    }

    private void regBtnClicked() {
        Main.getInstance().toRegisterView();
    }
}
