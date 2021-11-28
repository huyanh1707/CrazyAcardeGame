package entities.enemies;

import graphics.Sprite;
import javafx.scene.image.Image;


public class Ballom extends Enemy{

    public Ballom(int x, int y, Image ballom) {
        super(x, y, ballom);
        speed = 1;
        movingEnemy = new MovingEnemy(MovingEnemy.IQ.LOW,
                ableToPassBrick, ableToPassWall);
    }

    public Ballom(int x, int y) {
        super(x, y, Sprite.ballom_right);
        speed = 1;
        movingEnemy = new MovingEnemy(MovingEnemy.IQ.LOW,
                ableToPassBrick, ableToPassWall);
    }

    public void playAnimation() {
        if (alive) {
            switch (currentDirection) {
                case UP:
                case RIGHT:
                    image = Sprite.playSpriteAnimation(Sprite.ballom_right
                            , Sprite.ballom_right_2, Sprite.ballom_right_2, animate, 30);
                    break;
                case DOWN:
                case LEFT:
                    image = Sprite.playSpriteAnimation(Sprite.ballom_left
                            , Sprite.ballom_left_1, Sprite.ballom_left_2, animate, 30);
                    break;
            }
        }
    }

    public Image getUpImage() {
        return Sprite.ballom_right;
    }

    public Image getDownImage() {
        return Sprite.ballom_left;
    }

    public Image getRightImage() {
        return Sprite.ballom_right;
    }

    public Image getLeftImage() {
        return Sprite.ballom_left;
    }
}
