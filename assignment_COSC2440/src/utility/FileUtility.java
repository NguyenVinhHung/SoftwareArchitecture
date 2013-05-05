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
    public static final Image GS_HELP_IMG = new ImageIcon(GSTART_HELP).getImage();
    public static final Image GS_PROFILE_IMG = new ImageIcon(GSTART_PROFILE).getImage();
    public static final Image GS_SETTING_IMG = new ImageIcon(GSTART_SETTING).getImage();
    public static final Image GS_SHOP_IMG = new ImageIcon(GSTART_SHOP).getImage();

    public static final Image SELECTED_POKE_IMG = new ImageIcon(SELECTED_POKE_URL).getImage();


    public static final String POKE_IMAGES_URL = IMAGE_URL + "/pokemon/";
    public static final String CHARIZARD_IMG_URL = POKE_IMAGES_URL + "006.gif";
    public static final String BLASTOISE_IMG_URL = POKE_IMAGES_URL + "009.gif";
    public static final String VENUSAUR_IMG_URL = POKE_IMAGES_URL + "003.gif";
    public static final String CHARIZARD_ICON_URL = POKE_IMAGES_URL + "6.png";
    public static final String BLASTOISE_ICON_URL = POKE_IMAGES_URL + "9.png";
    public static final String VENUSAUR_ICON_URL = POKE_IMAGES_URL + "3.png";

    public static final Image CHARIZARD_IMG = new ImageIcon(CHARIZARD_IMG_URL).getImage();
    public static final Image BLASTOISE_IMG = new ImageIcon(BLASTOISE_IMG_URL).getImage();
    public static final Image VENUSAUR_IMG = new ImageIcon(VENUSAUR_IMG_URL).getImage();

    public static final Image CHARIZARD_ICON = new ImageIcon(CHARIZARD_ICON_URL).getImage();
    public static final Image BLASTOISE_ICON = new ImageIcon(BLASTOISE_ICON_URL).getImage();
    public static final Image VENUSAUR_ICON = new ImageIcon(VENUSAUR_ICON_URL).getImage();

    public static final String POKE_AVAR_URL = IMAGE_URL + "/poke_avatar/";
    public static final String CHARIZARD_AVAR_URL = POKE_AVAR_URL + "charizard.png";
    public static final String BLASTOISE_AVAR_URL = POKE_AVAR_URL + "blastoise.png";
    public static final String VENUSAUR_AVAR_URL = POKE_AVAR_URL + "venusaur.png";

    public static final Image CHARIZARD_AVAR = new ImageIcon(CHARIZARD_AVAR_URL).getImage();
    public static final Image BLASTOISE_AVAR = new ImageIcon(BLASTOISE_AVAR_URL).getImage();
    public static final Image VENUSAUR_AVAR = new ImageIcon(VENUSAUR_AVAR_URL).getImage();
}
