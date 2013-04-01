package view.panel;

import main.Main;
import model.Player;
import server.SocketCommunicator;
import utility.FileUtility;
import view.customview.ImageButton;
import view.map.MapUtil;
import view.map.MatchPanel;
import view.smallview.SelectedPokeView;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.LinkedHashMap;

/**
 * Created with IntelliJ IDEA.
 * User: HungHandsome
 * Date: 4/1/13
 * Time: 7:51 AM
 * To change this template use File | Settings | File Templates.
 */
public class RoomView extends AfterLoginTemplate {

    private ArrayList<SelectedPokeView> g1;
    private ArrayList<SelectedPokeView> g2;
    private LinkedHashMap<String, Player> t1;
    private LinkedHashMap<String, Player> t2;
    private JTextArea chatBox;
    private JTextArea chatTyper;
    private boolean host;
    private boolean waiting = true;
    private int roomIdx;

    public RoomView() {
        SocketCommunicator sc = Main.getCommunicator();
        chatBox = new JTextArea();
        chatTyper = new JTextArea();

        try {
            roomIdx = sc.getFrom().readInt();
            host = sc.getFrom().readBoolean();
            t1 = (LinkedHashMap<String, Player>)sc.read();
            t2 = (LinkedHashMap<String, Player>)sc.read();
        } catch (Exception ex) {
        }

        remove(createBtn);

        createBtn = new ImageButton(CREATE_X, CREATE_Y, CREATE_W, CREATE_H,
                FileUtility.GS_BTN_IMG, FileUtility.GS_BTN_IMG) {
            @Override
            public void onClick() {
                if(!host) {
                    return;
                }

                System.out.println("on create room");
                waiting = false;
                Main.getInstance().pushPanel(new MatchPanel(MapUtil.MAP_ARRS[0]));
            }
        };

        createBtn.setText("Start");

//        chatBox.setBounds();
//        chatTyper.setBounds();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

    }
}
