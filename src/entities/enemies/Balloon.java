package entities.enemies;

import graphics.Sprite;
import javafx.scene.image.Image;

public class Balloon extends Enemy{

    public Balloon(int x, int y, Image balloon) {
        super(x, y, balloon);
        speed = 1;
        movingEnemy = new MovingEnemy(MovingEnemy.IQ.LOW,
                ableToPassBrick, ableToPassWall);
    }

    public Balloon(int x, int y) {
        super(x, y, Sprite.balloon_right);
        speed = 1;
        movingEnemy = new MovingEnemy(MovingEnemy.IQ.LOW,
                ableToPassBrick, ableToPassWall);
    }

    public void playAnimation() {
        if (alive) {
            switch (currentDirection) {
                case UP:
                case RIGHT:
                    image = Sprite.playSpriteAnimation(Sprite.balloon_right
                            , Sprite.balloon_right_2, Sprite.balloon_right_2, animate, 30);
                    break;
                case DOWN:
                case LEFT:
                    image = Sprite.playSpriteAnimation(Sprite.balloon_left
                            , Sprite.balloon_left_1, Sprite.balloon_left_2, animate, 30);
                    break;
            }
        }
    }

    public Image getUpImage() {
        return Sprite.balloon_right;
    }

    public Image getDownImage() {
        return Sprite.balloon_left;
    }

    public Image getRightImage() {
        return Sprite.balloon_right;
    }

    public Image getLeftImage() {
        return Sprite.balloon_left;
    }
}
