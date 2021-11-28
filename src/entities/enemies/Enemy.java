package entities.enemies;

import entities.Entity;
import entities.MovingEntity;
import entities.RectangleBox;
import gameplay.MapCreate;
import javafx.scene.image.Image;

import java.util.Iterator;

public abstract class Enemy extends MovingEntity {
    protected MovingEnemy movingEnemy;

    public Enemy(int x, int y, Image image) {
        super(x, y, image);
        this.boundedBox = new RectangleBox(x, y, 48, 48);
        this.alive = true;
    }

    public void update() {
        this.animation();
        if (!this.alive) {
            if (this.passAwayTime > 0) {
                --this.passAwayTime;
            } else {
                this.remove();
            }
        }

        this.playAnimation();

        try {
            this.enemySmartMoving();
        } catch (Exception var2) {
            var2.printStackTrace();
        }

    }

    public boolean checkFriendlyCollisions(int x, int y) {
        this.boundedBox.setPosition(x, y);
        Iterator var3 = MapCreate.getTopLayer().iterator();

        Entity entity;
        do {
            if (!var3.hasNext()) {
                return super.checkFriendlyCollisions(x, y);
            }

            entity = (Entity)var3.next();
        }
        while(!this.isColliding(entity));
        this.boundedBox.setPosition(this.x_pos, this.y_pos);
        return false;
    }

    protected void enemySmartMoving() {
        if (this.aBigStep > 0 && this.movableSteps(this.speed, this.currentDirection)) {
            this.move(this.speed, this.currentDirection);
            this.aBigStep -= this.speed;
        } else {
            this.aBigStep = 48;
            switch(this.currentDirection) {
                case UP:
                    --this.y_node;
                    break;
                case DOWN:
                    ++this.y_node;
                    break;
                case LEFT:
                    --this.x_node;
                    break;
                case RIGHT:
                    ++this.x_node;
            }

            this.currentDirection = this.movingEnemy.movingDirection(MapCreate.mapMatrix, this.x_node, this.y_node);
        }

    }
}
