package view.popup;

import main.Main;
import view.customview.RawButton;

import java.awt.*;

/**
 * Created with IntelliJ IDEA.
 * User: HungHandsome
 * Date: 3/31/13
 * Time: 5:21 AM
 * To change this template use File | Settings | File Templates.
 */
public class AlertPopup extends AbstractPopup {

    public static final int START_Y = 290;
    public static final int FONT_SIZE = 15;
    public static final int OK_X = 450;
    public static final int OK_Y = 425;

    private RawButton okBtn;
    private String msg;

    public AlertPopup(String message) {
        msg = message;

        okBtn = new RawButton("Ok", OK_X, OK_Y, RawButton.DEFAULT_WIDTH, RawButton.DEFAULT_HEIGHT) {
            @Override
            public void onClick() {
                okClicked();
            }
        };

        add(okBtn);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        FontMetrics fm = g.getFontMetrics();
        int x = (getWidth() - fm.stringWidth(msg)) / 2;

        g.setFont(new Font("", Font.PLAIN, FONT_SIZE));
        g.setColor(Color.WHITE);
        g.drawString(msg, x, START_Y);

        okBtn.draw(g);
    }

    public void okClicked() {
        Main.getInstance().popPanel();
    }
}
