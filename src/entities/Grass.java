package entities;

import gameplay.Parameter;
import graphics.Sprite;
import javafx.scene.image.Image;

public class Grass extends Entity {
    public Grass(int x, int y, Image grass) {
        super(x, y, grass);
        boundedBox = new RectangleBox(x, y, Parameter.BLOCK_SIZE, Parameter.BLOCK_SIZE);
    }

    public Grass(int x, int y) {
        super(x, y, Sprite.grass);
        boundedBox = new RectangleBox(x, y, Parameter.BLOCK_SIZE, Parameter.BLOCK_SIZE);
    }

    public void update() {
    }
}
