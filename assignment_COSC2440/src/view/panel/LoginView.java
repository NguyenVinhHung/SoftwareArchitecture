/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package view.panel;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import main.Main;
import server.Server;
import server.Services;
import utility.FileUtility;
import view.customview.RawButton;
import view.popup.AlertPopup;

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
//        bg = new ImageIcon(FileUtility.WELCOME_IMG_URL).getImage();
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
        g.drawImage(FileUtility.WELCOME_IMG, 0, 0, getWidth(), getHeight(), null);
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
                okClicked();
            }
        };
        
        cancelBtn = new RawButton("Cancel", CANCEL_X, BUTTON_Y,
                RawButton.DEFAULT_WIDTH, RawButton.DEFAULT_HEIGHT) {
            @Override
            public void onClick() {
                cancelClicked();
            }
        };
        
        add(okBtn);
        add(cancelBtn);
    }

    private void okClicked() {
        System.out.println("Login");
        String username = user.getText();
        String pw = password.getText();

        if(pw.isEmpty() ||  username.isEmpty()) {
            return;
        }

        try {
            Socket s = new Socket(Server.IP, Server.MAIN_PORT);
            ObjectOutputStream output = new ObjectOutputStream(s.getOutputStream());
            ObjectInputStream input = new ObjectInputStream(s.getInputStream());

            sendRequest(output, username, pw);
            afterOkClicked(s, output, input);
        } catch(Exception ex) {
        }
    }

    private void cancelClicked() {
        System.out.println("Cancel");
        cancelBtn.setHover(false);
        user.setText("");
        password.setText("");
        Main.getInstance().popPanel();
    }

    private void sendRequest(ObjectOutputStream output, String username, String pw) throws IOException {
        output.writeInt(Services.LOGIN);
        output.writeUTF(username);
        output.writeUTF(pw);
        output.flush();
    }

    private void afterOkClicked(Socket s, ObjectOutputStream output, ObjectInputStream input) throws IOException {
        int result = input.readInt();

        switch(result) {
            case Services.LOGIN_SUCCESS:
                System.out.println("Login successful");
                Main.getInstance().toStartGame(s, output, input);
                break;
            case Services.LOGIN_WRONG_USER:
                System.out.println("Login wrong username");
                Main.getInstance().pushPanel(new AlertPopup("This username does not exist."));
                output.close();
                input.close();
                s.close();
                break;
            case Services.LOGIN_WRONG_PW:
                System.out.println("Login wrong password");
                Main.getInstance().pushPanel(new AlertPopup("Password not match"));
                output.close();
                input.close();
                s.close();
        }
    }
}
