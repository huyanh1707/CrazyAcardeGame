package entities.block;

import graphics.Parameter;
import entities.Entity;
import entities.RectangleBox;
import graphics.Sprite;

public class Wall extends Entity {
    public Wall(int x, int y) {
        super(x, y, Sprite.wall);
        boundedBox = new RectangleBox(x, y, Parameter.BLOCK_SIZE, Parameter.BLOCK_SIZE);
    }

    public void update() {
    }
}
