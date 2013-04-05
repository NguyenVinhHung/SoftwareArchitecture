package view.panel;

import main.Main;
import model.room.RoomPublicInfo;
import server.Services;
import server.SocketCommunicator;
import view.customview.RawButton;

import javax.swing.*;
import java.awt.*;

/**
 * Created with IntelliJ IDEA.
 * User: HungHandsome
 * Date: 3/31/13
 * Time: 10:02 PM
 * To change this template use File | Settings | File Templates.
 */
public class CreateRoomView extends AfterLoginTemplate {

    public static final int X1 = LAYER_X + 230;
    public static final int X2 = X1 + 170;
    public static final int Y1 = BG_Y + 80;
    public static final int Y2 = Y1 + 40;
    public static final int BTN_Y = Y2 + 60;
    public static final int BTN_X2 = X1 + RawButton.DEFAULT_WIDTH + 10;

    private JComboBox typeBox;
    private RawButton okBtn;
    private RawButton cancelBtn;
    private String creator;
    private int selectedType;

    public CreateRoomView() {
        SocketCommunicator sc = Main.getCommunicator();
        creator = sc.getUsername();
        typeBox = new JComboBox(new Integer[] {
            new Integer(1), new Integer(2), new Integer(3), new Integer(4), new Integer(5)
        });

        typeBox.setBounds(X2, Y2, 50, 30);

        remove(createBtn);
        initBtns();
        add(typeBox);
    }

    private void initBtns() {
        okBtn = new RawButton("Ok", X1, BTN_Y, RawButton.DEFAULT_WIDTH, RawButton.DEFAULT_HEIGHT) {
            @Override
            public void onClick() {
                okClicked();
            }
        };

        cancelBtn = new RawButton("Cancel", BTN_X2, BTN_Y, RawButton.DEFAULT_WIDTH, RawButton.DEFAULT_HEIGHT) {
            @Override
            public void onClick() {
                cancelClicked();
            }
        };

        add(okBtn);
        add(cancelBtn);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        drawTransparentLayer(g, LAYER_X, BG_Y+1, LAYER_W, BG_H);

        g.setFont(new Font("", Font.BOLD, 20));
        g.setColor(Color.YELLOW);

        g.drawString("Creator:", X1, Y1);
        g.drawString(creator, X2, Y1);
        g.drawString("Players per team:", X1, Y2+20);

        okBtn.draw(g);
        cancelBtn.draw(g);
    }

    private void okClicked() {
        SocketCommunicator sc = Main.getCommunicator();
        sc.sendRequestHeader(Services.CREATE_ROOM);
//        sc.write(sc);
        sc.write(new Integer(typeBox.getSelectedIndex() + 1));
        sc.flushOutput();

        RoomPublicInfo result = (RoomPublicInfo)sc.read();

        Main.getInstance().setCurrPanel(new RoomView(result));
        Main.getInstance().clearPanelStack();
    }

    private void cancelClicked() {
        Main.getInstance().popPanel();
    }
}
