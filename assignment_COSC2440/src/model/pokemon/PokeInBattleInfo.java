package model.pokemon;

import java.awt.*;
import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 * User: s3342128
 * Date: 5/04/13
 * Time: 7:29 PM
 * To change this template use File | Settings | File Templates.
 */
public class PokeInBattleInfo implements Serializable {

    private static final long serialVersionUID = 113121110987654321L;

    private Image image;
    private int x;
    private int y;

    public PokeInBattleInfo(Image img, int x, int y) {
        image = img;
        this.x = x;
        this.y = y;
    }

    public Image getImage() {
        return image;
    }

    public void setLocation(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }
}
