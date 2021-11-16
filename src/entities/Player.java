package entities;

import gameplay.Parameter;
import graphics.Sprite;
import javafx.scene.image.Image;

public class Player extends Entity {
    public Player(int x, int y, Image player) {
        super(x, y, player);
        boundedBox = new RectangleBox(x, y, Parameter.BLOCK_SIZE, Parameter.BLOCK_SIZE);
    }

    public Player(int x, int y) {
        super(x, y, Sprite.player_left);
        boundedBox = new RectangleBox(x, y, Parameter.BLOCK_SIZE, Parameter.BLOCK_SIZE);
    }

    public void update() {
    }
}
