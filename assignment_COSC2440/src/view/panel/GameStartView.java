package view.panel;

import main.Main;
import model.room.RoomPublicInfo;
import server.Services;
import server.SocketCommunicator;
import view.customview.RawButton;
import view.smallview.RoomSelectorView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA.
 * User: HungHandsome
 * Date: 3/31/13
 * Time: 8:36 PM
 * To change this template use File | Settings | File Templates.
 */
public class GameStartView extends AfterLoginTemplate {

    private ArrayList<RoomSelectorView> rooms;

    public GameStartView() {
        loadRoomInfoList();

        Action reloadRoomList = new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                removeRoomSelectorViews();
                loadRoomInfoList();
                repaint();
            }
        };
        getInputMap().put(KeyStroke.getKeyStroke("F5"),"reloadRoomList");
        getActionMap().put("reloadRoomList", reloadRoomList);
    }

    private void loadRoomInfoList() {
        rooms = new ArrayList<RoomSelectorView>();
        SocketCommunicator sc = Main.getCommunicator();
        sc.sendRequestHeader(Services.ROOM_LIST);
        sc.flushOutput();

//        System.out.println("Retreive room list get list");
        ArrayList<RoomPublicInfo> roomInfos = (ArrayList<RoomPublicInfo>)sc.read();

//        System.out.println("Retreive room list create array");
        for(int i=0, rsvY=BG_Y+20; i<roomInfos.size(); i++) {
            RoomSelectorView rsv = new RoomSelectorView(roomInfos.get(i), i, rsvY);
            rooms.add(rsv);
            add(rsv);
            rsvY += RawButton.DEFAULT_HEIGHT;
        }

//        for(RoomPublicInfo r : roomInfos) {
//            System.out.println(r);
//        }

//        repaint();
//        System.out.println("Retreive room list end");
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        drawTransparentLayer(g, LAYER_X, BG_Y+1, LAYER_W, BG_H);

        for(RoomSelectorView r : rooms) {
            r.draw(g);
        }
    }

    private void removeRoomSelectorViews() {
        for(RoomSelectorView r : rooms) {
            remove(r);
        }
    }
}
