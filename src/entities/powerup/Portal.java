package entities.powerup;

import constants.Parameter;
import entities.Entity;
import entities.Player;
import entities.RectangleBox;
import gameplay.MapCreate;
import graphics.Sprite;
import javafx.scene.image.Image;

public class Portal extends Powerup {
    public Portal(int x, int y) {
        super(x, y, Sprite.portal);
    }
    public Portal(int x, int y, Image powerup) {
        super(x, y, powerup);
    }

    public void checkPlayerCollision() {
        if (isColliding(Player.getPlayer()) && MapCreate.getEnemyLayer().size() == 0) {
            MapCreate.nextLevel();
        }
    }

    public void update() {
        checkPlayerCollision();
    }
}