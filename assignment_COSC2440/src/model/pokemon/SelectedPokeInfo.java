package model.pokemon;

import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 * User: HungHandsome
 * Date: 4/4/13
 * Time: 8:07 AM
 * To change this template use File | Settings | File Templates.
 */
public class SelectedPokeInfo implements Serializable {

    private static final long serialVersionUID = 111111111111111111L;

    private String name;
    private String lv;
    private String owner;
    private boolean host;
    private int x;
    private int y;

    public SelectedPokeInfo(String name, int lv, String owner, boolean host) {
        this.name = name;
        this.lv = "Lv" + lv;
        this.owner = owner;
        this.host = host;
    }

    public String getName() {
        return name;
    }

    public String getLv() {
        return lv;
    }

    public String getOwner() {
        return owner;
    }

    public boolean isHost() {
        return host;
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
