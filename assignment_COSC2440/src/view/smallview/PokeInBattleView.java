package view.smallview;

import model.pokemon.PokeInBattleInfo;
import utility.FileUtility;
import view.map.MapUtil;

import javax.swing.*;
import java.awt.*;

/**
 * Created with IntelliJ IDEA.
 * User: s3342128
 * Date: 5/04/13
 * Time: 7:29 PM
 * To change this template use File | Settings | File Templates.
 */
public class PokeInBattleView {

    private PokeInBattleInfo model;
    private Image img;
    private int x;
    private int y;

    public PokeInBattleView(PokeInBattleInfo model) {
        this.model = model;
        img = new ImageIcon(model.getImageURL()).getImage();
//        img = FileUtility.BLASTOISE_IMG;

        x = model.getI() * MapUtil.TILE_SIZE;
        y = model.getJ() * MapUtil.TILE_SIZE;
    }

    public void draw(Graphics g) {
        g.drawImage(img, x, y, MapUtil.TILE_SIZE, MapUtil.TILE_SIZE, null);
    }

    @Override
    public String toString() {
        return "PokeInBattleView{" +
                "i = " + x +
                "j = " + y +
                ", img=" + img +
                '}';
    }

    public String getOwner() {
        return model.getOwner();
    }

    public PokeInBattleInfo getModel() {
        return model;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
