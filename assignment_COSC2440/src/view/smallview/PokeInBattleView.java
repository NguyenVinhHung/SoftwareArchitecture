package view.smallview;

import model.pokemon.PokeInBattleInfo;
import utility.FileUtility;
import view.map.MapUtil;

import javax.swing.*;
import java.awt.*;


public class PokeInBattleView {

    private PokeInBattleInfo model;
    private Image img;
    private int x;
    private int y;

    public PokeInBattleView(PokeInBattleInfo model) {
        this.model = model;
        img = new ImageIcon(model.getImageURL()).getImage();
//        img = FileUtility.BLASTOISE_FRONT_IMG;

        x = model.getI() * MapUtil.TILE_SIZE;
        y = model.getJ() * MapUtil.TILE_SIZE;
    }

    public void draw(Graphics g) {
        x = model.getI() * MapUtil.TILE_SIZE;
        y = model.getJ() * MapUtil.TILE_SIZE;

        int hp = (int)(((float)model.getHp()/model.getMaxHp()) * MapUtil.TILE_SIZE);

        g.setColor(Color.GREEN);
        g.fillRect(x, y, hp, 10);

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
