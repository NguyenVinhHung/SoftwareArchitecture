package utility;

import javax.swing.*;
import java.awt.*;

/**
 *
 *
 */
public class FileUtility {






    
    public static final String IMAGE_URL = "images/";

    public static final String[] TILES = {
        IMAGE_URL + "brown_tile.png",
        IMAGE_URL + "grass_tile.png",
        IMAGE_URL + "dirt_tile.png",
        IMAGE_URL + "water_tile.png",
        IMAGE_URL + "magma_tile.png",
        IMAGE_URL + "tree_short_tile.png",
        IMAGE_URL + "tree_tall_tile.png",
        IMAGE_URL + "tree_ugly_tile.png",
        IMAGE_URL + "black_blaster.png",
        IMAGE_URL + "red_blaster.png"
    };

    public static final String MAP_URL = IMAGE_URL + "/map/ZMap.png";
    public static final Image MAP_IMG = new ImageIcon(MAP_URL).getImage();

    public static final String SELECTED_TILE_URL = IMAGE_URL + "/map/selected_tile.png";

    public static final String LOGO_URL = IMAGE_URL + "game_logo.png";
    public static final String WELCOME_IMG_URL = IMAGE_URL + "welcome.png";
    public static final String POPUP_IMG_URL = IMAGE_URL + "popup.png";
//    public static final String TRANSPARENT_LAYER = IMAGE_URL + "transparent_layer.png";

    public static final String GAMESTART_IMAGES_URL = IMAGE_URL + "/game_start/";
    public static final String GSTART_BTN = GAMESTART_IMAGES_URL + "button.png";
    public static final String GSTART_BG = GAMESTART_IMAGES_URL + "game_start_bg.png";
    public static final String GSTART_HELP = GAMESTART_IMAGES_URL + "help.png";
    public static final String GSTART_PROFILE = GAMESTART_IMAGES_URL + "profile.png";
    public static final String GSTART_SETTING = GAMESTART_IMAGES_URL + "setting.png";
    public static final String GSTART_SHOP = GAMESTART_IMAGES_URL + "shop.png";

    public static final String SELECTED_POKE_URL = IMAGE_URL + "selected_poke.png";

    public static final Image LOGO_IMG = new ImageIcon(LOGO_URL).getImage();
    public static final Image WELCOME_IMG = new ImageIcon(WELCOME_IMG_URL).getImage();
    public static final Image POPUP_IMG = new ImageIcon(POPUP_IMG_URL).getImage();
//    public static final Image TRANS_LAYER_IMG = new ImageIcon(TRANSPARENT_LAYER).getImageURL();

    public static final Image GS_BTN_IMG = new ImageIcon(GSTART_BTN).getImage();
    public static final Image GS_BG_IMG = new ImageIcon(GSTART_BG).getImage();
//    public static final Image GS_HELP_IMG = new ImageIcon(GSTART_HELP).getImage();
//    public static final Image GS_PROFILE_IMG = new ImageIcon(GSTART_PROFILE).getImage();
//    public static final Image GS_SETTING_IMG = new ImageIcon(GSTART_SETTING).getImage();
//    public static final Image GS_SHOP_IMG = new ImageIcon(GSTART_SHOP).getImage();

    public static final Image SELECTED_POKE_IMG = new ImageIcon(SELECTED_POKE_URL).getImage();

    // Front folder
    public static final String POKE_FRONT_IMAGES_URL = IMAGE_URL + "/pokemon/";

