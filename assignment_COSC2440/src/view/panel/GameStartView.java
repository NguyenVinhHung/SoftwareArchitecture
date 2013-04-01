package view.panel;

import main.Main;
import model.RoomPublicInfo;
import server.Services;
import server.SocketCommunicator;
import view.customview.RawButton;
import view.smallview.RoomSelectorView;

import java.awt.*;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.net.Socket;
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
        rooms = new ArrayList<RoomSelectorView>();
//        loadRoomInfoList();
    }

    public void loadRoomInfoList() {
//        Socket s = Main.getCommunicator().getSocket();
//        ObjectInputStream from = Main.getCommunicator().getFrom();
//        ObjectOutputStream to = Main.getCommunicator().getTo();
        SocketCommunicator sc = Main.getCommunicator();

        System.out.println("Retreive room list send header");
        sc.sendRequestHeader(Services.ROOM_LIST);
//        sc.sendRequestHeader(-10);
        sc.write(new Object());

        System.out.println("Retreive room list get list");
        ArrayList<RoomPublicInfo> roomInfos = (ArrayList<RoomPublicInfo>)sc.read();

        System.out.println("Retreive room list create array");
        for(int i=0, rsvY=BG_Y; i<roomInfos.size(); i++) {
            RoomSelectorView rsv = new RoomSelectorView(roomInfos.get(i), i, rsvY);
            rooms.add(rsv);
            add(rsv);
            rsvY += RawButton.DEFAULT_HEIGHT;
        }

        System.out.println("Retreive room list end");
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        drawTransparentLayer(g, LAYER_X, BG_Y+1, LAYER_W, BG_H);

        for(RoomSelectorView r : rooms) {
            r.draw(g);
        }
    }
}
