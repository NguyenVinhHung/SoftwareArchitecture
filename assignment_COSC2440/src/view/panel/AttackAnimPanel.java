package view.panel;

import animation.ScreenCapturer;
import main.Main;
import model.pokemon.PokemonFactory;
import org.pushingpixels.trident.Timeline;
import utility.FileUtility;
import view.anim.AnimUtil;

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

    private static final int ATK_X = 350;
    private static final int ATK_Y = 400;
    private static final int ENE_X = ATK_X + 200;
    private static final int ENE_Y = ATK_Y - 50;

    private static final int TOP_BG_X = 256;
    private static final int TOP_BG_Y = 194;
    private static final int TOP_BG_W = 512;
    private static final int TOP_BG_H = 384;

    private static final int START_X = ATK_X + 40;
    private static final int END_X = ENE_X;
    private static final int BUL_Y = ATK_Y;

    private Image bg;
    private Image attacker;
    private Image enemy;
    private Timeline timeline;

    private int bulletX = START_X;

    public AttackAnimPanel(String attackerName, String enemyName) {
        System.out.println(attackerName + " - " + enemyName);

        attacker = PokemonFactory.getBackGifImage(attackerName);
        enemy = PokemonFactory.getFrontGifImage(enemyName);
    }

    @Override
    protected void paintComponent(Graphics g) {
        g.drawImage(ScreenCapturer.getScreenShot(), 0, 0, getWidth(), getHeight(), null);
        g.drawImage(FileUtility.OVERLAY_BG_IMG, 0, 0, getWidth(), getHeight(), null);
        g.drawImage(FileUtility.TOP_BG_IMG, TOP_BG_X, TOP_BG_Y, TOP_BG_W, TOP_BG_H, null);

        g.drawImage(attacker, ATK_X, ATK_Y, null);
        g.drawImage(enemy, ENE_X, ENE_Y, null);

        if(timeline!=null && !timeline.isDone()) {
            g.drawImage(FileUtility.BULLET_IMG, bulletX, BUL_Y, null);
        }
    }

    public void playAnim() {
        timeline = new Timeline(this);
        timeline.addPropertyToInterpolate("bulletX", START_X, END_X);
        timeline.setDuration(AnimUtil.ATK_DURATION);
        timeline.play();

        new Thread() {
            @Override
            public void run() {
                try {
                    sleep(AnimUtil.ATK_DURATION);
                } catch(Exception ex) {

                }
                Main.getInstance().popPanel();
            }
        }.start();
    }

    public void setBulletX(int bulletX) {
        this.bulletX = bulletX;
        repaint();
    }
}
