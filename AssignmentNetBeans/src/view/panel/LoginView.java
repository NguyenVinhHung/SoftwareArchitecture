/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package view.panel;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import javax.swing.ImageIcon;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import main.Main;
import utility.FileUtility;
import view.customview.RawButton;

/**
 *
 * @author HungHandsome
 */
public class LoginView extends CustomPanel {  
    
    public static final int TEXT_FIELD_COLS = 30;
    public static final int TEXT_FIELD_WIDTH = 200;
    public static final int TEXT_FIELD_HEIGHT = 30;
    public static final int X1 = 338;
    public static final int Y1 = 331;
    public static final int X2 = X1 + 110;
    public static final int Y2 = Y1 + 40;
    public static final int OK_X = 420;
    public static final int CANCEL_X = OK_X + 120;
    public static final int BUTTON_Y = 425;
    
//    private Image popup;
    private JTextField user;
    private JPasswordField password;
    private RawButton okBtn;
    private RawButton cancelBtn;
    
    public LoginView() {
        bg = new ImageIcon(FileUtility.WELCOME_IMG_URL).getImage();
//        popup = new ImageIcon(FileUtility.POPUP_IMG_URL).getImage();
        user = new JTextField(TEXT_FIELD_COLS);
        password = new JPasswordField(TEXT_FIELD_COLS);

        setLayout(null);

        user.setBounds(X2, Y1, TEXT_FIELD_WIDTH, TEXT_FIELD_HEIGHT);
        password.setBounds(X2, Y2, TEXT_FIELD_WIDTH, TEXT_FIELD_HEIGHT);

        add(user);
        add(password);

        initButtons();
    }

    @Override
    protected void paintComponent(Graphics g) {
        g.drawImage(bg, 0, 0, getWidth(), getHeight(), null);
        g.drawImage(FileUtility.POPUP_IMG, 0, 0, getWidth(), getHeight(), null);
        
        g.setColor(Color.WHITE);
        
        Font f = new Font("", Font.BOLD, 20);
        g.setFont(f);
        g.drawString("Username", X1, Y1+20);
        g.drawString("Password", X1, Y2+20);
        
        okBtn.draw(g);
        cancelBtn.draw(g);
    }
    
    private void initButtons() {
        okBtn = new RawButton("Login", OK_X, BUTTON_Y,
                RawButton.DEFAULT_WIDTH, RawButton.DEFAULT_HEIGHT) {
            @Override
            public void onClick() {
                System.out.println("Login");
            }
        };
        
        cancelBtn = new RawButton("Cancel", CANCEL_X, BUTTON_Y,
                RawButton.DEFAULT_WIDTH, RawButton.DEFAULT_HEIGHT) {
            @Override
            public void onClick() {
                System.out.println("Cancel");
                cancelBtn.setHover(false);
                Main.getInstance().popPanel();
            }
        };
        
        add(okBtn);
        add(cancelBtn);
    }
}