    // Front URL
    public static final String CHARIZARD_FRONT_IMG_URL = POKE_FRONT_IMAGES_URL + "006.png";
    public static final String BLASTOISE_FRONT_IMG_URL = POKE_FRONT_IMAGES_URL + "009.png";
    public static final String VENUSAUR_FRONT_IMG_URL = POKE_FRONT_IMAGES_URL + "003.png";
    public static final String BUTTERFREE_FRONT_IMG_URL = POKE_FRONT_IMAGES_URL + "012.png";
    public static final String BEEDRILL_FRONT_IMG_URL = POKE_FRONT_IMAGES_URL + "015.png";
    public static final String PIDGEOT_FRONT_IMG_URL = POKE_FRONT_IMAGES_URL + "018.png";
    public static final String RATICATE_FRONT_IMG_URL = POKE_FRONT_IMAGES_URL + "020.png";
    public static final String ARBOK_FRONT_IMG_URL = POKE_FRONT_IMAGES_URL + "024.png";
    public static final String RAICHU_FRONT_IMG_URL = POKE_FRONT_IMAGES_URL + "026.png";
    public static final String ARTICUNO_FRONT_IMG_URL = POKE_FRONT_IMAGES_URL + "0144.png";
    public static final String ZAPDOS_FRONT_IMG_URL = POKE_FRONT_IMAGES_URL + "0145.png";
    public static final String MORTRESS_FRONT_IMG_URL = POKE_FRONT_IMAGES_URL + "0146.png";
    public static final String DRAGONITE_FRONT_IMG_URL = POKE_FRONT_IMAGES_URL + "0149.png";


    // Icon URL
    public static final String CHARIZARD_ICON_URL = POKE_FRONT_IMAGES_URL + "6.png";
    public static final String BLASTOISE_ICON_URL = POKE_FRONT_IMAGES_URL + "9.png";
    public static final String VENUSAUR_ICON_URL = POKE_FRONT_IMAGES_URL + "3.png";
    public static final String BUTTERFREE_ICON_URL = POKE_FRONT_IMAGES_URL + "12.png";
    public static final String BEEDRILL_ICON_URL = POKE_FRONT_IMAGES_URL + "15.png";
    public static final String PIDGEOT_ICON_URL = POKE_FRONT_IMAGES_URL + "18.png";
    public static final String RATICATE_ICON_URL = POKE_FRONT_IMAGES_URL + "20.png";
    public static final String ARBOK_ICON_URL = POKE_FRONT_IMAGES_URL + "24.png";
    public static final String RAICHU_ICON_URL = POKE_FRONT_IMAGES_URL + "26.png";
    public static final String ARTICUNO_ICON_URL = POKE_FRONT_IMAGES_URL + "144.png";
    public static final String ZAPDOS_ICON_URL = POKE_FRONT_IMAGES_URL + "145.png";
    public static final String MORTRESS_ICON_URL = POKE_FRONT_IMAGES_URL + "146.png";
    public static final String DRAGONITE_ICON_URL = POKE_FRONT_IMAGES_URL + "149.png";


    // Front Image
    public static final Image CHARIZARD_FRONT_IMG = new ImageIcon(CHARIZARD_FRONT_IMG_URL).getImage();
    public static final Image BLASTOISE_FRONT_IMG = new ImageIcon(BLASTOISE_FRONT_IMG_URL).getImage();
    public static final Image VENUSAUR_FRONT_IMG = new ImageIcon(VENUSAUR_FRONT_IMG_URL).getImage();
    public static final Image BUTTERFREE_FRONT_IMG = new ImageIcon(BUTTERFREE_FRONT_IMG_URL).getImage();
    public static final Image BEEDRILL_FRONT_IMG = new ImageIcon(BEEDRILL_FRONT_IMG_URL).getImage();
    public static final Image PIDGEOT_FRONT_IMG = new ImageIcon(PIDGEOT_FRONT_IMG_URL).getImage();
    public static final Image RATICATE_FRONT_IMG = new ImageIcon(RATICATE_FRONT_IMG_URL).getImage();
    public static final Image ARBOK_FRONT_IMG = new ImageIcon(ARBOK_FRONT_IMG_URL).getImage();
    public static final Image RAICHU_FRONT_IMG = new ImageIcon(RAICHU_FRONT_IMG_URL).getImage();
    public static final Image ARTICUNO_FRONT_IMG = new ImageIcon(ARTICUNO_FRONT_IMG_URL).getImage();
    public static final Image ZAPDOS_FRONT_IMG = new ImageIcon(ZAPDOS_FRONT_IMG_URL).getImage();
    public static final Image MORTRESS_FRONT_IMG = new ImageIcon(MORTRESS_FRONT_IMG_URL).getImage();
    public static final Image DRAGONITE_FRONT_IMG = new ImageIcon(DRAGONITE_FRONT_IMG_URL).getImage();



