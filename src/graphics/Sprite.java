package graphics;

import gameplay.Parameter;
import javafx.scene.image.Image;

public class Sprite {

    private static final Image[][] spriteImages = SpriteSheet.tiles.scaleAll(Parameter.SCALE_RATIO);

    //==================================================================================================================
    /**
     * board sprites.
     */
    public static Image wall = spriteImages[5][0];
    public static Image grass = spriteImages[6][0];
    public static Image portal = spriteImages[4][0];
    public static Image brick = spriteImages[7][0];

    /**
     * Player Sprites.
     */
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

    /**
     * Enemies.
     */
    //BALLOM
    public static Image ballom_left = spriteImages[9][0];
    public static Image ballom_left_1 = spriteImages[9][1];
    public static Image ballom_left_2 = spriteImages[9][2];

    public static Image ballom_right = spriteImages[10][0];
    public static Image ballom_right_1 = spriteImages[10][1];
    public static Image ballom_right_2 = spriteImages[10][2];

    public static Image ballom_dead = spriteImages[9][3];

    //ONEAL
    public static Image oneal_left = spriteImages[11][0];
    public static Image oneal_left_1 = spriteImages[11][1];
    public static Image oneal_left_2 = spriteImages[11][2];

    public static Image oneal_right = spriteImages[12][0];
    public static Image oneal_right_1 = spriteImages[12][1];
    public static Image oneal_right_2 = spriteImages[12][2];

    public static Image oneal_dead = spriteImages[11][3];

    //DOLL
    public static Image doll_left = spriteImages[13][0];
    public static Image doll_left_1 = spriteImages[13][1];
    public static Image doll_left_2 = spriteImages[13][2];

    public static Image doll_right = spriteImages[14][0];
    public static Image doll_right_1 = spriteImages[14][1];
    public static Image doll_right_2 = spriteImages[14][2];

    public static Image doll_dead = spriteImages[13][3];

    //MINVO
    public static Image minvo_left = spriteImages[8][5];
    public static Image minvo_left_1 = spriteImages[8][6];
    public static Image minvo_left_2 = spriteImages[8][7];

    public static Image minvo_right = spriteImages[9][5];
    public static Image minvo_right_1 = spriteImages[9][6];
    public static Image minvo_right_2 = spriteImages[9][7];

    public static Image minvo_dead = spriteImages[8][8];

    //KONDORIA
    public static Image kondoria_left = spriteImages[10][5];
    public static Image kondoria_left_1 = spriteImages[10][6];
    public static Image kondoria_left_2 = spriteImages[10][7];

    public static Image kondoria_right = spriteImages[11][5];
    public static Image kondoria_right_1 = spriteImages[11][6];
    public static Image kondoria_right_2 = spriteImages[11][7];

    public static Image kondoria_dead = spriteImages[10][8];

    //ALL
    public static Image mob_dead_1 = spriteImages[15][0];
    public static Image mob_dead_2 = spriteImages[15][1];
    public static Image mob_dead_3 = spriteImages[15][2];

    /**
     * Bobm sprites.
     */
    public static Image bomb = spriteImages[0][3];
    public static Image bomb_1 = spriteImages[1][3];
    public static Image bomb_2 = spriteImages[2][3];

    /**
     * FlameSegment Sprites.
     */
    public static Image bomb_exploded = spriteImages[0][4];
    public static Image bomb_exploded_1 = spriteImages[0][5];
    public static Image bomb_exploded_2 = spriteImages[0][6];

    public static Image explosion_vertical = spriteImages[1][5];
    public static Image explosion_vertical_1 = spriteImages[2][5];
    public static Image explosion_vertical_2 = spriteImages[3][5];

    public static Image explosion_horizontal = spriteImages[1][7];
    public static Image explosion_horizontal_1 = spriteImages[1][8];
    public static Image explosion_horizontal_2 = spriteImages[1][9];

    public static Image explosion_horizontal_left_last = spriteImages[0][7];
    public static Image explosion_horizontal_left_last_1 = spriteImages[0][8];
    public static Image explosion_horizontal_left_last_2 = spriteImages[0][9];

    public static Image explosion_horizontal_right_last = spriteImages[2][7];
    public static Image explosion_horizontal_right_last_1 = spriteImages[2][8];
    public static Image explosion_horizontal_right_last_2 = spriteImages[2][9];

    public static Image explosion_vertical_top_last = spriteImages[1][4];
    public static Image explosion_vertical_top_last_1 = spriteImages[2][4];
    public static Image explosion_vertical_top_last_2 = spriteImages[3][4];

    public static Image explosion_vertical_down_last = spriteImages[1][6];
    public static Image explosion_vertical_down_last_1 = spriteImages[2][6];
    public static Image explosion_vertical_down_last_2 = spriteImages[3][6];

    /**
     * Brick Explosion.
     */
    public static Image brick_exploded = spriteImages[7][1];
    public static Image brick_exploded_1 = spriteImages[7][2];
    public static Image brick_exploded_2 = spriteImages[7][3];

    /**
     * Powerups.
     */
    public static Image powerup_bombs = spriteImages[0][10];
    public static Image powerup_flames = spriteImages[1][10];
    public static Image powerup_speed = spriteImages[2][10];
    public static Image powerup_wallpass = spriteImages[3][10];
    public static Image powerup_detonator = spriteImages[4][10];
    public static Image powerup_bombpass = spriteImages[5][10];
    public static Image powerup_flamepass = spriteImages[6][10];
    public static Image powerup_mystery = spriteImages[7][10];
    //==================================================================================================================


}

