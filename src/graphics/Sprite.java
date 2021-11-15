package graphics;

import gameplay.Parameter;
import javafx.scene.image.Image;

public class Sprite {

    private static final Image[][] spriteImages = SpriteSheet.tiles.scaleAll(Parameter.SCALE_RATIO);

    /**
     * board sprites.
     */
    public static Image wall = spriteImages[5][0];
    public static Image grass = spriteImages[6][0];
}
