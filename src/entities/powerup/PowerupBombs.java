package entities.powerup;

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
            MapCreate.mapMatrix[y_pos / Sprite.BLOCK_SIZE][x_pos / Sprite.BLOCK_SIZE] = ' ';
            SoundEffect.EAT_PROPS.play(false);
            remove();
        }
    }
}
