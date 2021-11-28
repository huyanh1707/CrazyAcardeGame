package entities.enemies;

import graphics.Sprite;
import javafx.scene.image.Image;

public class Oneal extends Enemy {
    public Oneal(int x, int y, Image oneal) {
        super(x, y, oneal);
        speed = 2;
        movingEnemy = new MovingEnemy(MovingEnemy.IQ.MEDIUM,
                ableToPassBrick, ableToPassWall);
    }

    public Oneal(int x, int y) {
        super(x, y, Sprite.oneal_right);
        speed = 2;
        movingEnemy = new MovingEnemy(MovingEnemy.IQ.MEDIUM,
                ableToPassBrick, ableToPassWall);
    }

    public void playAnimation() {
        if (alive) {
            switch (currentDirection) {
                case UP:
                case RIGHT:
                    image = Sprite.playSpriteAnimation(Sprite.oneal_right
                            , Sprite.oneal_right_2, Sprite.oneal_right_2, animate, 30);
                    break;
                case DOWN:
                case LEFT:
                    image = Sprite.playSpriteAnimation(Sprite.oneal_left
                            , Sprite.oneal_left_1, Sprite.oneal_left_2, animate, 30);
                    break;
            }
        }
    }



    public Image getUpImage() {
        return Sprite.oneal_right;
    }

    public Image getDownImage() {
        return Sprite.oneal_left;
    }

    public Image getRightImage() {
        return Sprite.oneal_right;
    }

    public Image getLeftImage() {
        return Sprite.oneal_left;
    }
}
