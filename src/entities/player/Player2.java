package entities.player;

import entities.Entity;
import entities.MovingEntity;
import entities.RectangleBox;
import entities.bomb.Bomb;
import entities.bomb.BombExplosion;
import graphics.Sprite;
import entities.bomb.Bomb2;
import entities.enemies.Enemy;
import gamelogic.KeyInput;
import gamelogic.SoundEffect;
import gameplay.MapCreate;
import javafx.scene.image.Image;

import java.util.ArrayList;
import java.util.List;

public class Player2 extends MovingEntity {

    private static Player2 player2 = null;

    private String name = "";
    private int bombCount = 1;
    private int bombRadius = 1;
    private int speed;
    private int placedBombs;
    private int immortalTime = 100;
    private int lifeCount = 7;

    private boolean ableToPassFlame = false;
    private boolean ableToPassBomb = false;
    private boolean ableToPlaceBomb = true;
    private static boolean canDie = false;

    private final int x_init, y_init;

    KeyInput input;

    private final List<Bomb2> bombList = new ArrayList<>();

    public Player2(int x, int y, Image player) {
        super(x, y, player);
        boundedBox = new RectangleBox(x, y, Sprite.SCALED_SIZE - 10, Sprite.SCALED_SIZE - 2);
        alive = true;
        input = new KeyInput();
        x_init = x;
        y_init = y;
        speed = 2;
    }

    public Player2(int x, int y) {
        super(x, y, Sprite.player_right);
        boundedBox = new RectangleBox(x, y, Sprite.SCALED_SIZE - 10, Sprite.SCALED_SIZE - 2);
        alive = true;
        input = new KeyInput();
        x_init = x;
        y_init = y;
        speed = 2;
    }

    public static Player2 setPlayer(int x, int y, boolean newOne) {
        if (player2 == null || newOne) {
            player2 = new Player2(x, y);
        } else {
            player2.setPosition(x, y);
        }
        canDie = false;
        return player2;
    }

    public static Player2 getPlayer() {
        return player2;
    }

