package view.smallview;

import model.pokemon.PokeInBattleInfo;
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

    public PokeInBattleView(PokeInBattleInfo model) {
        this.model = model;
        img = new ImageIcon(model.getImageURL()).getImage();
    }

    public void draw(Graphics g) {
        g.drawImage(img, model.getI(), model.getJ(), MapUtil.TILE_SIZE, MapUtil.TILE_SIZE, null);
    }
}