    // Icon Image
    public static final Image CHARIZARD_ICON = new ImageIcon(CHARIZARD_ICON_URL).getImage();
    public static final Image BLASTOISE_ICON = new ImageIcon(BLASTOISE_ICON_URL).getImage();
    public static final Image VENUSAUR_ICON = new ImageIcon(VENUSAUR_ICON_URL).getImage();
    public static final Image BUTTERFREE_ICON = new ImageIcon(BUTTERFREE_ICON_URL).getImage();
    public static final Image BEEDRILL_ICON = new ImageIcon(BEEDRILL_ICON_URL).getImage();
    public static final Image PIDGEOT_ICON = new ImageIcon(PIDGEOT_ICON_URL).getImage();
    public static final Image RATICATE_ICON = new ImageIcon(RATICATE_ICON_URL).getImage();
    public static final Image ARBOK_ICON = new ImageIcon(ARBOK_ICON_URL).getImage();
    public static final Image RAICHU_ICON = new ImageIcon(RAICHU_ICON_URL).getImage();
    public static final Image ARTICUNO_ICON = new ImageIcon(ARTICUNO_ICON_URL).getImage();
    public static final Image ZAPDOS_ICON = new ImageIcon(ZAPDOS_ICON_URL).getImage();
    public static final Image MORTRESS_ICON = new ImageIcon(MORTRESS_ICON_URL).getImage();
    public static final Image DRAGONITE_ICON = new ImageIcon(DRAGONITE_ICON_URL).getImage();


    // Back URL
    public static final String POKE_BACK_IMAGES_URL = IMAGE_URL + "/poke_back/";

    public static final String CHARIZARD_BACK_IMG_URL = POKE_BACK_IMAGES_URL + "006.png";
    public static final String BLASTOISE_BACK_IMG_URL = POKE_BACK_IMAGES_URL + "009.png";
    public static final String VENUSAUR_BACK_IMG_URL = POKE_BACK_IMAGES_URL + "003.png";
    public static final String BUTTERFREE_BACK_IMG_URL = POKE_BACK_IMAGES_URL + "12.png";
    public static final String BEEDRILL_BACK_IMG_URL = POKE_BACK_IMAGES_URL + "15.png";
    public static final String PIDGEOT_BACK_IMG_URL = POKE_BACK_IMAGES_URL + "18.png";
    public static final String RATICATE_BACK_IMG_URL = POKE_BACK_IMAGES_URL + "20.png";
    public static final String ARBOK_BACK_IMG_URL = POKE_BACK_IMAGES_URL + "24.png";
    public static final String RAICHU_BACK_IMG_URL = POKE_BACK_IMAGES_URL + "26.png";
    public static final String ARTICUNO_BACK_IMG_URL = POKE_BACK_IMAGES_URL + "144.png";
    public static final String ZAPDOS_BACK_IMG_URL = POKE_BACK_IMAGES_URL + "145.png";
    public static final String MORTRESS_BACK_IMG_URL = POKE_BACK_IMAGES_URL + "146.png";
    public static final String DRAGONITE_BACK_IMG_URL = POKE_BACK_IMAGES_URL + "149.png";


    // Back Image
    public static final Image CHARIZARD_BACK_IMG = new ImageIcon(CHARIZARD_BACK_IMG_URL).getImage();
    public static final Image BLASTOISE_BACK_IMG = new ImageIcon(BLASTOISE_BACK_IMG_URL).getImage();
    public static final Image VENUSAUR_BACK_IMG = new ImageIcon(VENUSAUR_BACK_IMG_URL).getImage();
    public static final Image BUTTERFREE_BACK_IMG = new ImageIcon(BUTTERFREE_BACK_IMG_URL).getImage();
    public static final Image BEEDRILL_BACK_IMG = new ImageIcon(BEEDRILL_BACK_IMG_URL).getImage();
    public static final Image PIDGEOT_BACK_IMG = new ImageIcon(PIDGEOT_BACK_IMG_URL).getImage();
    public static final Image RATICATE_BACK_IMG = new ImageIcon(RATICATE_BACK_IMG_URL).getImage();
    public static final Image ARBOK_BACK_IMG = new ImageIcon(ARBOK_BACK_IMG_URL).getImage();
    public static final Image RAICHU_BACK_IMG = new ImageIcon(RAICHU_BACK_IMG_URL).getImage();
    public static final Image ARTICUNO_BACK_IMG = new ImageIcon(ARTICUNO_BACK_IMG_URL).getImage();
    public static final Image ZAPDOS_BACK_IMG = new ImageIcon(ZAPDOS_BACK_IMG_URL).getImage();
    public static final Image MORTRESS_BACK_IMG = new ImageIcon(MORTRESS_BACK_IMG_URL).getImage();
    public static final Image DRAGONITE_BACK_IMG = new ImageIcon(DRAGONITE_BACK_IMG_URL).getImage();



