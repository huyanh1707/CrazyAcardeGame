package entities.bomb;

import constants.Parameter;
import entities.AnimatedEntity;
import entities.RectangleBox;
import entities.SinglePlayer;
import graphics.Sprite;
import javafx.scene.image.Image;
import gameplay.MapCreate;

public class Bomb extends AnimatedEntity {
    private int countDownTime = 120;

    private int removeTime = 30;

    private boolean allowToPass = true;

    private boolean exploded = false;

    public Bomb(int x, int y, Image boom) {
        super(x, y, boom);
        boundedBox = new RectangleBox(x, y, Parameter.SCALED_SIZE, Parameter.SCALED_SIZE);
    }

    public Bomb(int x, int y) {
        super(x, y, Sprite.bomb);
        boundedBox = new RectangleBox(x, y, Parameter.SCALED_SIZE, Parameter.SCALED_SIZE);
    }

    public void update() {
        if (countDownTime > 0) {
            countDownTime--;
        } else {
            if (!exploded) {
                exploded = true;
            }
            if (removeTime > 0) {
                removeTime--;
            } else {
                MapCreate.mapMatrix[y_node][x_node] = ' ';
                remove();
            }
        }
        animation();
        playAnimation();
        setAllowToPass();
    }


    public void playAnimation() {
        if (exploded) {
            image = Sprite.playSpriteAnimation(Sprite.bomb_exploded
                    , Sprite.bomb_exploded_1, Sprite.bomb_exploded_2, animate, 30);
        } else {
            image = Sprite.playSpriteAnimation(Sprite.bomb
                    , Sprite.bomb_1, Sprite.bomb_2, animate, 50);
        }
    }

    public boolean isExploded() {
        return exploded;
    }

    public void setAllowToPass() {
        if (!isColliding(SinglePlayer.getPlayer())) {
            allowToPass = false;
        }
    }

    public boolean allowToPass() {
        return allowToPass;
    }
}
