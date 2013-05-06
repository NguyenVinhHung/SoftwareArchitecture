package view.panel;

import animation.ScreenCapturer;
import model.pokemon.PokemonFactory;
import utility.FileUtility;

import javax.swing.*;
import java.awt.*;

/**
 * Created with IntelliJ IDEA.
 * User: s3342128
 * Date: 6/05/13
 * Time: 12:11 AM
 * To change this template use File | Settings | File Templates.
 */
public class AttackAnimPanel extends JPanel {

    private static final int ATK_X = 300;
    private static final int POKE_Y = 400;
    private static final int ENE_X = 500;
//    private static final int BUL_Y = ;
    private static final int TOP_BG_X = 256;
    private static final int TOP_BG_Y = 194;
    private static final int TOP_BG_W = 512;
    private static final int TOP_BG_H = 384;

    private Image bg;
    private Image attacker;
    private Image enemy;

    private int bulletX;

    public AttackAnimPanel(String attackerName, String enemyName) {
        System.out.println(attackerName + " - " + enemyName);

        attacker = PokemonFactory.getBackGifImage(attackerName);
        enemy = PokemonFactory.getFrontGifImage(enemyName);

        if(attacker == null) {
            System.out.println("Attacker Image is null");
        } else {
            System.out.println("Attacker Image ok");
        }
        if(enemy == null) {
            System.out.println("Enemy Image is null");
        } else {
            System.out.println("Enemy Image ok");
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
//        g.drawImage(ScreenCapturer.getScreenShot(), 0, 0, getWidth(), getHeight(), null);
        g.drawImage(FileUtility.OVERLAY_BG_IMG, 0, 0, getWidth(), getHeight(), null);
        g.drawImage(FileUtility.TOP_BG_IMG, TOP_BG_X, TOP_BG_Y, TOP_BG_W, TOP_BG_H, null);

        g.drawImage(attacker, ATK_X, POKE_Y, null);
        g.drawImage(enemy, ENE_X, POKE_Y, null);
    }
}