    // Avatar Folder
    public static final String POKE_AVAR_URL = IMAGE_URL + "/poke_avatar/";

    // Avatar URL
    public static final String CHARIZARD_AVAR_URL = POKE_AVAR_URL + "charizard.png";
    public static final String BLASTOISE_AVAR_URL = POKE_AVAR_URL + "blastoise.png";
    public static final String VENUSAUR_AVAR_URL = POKE_AVAR_URL + "venusaur.png";
    public static final String BUTTERFREE_AVAR_URL = POKE_AVAR_URL + "butterfree.png";
    public static final String BEEDRILL_AVAR_URL = POKE_AVAR_URL + "beedrill.png";
    public static final String PIDGEOT_AVAR_URL = POKE_AVAR_URL + "pidgeot.png";
    public static final String RATICATE_AVAR_URL = POKE_AVAR_URL + "raticate.png";
    public static final String ARBOK_AVAR_URL = POKE_AVAR_URL + "arbok.png";
    public static final String RAICHU_AVAR_URL = POKE_AVAR_URL + "raichu.png";
    public static final String ARTICUNO_AVAR_URL = POKE_AVAR_URL + "articuno.png";
    public static final String ZAPDOS_AVAR_URL = POKE_AVAR_URL + "zapdos.png";
    public static final String MORTRESS_AVAR_URL = POKE_AVAR_URL + "mortress.png";
    public static final String DRAGONITE_AVAR_URL = POKE_AVAR_URL + "dragonite.png";

    // Avatar Image
    public static final Image CHARIZARD_AVAR = new ImageIcon(CHARIZARD_AVAR_URL).getImage();
    public static final Image BLASTOISE_AVAR = new ImageIcon(BLASTOISE_AVAR_URL).getImage();
    public static final Image VENUSAUR_AVAR = new ImageIcon(VENUSAUR_AVAR_URL).getImage();

    public static final Image BUTTERFREE_AVAR = new ImageIcon(BUTTERFREE_AVAR_URL).getImage();
    public static final Image BEEDRILL_AVAR = new ImageIcon(BEEDRILL_AVAR_URL).getImage();
    public static final Image PIDGEOT_AVAR = new ImageIcon(PIDGEOT_AVAR_URL).getImage();
    public static final Image RATICATE_AVAR = new ImageIcon(RATICATE_AVAR_URL).getImage();
    public static final Image ARBOK_AVAR = new ImageIcon(ARBOK_AVAR_URL).getImage();
    public static final Image RAICHU_AVAR = new ImageIcon(RAICHU_AVAR_URL).getImage();
    public static final Image ARTICUNO_AVAR = new ImageIcon(ARTICUNO_AVAR_URL).getImage();
    public static final Image ZAPDOS_AVAR = new ImageIcon(ZAPDOS_AVAR_URL).getImage();
    public static final Image MORTRESS_AVAR = new ImageIcon(MORTRESS_AVAR_URL).getImage();
    public static final Image DRAGONITE_AVAR = new ImageIcon(DRAGONITE_AVAR_URL).getImage();


    // Skills Folder
    public static final String SKILL_ANIM_URL = IMAGE_URL + "/skill_animation/";

    // Skill Image
    public static final Image BULLET_IMG = new ImageIcon(SKILL_ANIM_URL + "bullet.png").getImage();
    public static final Image OVERLAY_BG_IMG = new ImageIcon(SKILL_ANIM_URL + "overlay_bg.png").getImage();
    public static final Image TOP_BG_IMG = new ImageIcon(SKILL_ANIM_URL + "top_bg.png").getImage();

}
