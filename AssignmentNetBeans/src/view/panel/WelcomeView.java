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
import main.ViewUtilities;

/**
 *
 * @author HungHandsome
 */
public class WelcomeView extends CustomPanel {
    
    private JPanel playBtn;
    
    public WelcomeView() {
        bg = new ImageIcon(ViewUtilities.WELCOME_IMG).getImage();
        playBtn = new JPanel();
        
        setLayout(null);
        
        playBtn.setOpaque(false);
        playBtn.setBounds(228, 628, 131, 50);
        playBtn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                playBtnClicked();
            }            
        });
        
        add(playBtn);
        
        addMouseMotionListener(new MouseAdapter() {
            @Override
            public void mouseMoved(MouseEvent e) {
                System.out.println(e.getX() + " - " + e.getY());
            }            
        });
    }

    @Override
    protected void paintComponent(Graphics g) {
        g.drawImage(bg, 0, 0, getWidth(), getHeight(), null);
    }
    
    private void playBtnClicked() {
        Main.getInstance().switchPanel(new LoginView());
    }
}
