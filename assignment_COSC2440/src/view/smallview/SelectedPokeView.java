package view.smallview;

import model.pokemon.Pokemon;
import utility.FileUtility;
import view.customview.InteractiveView;

import java.awt.*;

/**
 * Created with IntelliJ IDEA.
 * User: HungHandsome
 * Date: 4/1/13
 * Time: 9:13 AM
 * To change this template use File | Settings | File Templates.
 */
public class SelectedPokeView extends InteractiveView {

    private Image img;

    public SelectedPokeView(Pokemon p, int x, int y) {
//        super(x, y, FileUtility.SELECTED_POKE_IMG.getWidth(null), FileUtility.SELECTED_POKE_IMG.getWidth(null));
        super(x, y, 50, 50);

        if(p.getName().equals("Charizard")) {
            img = FileUtility.CHARIZARD_IMG;
        } else if(p.getName().equals("Blastoise")) {
            img = FileUtility.BLASTOISE_IMG;
        } else if(p.getName().equals("Venusaur")) {
            img = FileUtility.VENUSAUR_IMG;
        }
    }

    @Override
    public void draw(Graphics g) {
        g.drawImage(img, x, y, null);
    }

    @Override
    public void onClick() {
        //To change body of implemented methods use File | Settings | File Templates.
    }
}
