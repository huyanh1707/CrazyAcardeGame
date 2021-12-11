package entities.block;

import graphics.Sprite;
import entities.Entity;
import entities.RectangleBox;

public class Grass extends Entity {

    public Grass(int x, int y) {
        super(x, y, Sprite.grass);
        boundedBox = new RectangleBox(x, y, Sprite.BLOCK_SIZE, Sprite.BLOCK_SIZE);
    }

    public void update() {

    }
}
