package view.smallview;

import model.pokemon.PokeInBattleInfo;
import org.springframework.core.env.MapPropertySource;
import view.map.MapUtil;

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

    public PokeInBattleView(PokeInBattleInfo model) {
        this.model = model;
    }

    public void draw(Graphics g) {
        g.drawImage(model.getImage(), model.getX(), model.getY(), MapUtil.TILE_SIZE, MapUtil.TILE_SIZE, null);
    }
}
