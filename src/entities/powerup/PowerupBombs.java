package entities.powerup;

import graphics.Parameter;
import entities.player.Player;
import gamelogic.SoundEffect;
import graphics.Sprite;
import gameplay.MapCreate;

public class PowerupBombs extends Powerup {
    public PowerupBombs(int x, int y) {
        super(x, y, Sprite.powerup_bombs);
    }

    public void checkPlayerCollision() {
        if (isColliding(Player.getPlayer())) {
            Player.getPlayer().increaseBombs();
            MapCreate.mapMatrix[y_pos / Parameter.BLOCK_SIZE][x_pos / Parameter.BLOCK_SIZE] = ' ';
            SoundEffect.EAT_PROPS.play(false);
            remove();
        }
    }
}
