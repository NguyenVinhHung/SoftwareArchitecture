package model.pokemon;

import org.pushingpixels.trident.Timeline;
import utility.Move;

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

    private static final long serialVersionUID = 999L;

    private String imageURL;
    private String owner;
    private int teamNo;
    private int i;
    private int j;
    private int hp;
    private int maxHp;

    public PokeInBattleInfo(String owner, String imgURL, int i, int j) {
        this.owner = owner;
        imageURL = imgURL;
        this.i = i;
        this.j = j;
    }

    public PokeInBattleInfo(String owner, String imgURL, Move move, int teamNo, int hp, int maxHp) {
        this.owner = owner;
        imageURL = imgURL;
        this.i = move.i;
        this.j = move.j;
        this.teamNo = teamNo;
        this.hp = hp;
        this.maxHp = maxHp;
    }

//    public void moveAnim(int fromI, int fromJ, int toI, int toJ) {
//        Timeline timeline = new Timeline(this);
//        timeline.addPropertyToInterpolate("i", fromI, toI);
//        timeline.addPropertyToInterpolate("j", fromJ, toJ);
//        timeline.play();
//    }

    public String getOwner() {
        return owner;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setLocation(int i, int j) {
        this.i = i;
        this.j = j;
    }

    public int getI() {
        return i;
    }

    public void setI(int i) {
        this.i = i;
    }

    public int getJ() {
        return j;
    }

    public void setJ(int j) {
        this.j = j;
    }

    public int getTeamNo() {
        return teamNo;
    }

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public int getMaxHp() {
        return maxHp;
    }

    public boolean isDead() {
        return hp <= 0;
    }

    @Override
    public String toString() {
        return "PokeInBattleInfo{" +
                "hp=" + hp +
                '}';
    }
}
