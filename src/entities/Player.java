package entities;

import constants.Parameter;
import gamelogic.KeyInput;
import graphics.Sprite;
import javafx.scene.image.Image;
import gameplay.MapCreate;

public class Player extends MovingEntity {

    private static Player player = null;

    private int immortalTime = 100;
    private int lifeCount = 100;
    private static boolean canDie = false;

    private final int x_init, y_init;

    KeyInput input;

    public Player(int x, int y) {
        super(x, y, Sprite.player_right);
        boundedBox = new RectangleBox(x, y, Parameter.SCALED_SIZE - 10, Parameter.SCALED_SIZE - 2);
        alive = true;
        input = new KeyInput();
        x_init = x;
        y_init = y;
        speed = 2;
    }

    public static Player setPlayer(int x, int y, boolean newOne) {
        if (player == null || newOne) {
            player = new Player(x, y);
        } else {
            player.setPosition(x, y);
        }
        canDie = false;
        return player;
    }

    public static Player getPlayer() {
        return player;
    }

    public void update() {
        animation();
        playAnimation();
        try {
            input.playerKeyHandler();
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (immortalTime > 0 && !canDie) {
            immortalTime--;
        } else {
            canDie = true;
        }
        x_node = x_pos / Parameter.BLOCK_SIZE;
        y_node = y_pos / Parameter.BLOCK_SIZE;
    }

    public void playAnimation() {
        if (alive) {
            switch(currentDirection) {
                case UP:
                    if (isMoving) {
                        image = Sprite.playSpriteAnimation(Sprite.player_up
                                , Sprite.player_up_1, Sprite.player_up_2, animate, 20);
                    }
                    break;
                case DOWN:
                    if (isMoving) {
                        image = Sprite.playSpriteAnimation(Sprite.player_down
                                , Sprite.player_down_1, Sprite.player_down_2, animate, 20);
                    }
                    break;
                case RIGHT:
                    if (isMoving) {
                        image = Sprite.playSpriteAnimation(Sprite.player_right
                                , Sprite.player_right_1, Sprite.player_right_2, animate, 20);
                    }
                    break;
                case LEFT:
                    if (isMoving) {
                        image = Sprite.playSpriteAnimation(Sprite.player_left
                                , Sprite.player_left_1, Sprite.player_left_2, animate, 20);
                    }
                    break;
            }
        } else {
            image = Sprite.playSpriteAnimation(Sprite.player_dead
                    , Sprite.player_dead_1, Sprite.player_dead_2, animate, 30);
        }
    }

    public void setPosition(int x, int y) {
        x_pos = x;
        y_pos = y;
        boundedBox.setPosition(x, y);
    }

    @Override
    public boolean checkFriendlyCollisions(int x, int y) {
        boundedBox.setPosition(x, y);
        if (x < Parameter.BLOCK_SIZE || x > MapCreate.CANVAS_WIDTH - Parameter.BLOCK_SIZE) {
            boundedBox.setPosition(x_pos, y_pos);
            return false;
        }
        if (y < Parameter.BLOCK_SIZE || y > MapCreate.CANVAS_HEIGHT - Parameter.BLOCK_SIZE) {
            boundedBox.setPosition(x_pos, y_pos);
            return false;
        }
        return super.checkFriendlyCollisions(x, y);
    }

    private void revival() {
        if (lifeCount > 0) {
            immortalTime = 100;
            canDie = false;
            alive = true;
            lifeCount--;
            setPosition(x_init, y_init);
        } else {
            alive = false;
            remove();
        }
    }

    public int getSpeed() {
        return speed;
    }

    public Image getUpImage() {
        return Sprite.player_up;
    }

    public Image getDownImage() {
        return Sprite.player_down;
    }

    public Image getRightImage() {
        return Sprite.player_right;
    }

    public Image getLeftImage() {
        return Sprite.player_left;
    }
}
