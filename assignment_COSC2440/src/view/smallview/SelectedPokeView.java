package view.smallview;

import model.pokemon.SelectedPokeInfo;
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

    public static final int SPV_WIDTH = 208;
    public static final int SPV_HEIGHT = 93;

    private static final int AVATAR_X = 62;
    private static final int AVATAR_Y = 11;
    private static final int AVATAR_SIZE = 56;
    private static final int LV_X = 13;
    private static final int LV_Y = 24;
    private static final int DETAIL_X = 128;
    private static final int ONWER_Y = 24;
    private static final int NAME_Y = 42;
    private static final int HOST_Y = 82;
    private static final int DETAIL_FONT_SIZE = 12;

    private Image img;
    private SelectedPokeInfo pokemon;

    public SelectedPokeView(SelectedPokeInfo p, int x, int y) {
//        super(x, y, FileUtility.SELECTED_POKE_IMG.getWidth(null), FileUtility.SELECTED_POKE_IMG.getWidth(null));
        super(x, y, SPV_WIDTH, SPV_HEIGHT);
        pokemon = p;

        if(pokemon == null) {
            return;
        }

        updateImage();
    }

    @Override
    public void draw(Graphics g) {
        g.drawImage(FileUtility.SELECTED_POKE_IMG, x, y, null);

        if(pokemon == null) {
            return;
        }

        g.drawImage(img, x + AVATAR_X, y + AVATAR_Y, AVATAR_SIZE, AVATAR_SIZE, null);

        g.setFont(new Font("", Font.PLAIN, 20));
        g.setColor(Color.GREEN);
        g.drawString(pokemon.getLv(), LV_X, LV_Y);

        g.setFont(new Font("", Font.PLAIN, DETAIL_FONT_SIZE));
        g.setColor(Color.YELLOW);
        g.drawString(pokemon.getOwner(), DETAIL_X, ONWER_Y);
        g.drawString(pokemon.getName(), DETAIL_X, NAME_Y);

        if(pokemon.isHost()) {
            g.setColor(Color.GREEN);
            g.drawString(pokemon.getOwner(), DETAIL_X, HOST_Y);
        }
    }

    @Override
    public void onClick() {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    public void setPokemon(SelectedPokeInfo p) {
        this.pokemon = p;
        if(pokemon == null) {
            return;
        }
        updateImage();
        repaint();
    }

    private void updateImage() {
        if(pokemon.getName().equals("Charizard")) {
            img = FileUtility.CHARIZARD_IMG;
        } else if(pokemon.getName().equals("Blastoise")) {
            img = FileUtility.BLASTOISE_IMG;
        } else if(pokemon.getName().equals("Venusaur")) {
            img = FileUtility.VENUSAUR_IMG;
        }
    }
}
