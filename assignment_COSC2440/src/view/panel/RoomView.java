package view.panel;

import main.Main;
import model.pokemon.SelectedPokeInfo;
import model.room.RoomPublicInfo;
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

    private static final int LEFT_ROW_X = 0;
    private static final int RIGHT_ROW_X = 813;
    private static final int CHATBOX_X = 208;
    private static final int CHATBOX_Y = 490;
    private static final int CHATBOX_H = 198;
    private static final int CHATTYPER_Y = 690;
    private static final int CHATTYPER_H = 70;
    private static final int LAYER_W2 = 605;

    private ArrayList<SelectedPokeView> g1;
    private ArrayList<SelectedPokeView> g2;
    private LinkedHashMap<String, SelectedPokeInfo> t1;
    private LinkedHashMap<String, SelectedPokeInfo> t2;

    private JTabbedPane chatTabs;
    private JTextArea globalChat;
    private JTextArea teamChat;
    private JTextArea chatTyper;

    private RoomPublicInfo roomInfo;
    private boolean host;
    private boolean waiting = true;
//    private int roomIdx;

    public RoomView(RoomPublicInfo info) {
        roomInfo = info;
        SocketCommunicator sc = Main.getCommunicator();

//        try {
////            roomIdx = sc.getFrom().readInt();
////            host = sc.getFrom().readBoolean();
////            t1 = (LinkedHashMap<String, SelectedPokeInfo>)sc.read();
////            t2 = (LinkedHashMap<String, SelectedPokeInfo>)sc.read();
////            roomInfo = (RoomPublicInfo)sc.read();
//        } catch (Exception ex) {
//        }

        remove(createBtn);

        createBtn = new ImageButton(CREATE_X, CREATE_Y, CREATE_W, CREATE_H,
                FileUtility.GS_BTN_IMG, FileUtility.GS_BTN_IMG) {
            @Override
            public void onClick() {
                if(!host) {
                    return;
                }

                System.out.println("on start the match");
                waiting = false;
                Main.getInstance().pushPanel(new MatchPanel(MapUtil.MAP_ARRS[0]));
            }
        };

        createBtn.setText("Start");
        add(createBtn);

        initChatFeature();
        initSelectedPokeView();
    }

    private void initChatFeature() {
        chatTabs = new JTabbedPane();
        globalChat = new JTextArea();
        teamChat = new JTextArea();
        chatTyper = new JTextArea();

        chatTabs.setBounds(CHATBOX_X, CHATBOX_Y, LAYER_W2, CHATBOX_H);
//        globalChat.setBounds(CHATBOX_X, CHATBOX_Y, LAYER_W2, CHATBOX_H);
        chatTyper.setBounds(CHATBOX_X,CHATTYPER_Y, LAYER_W2, CHATTYPER_H);

        chatTabs.addTab("Global", globalChat);
        chatTabs.addTab("Team", teamChat);

//        add(globalChat);
        add(chatTabs);
        add(chatTyper);
    }

    private void initSelectedPokeView() {
        g1 = new ArrayList<SelectedPokeView>();
        g2 = new ArrayList<SelectedPokeView>();

        for(int i=0, y=BG_Y+1; i<roomInfo.getPlayersPerTeam(); i++) {
            g1.add(new SelectedPokeView(null, LEFT_ROW_X, y));
            g2.add(new SelectedPokeView(null, RIGHT_ROW_X, y));
            y += SelectedPokeView.SPV_HEIGHT;
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        drawTransparentLayer(g, SelectedPokeView.SPV_WIDTH, BG_Y+1, LAYER_W2, BG_H);

        for(int i=0; i<roomInfo.getPlayersPerTeam(); i++) {
            g1.get(i).draw(g);
            g2.get(i).draw(g);
        }
    }
}
