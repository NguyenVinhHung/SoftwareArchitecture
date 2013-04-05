package view.smallview;

import main.Main;
import model.room.RoomPublicInfo;
import server.Services;
import server.SocketCommunicator;
import view.customview.InteractiveView;
import view.customview.RawButton;
import view.panel.AfterLoginTemplate;
import view.panel.RoomView;

import java.awt.*;

/**
 * Created with IntelliJ IDEA.
 * User: HungHandsome
 * Date: 3/31/13
 * Time: 7:31 PM
 * To change this template use File | Settings | File Templates.
 */
public class RoomSelectorView extends InteractiveView {

    public static final int ROOMNO_X = AfterLoginTemplate.LAYER_X + 110;
    public static final int TYPE_X = ROOMNO_X + 200;
    public static final int HOST_X = TYPE_X + ROOMNO_X + 100;

    private Color normalCol;
    private Color hoverCol;
    private Color borderCol;
    private RoomPublicInfo model;
    private int roomIdx;
    private int textY;

    public RoomSelectorView(RoomPublicInfo model, int roomIndex, int y) {
        super(AfterLoginTemplate.LAYER_X, y, AfterLoginTemplate.LAYER_W, RawButton.DEFAULT_HEIGHT);
        this.model = model;
        roomIdx = roomIndex;
        normalCol = RawButton.DEFAULT_NORMAL_COL;
        hoverCol = RawButton.DEFAULT_HOVER_COL;
        borderCol = RawButton.DEFAULT_BORDER_COL;
        textY = y + 25;
    }

    @Override
    public void draw(Graphics g) {
        Graphics2D g2d = (Graphics2D)g.create();
        g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.7f));

        if(hover) {
            g2d.setColor(hoverCol);
            g.setColor(Color.BLACK);
        } else {
            g2d.setColor(normalCol);
            g.setColor(Color.WHITE);
        }
        g2d.fillRect(x, y, width, height);

        g.setFont(new Font("", Font.PLAIN, 20));
        g.drawString(model.getRoomNo(), ROOMNO_X, textY);
        g.drawString(model.getType(), TYPE_X, textY);
        g.drawString(model.getHostname(), HOST_X, textY);

        g.setColor(borderCol);
        g.drawRect(x, y, width, height);
    }

    @Override
    public void onClick() {
//        System.out.println(model.getRoomNo() + " - " + model.getType() + " - " + model.getHostname());
        SocketCommunicator sc = Main.getCommunicator();
        sc.sendRequestHeader(Services.GET_IN_ROOM);
        sc.write(model.getHostname());
        sc.flushOutput();

        System.out.println("GET IN ROOM Waiting for result");
        int result = (Integer)sc.read();
        System.out.println("GET IN ROOM Receive result");

        if(result == Services.GET_IN_ROOM_SUCCESS) {
            System.out.println("GET IN ROOM receive result success");
            RoomPublicInfo info = (RoomPublicInfo)sc.read();
            System.out.println("GET IN ROOM receive RoomPublicInfo");
            int updatedTeam = (Integer)sc.read();
            model = info;
            Main.getInstance().setCurrPanel(new RoomView(info, updatedTeam));

//            sc.sendRequestHeader(Services.NOTIFY);
//            sc.write(info.getHostname());
//            sc.write(new Integer(updatedTeam));

        } else if(result == Services.GET_IN_ROOM_FAILED) { //Handle this when the room is removed the request is in progress.
            System.out.println("GET IN ROOM receive result failed");
        } else {
            System.out.println("GET IN ROOM receive result else");
        }
    }
}
