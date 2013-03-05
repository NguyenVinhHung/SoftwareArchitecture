/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package view.panel;

import java.awt.Graphics;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import main.ViewUtilities;

/**
 *
 * @author HungHandsome
 */
public class LoginView extends CustomPanel {  
    
    public static final int TEXT_FIELD_COLS = 30;    
    
    private Image popup;
    private JTextField user;
    private JPasswordField password;
    
    public LoginView() {
        bg = new ImageIcon(ViewUtilities.WELCOME_IMG).getImage();
        popup = new ImageIcon(ViewUtilities.POPUP_IMG).getImage();
        user = new JTextField(TEXT_FIELD_COLS);
        password = new JPasswordField(TEXT_FIELD_COLS);
    }

    @Override
    protected void paintComponent(Graphics g) {
        g.drawImage(bg, 0, 0, getWidth(), getHeight(), null);
        g.drawImage(popup, 0, 0, getWidth(), getHeight(), null);
    }
       
}
