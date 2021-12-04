package entities.powerup;

import constants.Parameter;
import entities.Entity;
import entities.RectangleBox;
import javafx.scene.image.Image;

public abstract class Powerup extends Entity {

    public Powerup(int x, int y, Image powerup) {
        super(x, y, powerup);
        boundedBox = new RectangleBox(x, y, Parameter.SCALED_SIZE, Parameter.SCALED_SIZE);
    }

    public abstract void checkPlayerCollision();

    public void update() {
        checkPlayerCollision();
    }
}