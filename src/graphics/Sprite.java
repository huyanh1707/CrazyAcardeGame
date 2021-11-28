package graphics;

import constants.Parameter;
import javafx.scene.image.Image;

public class Sprite {

    private static final Image[][] spriteImages = SpriteSheet.tiles.scaleAll(Parameter.SCALE_RATIO);

    // background
    public static Image wall = spriteImages[5][0];
    public static Image grass = spriteImages[6][0];
    public static Image brick = spriteImages[7][0];

    public static Image brick_exploded = spriteImages[7][1];
    public static Image brick_exploded_1 = spriteImages[7][2];
    public static Image brick_exploded_2 = spriteImages[7][3];

    // Player
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

    // Ballom
    public static Image ballom_left = spriteImages[9][0];
    public static Image ballom_left_1 = spriteImages[9][1];
    public static Image ballom_left_2 = spriteImages[9][2];

    public static Image ballom_right = spriteImages[10][0];
    public static Image ballom_right_1 = spriteImages[10][1];
    public static Image ballom_right_2 = spriteImages[10][2];

    public static Image ballom_dead = spriteImages[9][3];

    // Doll
    public static Image doll_left = spriteImages[13][0];
    public static Image doll_left_1 = spriteImages[13][1];
    public static Image doll_left_2 = spriteImages[13][2];

    public static Image doll_right = spriteImages[14][0];
    public static Image doll_right_1 = spriteImages[14][1];
    public static Image doll_right_2 = spriteImages[14][2];

    public static Image doll_dead = spriteImages[13][3];

    // Kondoria
    public static Image kondoria_left = spriteImages[10][5];
    public static Image kondoria_left_1 = spriteImages[10][6];
    public static Image kondoria_left_2 = spriteImages[10][7];

    public static Image kondoria_right = spriteImages[11][5];
    public static Image kondoria_right_1 = spriteImages[11][6];
    public static Image kondoria_right_2 = spriteImages[11][7];

    public static Image kondoria_dead = spriteImages[10][8];

    // Oneal
    public static Image oneal_left = spriteImages[11][0];
    public static Image oneal_left_1 = spriteImages[11][1];
    public static Image oneal_left_2 = spriteImages[11][2];

    public static Image oneal_right = spriteImages[12][0];
    public static Image oneal_right_1 = spriteImages[12][1];
    public static Image oneal_right_2 = spriteImages[12][2];

    public static Image oneal_dead = spriteImages[11][3];


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
