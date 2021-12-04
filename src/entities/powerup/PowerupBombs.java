package entities.powerup;

import constants.Parameter;
import entities.Player;
import graphics.Sprite;
import javafx.scene.image.Image;
import gameplay.MapCreate;

public class PowerupBombs extends Powerup {

    public PowerupBombs(int x, int y, Image powerup) {
        super(x, y, powerup);
    }

    public PowerupBombs(int x, int y) {
        super(x, y, Sprite.powerup_bombs);
    }

    public void checkPlayerCollision() {
        if (isColliding(Player.getPlayer())) {
            Player.getPlayer().increaseBombs();
            MapCreate.mapMatrix[y_pos / Parameter.BLOCK_SIZE][x_pos / Parameter.BLOCK_SIZE] = ' ';
            remove();
        }
    }
}
