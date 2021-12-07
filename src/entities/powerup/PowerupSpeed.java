package entities.powerup;

import constants.Parameter;
import entities.Player;
import gamelogic.SoundEffect;
import graphics.Sprite;
import gameplay.MapCreate;

public class PowerupSpeed extends Powerup {
    public PowerupSpeed(int x, int y) {
        super(x, y, Sprite.powerup_speed);
    }

    public void checkPlayerCollision() {
        if (isColliding(Player.getPlayer())) {
            Player.getPlayer().increaseSpeed();
            MapCreate.mapMatrix[y_pos / Parameter.BLOCK_SIZE][x_pos / Parameter.BLOCK_SIZE] = ' ';
            new SoundEffect("/sound/eatProp.wav").play(false);
            remove();
        }
    }
}
