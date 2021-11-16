package entities;

import gameplay.Parameter;
import graphics.Sprite;
import javafx.scene.image.Image;

public class Brick extends Entity {
    public Brick(int x, int y, Image brick) {
        super(x, y, brick);
        boundedBox = new RectangleBox(x, y, Parameter.BLOCK_SIZE, Parameter.BLOCK_SIZE);
    }

    public Brick(int x, int y) {
        super(x, y, Sprite.brick);
        boundedBox = new RectangleBox(x, y, Parameter.BLOCK_SIZE, Parameter.BLOCK_SIZE);
    }

    public void update() {
    }
}
