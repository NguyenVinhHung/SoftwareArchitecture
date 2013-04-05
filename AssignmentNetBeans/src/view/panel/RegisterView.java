package view.panel;

import main.Main;
import server.Server;
import server.Services;
import utility.FileUtility;
import view.customview.RawButton;
import view.popup.AlertPopup;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

/**
 * Created with IntelliJ IDEA.
 * User: HungHandsome
 * Date: 3/30/13
 * Time: 12:47 PM
 * To change this template use File | Settings | File Templates.
 */
public class RegisterView extends CustomPanel {

    public static final int TEXT_FIELD_COLS = 30;
    public static final int TEXT_FIELD_WIDTH = 200;
    public static final int TEXT_FIELD_HEIGHT = 30;
    public static final int X1 = 238;
    public static final int X2 = X1 + 210;
    public static final int X3 = X2 + 210;
    public static final int Y1 = 291;
    public static final int Y2 = Y1 + 40;
    public static final int Y3 = Y2 + 40;
    public static final int OK_X = 420;
    public static final int CANCEL_X = OK_X + 120;
    public static final int BUTTON_Y = 425;

//    private Image popup;
    private JTextField user;
    private JPasswordField password;
    private JPasswordField confirmPw;
    private RawButton okBtn;
    private RawButton cancelBtn;

    public RegisterView() {
//        bg = new ImageIcon(FileUtility.WELCOME_IMG_URL).getImage();
//        popup = new ImageIcon(FileUtility.POPUP_IMG_URL).getImage();
        user = new JTextField(TEXT_FIELD_COLS);
        password = new JPasswordField(TEXT_FIELD_COLS);
        confirmPw = new JPasswordField(TEXT_FIELD_COLS);

        setLayout(null);

        user.setBounds(X2, Y1, TEXT_FIELD_WIDTH, TEXT_FIELD_HEIGHT);
        password.setBounds(X2, Y2, TEXT_FIELD_WIDTH, TEXT_FIELD_HEIGHT);
        confirmPw.setBounds(X2, Y3, TEXT_FIELD_WIDTH, TEXT_FIELD_HEIGHT);

        add(user);
        add(password);
        add(confirmPw);

        initButtons();
        initPasswordField();
    }

    @Override
    protected void paintComponent(Graphics g) {
        g.drawImage(FileUtility.WELCOME_IMG, 0, 0, getWidth(), getHeight(), null);
        g.drawImage(FileUtility.POPUP_IMG, 0, 0, getWidth(), getHeight(), null);

        g.setColor(Color.WHITE);

        g.setFont(new Font("", Font.BOLD, 20));
        g.drawString("Username", X1, Y1+20);
        g.drawString("Password", X1, Y2+20);
        g.drawString("Confirm password", X1, Y3+20);

        if(!password.getText().equals(confirmPw.getText())) {
            g.setFont(new Font("", Font.PLAIN, 12));
            g.setColor(Color.RED);
            g.drawString("Password & Confirmed Password must be the same", X3, Y3+20);
        }

        okBtn.draw(g);
        cancelBtn.draw(g);
    }

    private void initButtons() {
        okBtn = new RawButton("Register", OK_X, BUTTON_Y,
                RawButton.DEFAULT_WIDTH, RawButton.DEFAULT_HEIGHT) {
            @Override
            public void onClick() {
                System.out.println("Register");
                okClicked();
            }
        };

        cancelBtn = new RawButton("Cancel", CANCEL_X, BUTTON_Y,
                RawButton.DEFAULT_WIDTH, RawButton.DEFAULT_HEIGHT) {
            @Override
            public void onClick() {
                System.out.println("Cancel");
                reset();
                Main.getInstance().popPanel();
            }
        };

        add(okBtn);
        add(cancelBtn);
    }

    private void initPasswordField() {
        password.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
//                checkPwFieldAndRepaint();
                repaint();
            }
        });
        confirmPw.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
//                checkPwFieldAndRepaint();
                repaint();
            }
        });
    }

    private void checkPwFieldAndRepaint() {
        System.out.println("Text change");
        if(!password.getText().equals(confirmPw.getText())) {
            repaint();
        }
    }

    public void okClicked() {
        String pw = password.getText();
        String cpw = confirmPw.getText();

        if(pw.isEmpty() ||  !pw.equals(cpw)) {
            return;
        }

        try {
            Socket s = new Socket(Server.IP, Server.PORT_NUM);
            ObjectOutputStream output = new ObjectOutputStream(s.getOutputStream());
            ObjectInputStream input = new ObjectInputStream(s.getInputStream());

            output.writeInt(Services.REGISTER);
            output.writeUTF(user.getText());
            output.writeUTF(pw);
            output.flush();

            afterOkClicked(input);

            output.close();
            input.close();
        } catch(Exception ex) {
        }
    }

    private void afterOkClicked(ObjectInputStream input) throws IOException {
        int result = input.readInt();

        switch(result) {
            case Services.REGISTER_SUCCESS:
                System.out.println("Adding new account success");
                Main.getInstance().pushPanel(new AlertPopup("Register Successful"));
                break;
            case Services.REGISTER_FAILED_DUPLICATE_NAME:
                System.out.println("Adding account failed due to duplicate name");
                Main.getInstance().pushPanel(new AlertPopup("This username already exist. PLease type another."));
                break;
            case Services.REGISTER_FAILED:
                System.out.println("Adding account has error");
                Main.getInstance().pushPanel(new AlertPopup("Register failed"));
        }
    }

    private void reset() {
        user.setText("");
        password.setText("");
        confirmPw.setText("");
        cancelBtn.setHover(false);
    }
}