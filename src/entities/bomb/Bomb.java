package entities.bomb;

import constants.Director;
import constants.Parameter;
import entities.AnimatedEntity;
import entities.Entity;
import entities.Player;
import entities.RectangleBox;
import entities.block.Brick;
import graphics.Sprite;
import javafx.scene.image.Image;
import gameplay.MapCreate;
import gamelogic.SoundEffect;

public class Bomb extends AnimatedEntity {
    private int countDownTime = 120;
    private int removeTime = 30;
    private boolean allowToPass = true;
    private boolean exploded = false;

    private BombExplosion explosion;

    private final int explosionTime = 50;

    public Bomb(int x, int y, Image boom) {
        super(x, y, boom);
        boundedBox = new RectangleBox(x, y, Parameter.SCALED_SIZE, Parameter.SCALED_SIZE);
    }

    public Bomb(int x, int y) {
        super(x, y, Sprite.bomb);
        boundedBox = new RectangleBox(x, y, Parameter.SCALED_SIZE, Parameter.SCALED_SIZE);
    }

    public void update() {
        if (countDownTime > 0) {
            countDownTime--;
        } else {
            if (!exploded) {
                setExplosions();
                exploded = true;
                new SoundEffect("/sound/explosion.wav").play(false);
            }
            if (removeTime > 0) {
                removeTime--;
            } else {
                MapCreate.mapMatrix[y_node][x_node] = ' ';
                remove();
            }
        }
        animation();
        playAnimation();
        setAllowToPass();
    }

    public void playAnimation() {
        if (exploded) {
            image = Sprite.playSpriteAnimation(Sprite.bomb_exploded
                    , Sprite.bomb_exploded_1, Sprite.bomb_exploded_2, animate, 30);
        } else {
            image = Sprite.playSpriteAnimation(Sprite.bomb
                    , Sprite.bomb_1, Sprite.bomb_2, animate, 50);
        }
    }

    public boolean isExploded() {
        return exploded;
    }

    private void setAllowToPass() {
        if (!isColliding(Player.getPlayer())) {
            allowToPass = false;
        }
    }

    public boolean allowToPass() {
        return allowToPass;
    }

    public void setExplosions() {
        ExplosionDirection[] explosions = new ExplosionDirection[4];
        Entity entity = MapCreate.getFixedEntityAt(x_pos, y_pos);
        if (entity instanceof Brick) {
            ((Brick) entity).setExploded();
        }
        for (int i = 0; i < explosions.length; i++) {
            explosions[i] = new ExplosionDirection(x_pos, y_pos, Director.dir[i], Player.getPlayer().getBombRadius());
            for (int j = 0; j < explosions[i].getExplosions().length; j++) {
                MapCreate.getTopLayer().add(explosions[i].getExplosions()[j]);
            }
        }
    }
}