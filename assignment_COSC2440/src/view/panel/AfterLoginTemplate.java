package view.panel;

import main.ClientSpring;
import main.Main;
import server.SocketCommunicator;
import utility.FileUtility;
import view.customview.ImageButton;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * Created with IntelliJ IDEA.
 * User: HungHandsome
 * Date: 3/31/13
 * Time: 11:09 AM
 * To change this template use File | Settings | File Templates.
 */
public class AfterLoginTemplate extends CustomPanel {

    public static final int LOGO_W = 178;
    public static final int LOGO_H = 150;
    public static final int BG_Y = 150;
    public static final int BG_H = 618;
    public static final int NAME_LBL_X = 714;
    public static final int NAME_LBL_Y = 17;
    public static final int CREATE_X = 444;
    public static final int CREATE_Y = 70;
    public static final int CREATE_W = 136;
    public static final int CREATE_H = 53;
    public static final int LAYER_X = 147;
    public static final int LAYER_W = 720;

    protected ImageButton createBtn;
    protected ImageButton usernameLabel;

    public AfterLoginTemplate() {
        setLayout(null);

        initBtns();
        initLabel();

        add(createBtn);
        add(usernameLabel);
    }

    private void initBtns() {
        createBtn = new ImageButton(CREATE_X, CREATE_Y, CREATE_W, CREATE_H,
                FileUtility.GS_BTN_IMG, FileUtility.GS_BTN_IMG) {
            @Override
            public void onClick() {
                System.out.println("on create room");
//                Main.getInstance().pushPanel((CreateRoomView)ClientSpring.getBean("createRoomView"));
                Main.getInstance().pushPanel(new CreateRoomView());
            }
        };

        createBtn.setText("Create");
    }

    private void initLabel() {
        SocketCommunicator sc = Main.getCommunicator();

        usernameLabel = new ImageButton(NAME_LBL_X, NAME_LBL_Y, CREATE_W * 2, CREATE_H,
                FileUtility.GS_BTN_IMG, FileUtility.GS_BTN_IMG) {
            @Override
            public void onClick() {}
        };

        usernameLabel.setText(sc.getUsername());
    }

    @Override
    protected void paintComponent(Graphics g) {
        g.fillRect(0, 0, getWidth(), getHeight());
        g.drawImage(FileUtility.LOGO_IMG, 0, 0, LOGO_W, LOGO_H, null);
        g.drawImage(FileUtility.GS_BG_IMG, 0, BG_Y, getWidth(), BG_H, null);

        //

        createBtn.draw(g);
        usernameLabel.draw(g);
    }

    protected void drawTransparentLayer(Graphics g, int x, int y, int w, int h) {
        Graphics2D g2 = (Graphics2D)g.create();
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.7f));
        g2.setColor(g2.getColor().darker());
//        g2.fillRect(LAYER_X, BG_Y+1, LAYER_W, BG_H);
        g2.fillRect(x, y, w, h);
    }
}
