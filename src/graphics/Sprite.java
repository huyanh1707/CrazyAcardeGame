package graphics;

import constants.Parameter;
import javafx.scene.image.Image;

public class Sprite {

    private static final Image[][] spriteImages = SpriteSheet.tiles.scaleAll(Parameter.SCALE_RATIO);

    public static Image wall = spriteImages[5][0];
    public static Image grass = spriteImages[6][0];
    public static Image brick = spriteImages[7][0];

    public static Image player_up = spriteImages[0][0];
    public static Image player_up_1 = spriteImages[0][1];
    public static Image player_up_2 = spriteImages[0][2];

    public static Image player_right = spriteImages[1][0];
    public static Image player_right_1 = spriteImages[1][1];
    public static Image player_right_2 = spriteImages[1][2];

    public static Image player_down = spriteImages[2][0];
    public static Image player_down_1 = spriteImages[2][1];
    public static Image player_down_2 = spriteImages[2][2];

    public static Image player_left = spriteImages[3][0];
    public static Image player_left_1 = spriteImages[3][1];
    public static Image player_left_2 = spriteImages[3][2];

    public static Image player_dead = spriteImages[4][2];
    public static Image player_dead_1 = spriteImages[5][2];
    public static Image player_dead_2 = spriteImages[6][2];

    public static Image brick_exploded = spriteImages[7][1];
    public static Image brick_exploded_1 = spriteImages[7][2];
    public static Image brick_exploded_2 = spriteImages[7][3];

    public static Image playSpriteAnimation(Image image_0, Image image_1, Image image_2, int animate, int time) {
        int temp = animate % time;
        int delta = time / 3;

        if (temp < delta) {
            return image_0;
        } else if (temp < delta * 2) {
            return image_1;
        } else {
            return image_2;
        }
    }
}
