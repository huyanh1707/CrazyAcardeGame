package entities.powerup;

import constants.Parameter;
import entities.Player;
import gamelogic.SoundEffect;
import graphics.Sprite;

import gameplay.MapCreate;

public class PowerupFlames extends Powerup {
    public PowerupFlames(int x, int y) {
        super(x, y, Sprite.powerup_flames);
    }

    public void checkPlayerCollision() {
        if (isColliding(Player.getPlayer())) {
            Player.getPlayer().increaseFlames();
            MapCreate.mapMatrix[y_pos / Parameter.BLOCK_SIZE][x_pos / Parameter.BLOCK_SIZE] = ' ';
            new SoundEffect("/sound/eatProp.wav").play(false);
            remove();
        }
    }
}
