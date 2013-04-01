package view.smallview;

import model.RoomPublicInfo;
import view.customview.InteractiveView;
import view.customview.RawButton;
import view.panel.AfterLoginTemplate;

import java.awt.*;

/**
 * Created with IntelliJ IDEA.
 * User: HungHandsome
 * Date: 3/31/13
 * Time: 7:31 PM
 * To change this template use File | Settings | File Templates.
 */
public class RoomSelectorView extends InteractiveView {

    public static final int ROOMNO_X = AfterLoginTemplate.LAYER_X + 10;
    public static final int TYPE_X = ROOMNO_X + 150;
    public static final int HOST_X = TYPE_X + TYPE_X;
    public static final int TEXT_Y = 25;

    private Color normalCol;
    private Color hoverCol;
    private Color borderCol;
    private RoomPublicInfo model;
    private int roomIdx;

    public RoomSelectorView(RoomPublicInfo model, int roomIndex, int y) {
        super(AfterLoginTemplate.LAYER_X, y, AfterLoginTemplate.LAYER_W, RawButton.DEFAULT_HEIGHT);
        this.model = model;
        roomIdx = roomIndex;
        normalCol = RawButton.DEFAULT_NORMAL_COL;
        hoverCol = RawButton.DEFAULT_HOVER_COL;
        borderCol = RawButton.DEFAULT_BORDER_COL;
    }

    @Override
    public void draw(Graphics g) {
        if(hover) {
            g.setColor(hoverCol);
        } else {
            g.setColor(normalCol);
        }
        g.fillRect(x, y, width, height);

        g.setColor(borderCol);
        g.drawRect(x, y, width, height);
        g.setColor(borderCol);

        g.setFont(new Font("", Font.PLAIN, 20));
        g.drawString(model.getRoomNo(), ROOMNO_X, TEXT_Y);
        g.drawString(model.getType(), TYPE_X, TEXT_Y);
        g.drawString(model.getHostname(), HOST_X, TEXT_Y);
    }

    @Override
    public void onClick() {
        System.out.println(model.getRoomNo() + " - " + model.getType() + " - " + model.getHostname());
    }
}