    public void update() {
        animation();
        checkBombCollision();
        checkEnemyCollision();
        recountBombs();
        playAnimation();
        try {
            input.player2KeyHandler();
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (immortalTime > 0 && !canDie) {
            immortalTime--;
        } else {
            canDie = true;
        }
        x_node = x_pos / Sprite.BLOCK_SIZE;
        y_node = y_pos / Sprite.BLOCK_SIZE;
    }

    public void playAnimation() {
        if (alive) {
            switch(currentDirection) {
                case UP:
                    if (isMoving) {
                        image = Sprite.playSpriteAnimation(Sprite.player2_up
                                , Sprite.player2_up_1, Sprite.player2_up_2, animate, 20);
                    }
                    break;
                case DOWN:
                    if (isMoving) {
                        image = Sprite.playSpriteAnimation(Sprite.player2_down
                                , Sprite.player2_down_1, Sprite.player2_down_2, animate, 20);
                    }
                    break;
                case RIGHT:
                    if (isMoving) {
                        image = Sprite.playSpriteAnimation(Sprite.player2_right
                                , Sprite.player2_right_1, Sprite.player2_right_2, animate, 20);
                    }
                    break;
                case LEFT:
                    if (isMoving) {
                        image = Sprite.playSpriteAnimation(Sprite.player2_left
                                , Sprite.player2_left_1, Sprite.player2_left_2, animate, 20);
                    }
                    break;
            }
        } else {
            image = Sprite.playSpriteAnimation(Sprite.player2_dead
                    , Sprite.player2_dead_1, Sprite.player2_dead_2, animate, 30);
        }
    }

    public void setPosition(int x, int y) {
        x_pos = x;
        y_pos = y;
        boundedBox.setPosition(x, y);
    }

    @Override
    public boolean checkFriendlyCollisions(int x, int y) {
        boundedBox.setPosition(x, y);
        for (Entity entity : MapCreate.getTopLayer()) {
            if (entity instanceof Bomb2 && isColliding(entity)
                    && !((Bomb2) entity).allowToPass2() && !ableToPassBomb) {
                boundedBox.setPosition(x_pos, y_pos);
                return false;
            }
        }
        if (x < Sprite.BLOCK_SIZE || x > MapCreate.CANVAS_WIDTH - Sprite.BLOCK_SIZE) {
            boundedBox.setPosition(x_pos, y_pos);
            return false;
        }
        if (y < Sprite.BLOCK_SIZE || y > MapCreate.CANVAS_HEIGHT - Sprite.BLOCK_SIZE) {
            boundedBox.setPosition(x_pos, y_pos);
            return false;
        }
        return super.checkFriendlyCollisions(x, y);
    }

    public void checkEnemyCollision() {
        for (Entity entity : MapCreate.getEnemyLayer()) {
            if (entity instanceof Enemy && isColliding(entity) && canDie) {
                revival();
                break;
            }
        }
    }

    public void checkBombCollision() {
        for (Entity entity : MapCreate.getTopLayer()) {
            if (entity instanceof BombExplosion
                    && isColliding(entity) && canDie && !ableToPassFlame) {
                revival();
                break;
            }
            if (entity instanceof Bomb && isColliding(entity)
                    && ((Bomb) entity).isExploded() && !ableToPassFlame) {
                revival();
                break;
            }
        }
    }

    private void revival() {
        if (lifeCount > 0) {
            SoundEffect.REVIVAL.play(false);
            immortalTime = 100;
            canDie = false;
            alive = true;
            lifeCount--;
            setPosition(x_init, y_init);
            setImage(Sprite.player2_left);
        } else {
            MapCreate.LoseEffect();
            alive = false;
            remove();
        }
    }


    public void placeBomb() {
        ableToPlaceBomb = true;
        int x_bomb = ((x_pos + Sprite.SCALED_SIZE / 2) / Sprite.SCALED_SIZE) * Sprite.SCALED_SIZE;
        int y_bomb = ((y_pos + Sprite.SCALED_SIZE / 2) / Sprite.SCALED_SIZE) * Sprite.SCALED_SIZE;
        for (Entity bomb : bombList) {
            if (bomb.getX_pos() == x_bomb && bomb.getY_pos() == y_bomb) {
                ableToPlaceBomb = false;
                break;
            }
        }
        if (placedBombs < bombCount && ableToPlaceBomb && alive) {
            Bomb2 bomb = new Bomb2(x_bomb, y_bomb);
            MapCreate.getTopLayer().add(bomb);
            bombList.add(bomb);
            MapCreate.mapMatrix[y_bomb / Sprite.BLOCK_SIZE][x_bomb / Sprite.BLOCK_SIZE] = '*';
            SoundEffect.PLACE_BOMB.play(false);
        }
    }

    private void recountBombs() {
        placedBombs = bombList.size();
        for (int i = 0; i < bombList.size(); i++) {
            if (bombList.get(i).isRemoved()) {
                bombList.remove(i);
                --i;
            }
        }
    }

    public void resetPlayer2() {
        setImage(Sprite.player2_left);
        alive = true;
        resetPlaceAble();
        resetBombCount();
        resetFlames();
        resetSpeed();
        resetLife();
        bombList.clear();
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getX_node() {
        return x_node;
    }

    public int getY_node() {
        return y_node;
    }

    public int getBombRadius() {
        return bombRadius;
    }

    public void increaseFlames() {
        bombRadius++;
    }

    public void resetFlames() {
        bombRadius = 1;
    }

    public void resetPlaceAble() {
        ableToPlaceBomb = true;
    }

    public int getRemainBombs() {
        return bombCount - placedBombs;
    }

    public int getLifeCount() {
        return lifeCount;
    }

    public void setLifeCount() {
        ++lifeCount;
    }

    public void resetBombList() {
        bombList.clear();
    }

    public void increaseBombs() {
        bombCount++;
    }

    public void resetBombCount() {
        bombCount = 1;
    }

    public void resetLife() { lifeCount = 1;}

    public int getSpeed() {
        return speed;
    }

    public void resetSpeed() {
        speed = 2;
    }

    public void increaseSpeed() {
        speed ++;
    }

    public Image getUpImage() {
        return Sprite.player2_up;
    }

    public Image getDownImage() {
        return Sprite.player2_down;
    }

    public Image getRightImage() {
        return Sprite.player2_right;
    }

    public Image getLeftImage() {
        return Sprite.player2_left;
    }
}
