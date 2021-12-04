package entities.powerup;

import constants.Parameter;
import entities.Player;
import graphics.Sprite;
import javafx.scene.image.Image;
import gameplay.MapCreate;

public class PowerupSpeed extends Powerup {

    public PowerupSpeed(int x, int y, Image powerup) {
        super(x, y, powerup);
    }

    public PowerupSpeed(int x, int y) {
        super(x, y, Sprite.powerup_speed);
    }

    public void checkPlayerCollision() {
        if (isColliding(Player.getPlayer())) {
            Player.getPlayer().increaseSpeed();
            MapCreate.mapMatrix[y_pos / Parameter.BLOCK_SIZE][x_pos / Parameter.BLOCK_SIZE] = ' ';
            remove();
        }
    }
}
