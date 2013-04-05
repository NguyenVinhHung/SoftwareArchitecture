package view.smallview;

import view.customview.InteractiveView;

import java.awt.*;

/**
 * Created with IntelliJ IDEA.
 * User: HungHandsome
 * Date: 4/4/13
 * Time: 7:13 PM
 * To change this template use File | Settings | File Templates.
 */
public class PokeForSelectView extends InteractiveView {

    private Image img;
    private boolean selected;

    public PokeForSelectView(Image i, int x, int y) {
        super(x, y, SelectedPokeView.AVATAR_SIZE, SelectedPokeView.AVATAR_SIZE);
        img = i;
    }

    @Override
    public void draw(Graphics g) {
        if(selected) {
            g.setColor(Color.GREEN);
            g.fillRect(x, y, width + 4, height + 4);
        }
        g.drawImage(img, x+2, y+2, width, height, null);
    }

    @Override
    public void onClick() {
        if(selected) {
            return;
        }
        selected = true;
        repaint();
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    public boolean isSelected() {
        return selected;
    }
}
