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

    public static final String SELECTED_TILE_URL = IMAGE_URL + "selected_tile.png";

    public static final String WELCOME_IMG_URL = IMAGE_URL + "welcome.png";
    public static final String POPUP_IMG_URL = IMAGE_URL + "popup.png";

    public static final Image POPUP_IMG = new ImageIcon(POPUP_IMG_URL).getImage();
}
