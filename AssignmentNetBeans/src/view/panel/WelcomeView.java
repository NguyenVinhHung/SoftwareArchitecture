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
    
    private JPanel playBtn;
    private RawButton regBtn;
    
    public WelcomeView() {
        bg = new ImageIcon(FileUtility.WELCOME_IMG_URL).getImage();
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

        //////////////////////////////////////////////////////////////////////////////////////////////////////////////
        regBtn = new RawButton("Register", 400, 628, RawButton.DEFAULT_WIDTH, RawButton.DEFAULT_HEIGHT) {
            @Override
            public void onClick() {
                System.out.println("Register");
//                Main.getInstance().pushPanel(new RegisterView());
                Main.getInstance().toRegisterView();
                regBtn.setHover(false);
            }
        };

        add(regBtn);
        //////////////////////////////////////////////////////////////////////////////////////////////////////////////
    }

    @Override
    protected void paintComponent(Graphics g) {
        g.drawImage(bg, 0, 0, getWidth(), getHeight(), null);
        regBtn.draw(g);
    }
    
    private void playBtnClicked() {
//        Main.getInstance().pushPanel(new LoginView());
        Main.getInstance().toLoginView();
    }
}
