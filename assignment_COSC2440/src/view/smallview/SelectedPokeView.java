package view.smallview;

import model.pokemon.PokemonFactory;
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

    public static final int AVATAR_X = 62;
    public static final int AVATAR_Y = 11;
    public static final int AVATAR_SIZE = 56;
    public static final int LV_X = 13;
    public static final int LV_Y = 27;
    public static final int DETAIL_X = 128;
    public static final int ONWER_Y = 24;
    public static final int NAME_Y = 42;
    public static final int HOST_Y = 82;
    public static final int DETAIL_FONT_SIZE = 12;

    private Image img;
    private SelectedPokeInfo pokemon;

    public SelectedPokeView(SelectedPokeInfo p, int x, int y) {
//        super(x, y, FileUtility.SELECTED_POKE_IMG.getWidth(null), FileUtility.SELECTED_POKE_IMG.getWidth(null));
        super(x, y, SPV_WIDTH, SPV_HEIGHT);
        pokemon = p;

        if(pokemon != null) {
//            System.out.println("pokemon is " + pokemon.getName());
        } else {
//            System.out.println("pokemon is null");
            return;
        }

        img = PokemonFactory.getPokeAvatar(pokemon.getName());
    }

    @Override
    public void draw(Graphics g) {
        g.drawImage(FileUtility.SELECTED_POKE_IMG, x, y, null);

        if(pokemon == null) {
//            System.out.println("SelectedPokeView in draw(), pokemon is null");
            return;
        }

        Graphics g2 = g.create();

        g2.translate(x, y);
        g2.drawImage(img, AVATAR_X, AVATAR_Y, AVATAR_SIZE, AVATAR_SIZE, null);

        g2.setFont(new Font("", Font.PLAIN, 20));
        g2.setColor(Color.GREEN);
        g2.drawString(pokemon.getLv(), LV_X, LV_Y);

        g2.setFont(new Font("", Font.PLAIN, DETAIL_FONT_SIZE));
        g2.setColor(Color.YELLOW);
        g2.drawString(pokemon.getOwner(), DETAIL_X, ONWER_Y);
        g2.drawString(pokemon.getName(), DETAIL_X, NAME_Y);

        if(pokemon.isHost()) {
            g2.setColor(Color.GREEN);
            g2.drawString("Host", DETAIL_X, HOST_Y);
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
        img = PokemonFactory.getPokeAvatar(pokemon.getName());
        repaint();
    }

//    private void updateImage() {
//        System.out.println("Update image of SelectedPokeView");
//        if(pokemon.getName().equals("Charizard")) {
//            img = FileUtility.CHARIZARD_IMG;
//        } else if(pokemon.getName().equals("Blastoise")) {
//            img = FileUtility.BLASTOISE_IMG;
//        } else if(pokemon.getName().equals("Venusaur")) {
//            img = FileUtility.VENUSAUR_IMG;
//        }
//
//    }
}
