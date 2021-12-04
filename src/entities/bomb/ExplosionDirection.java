package entities.bomb;

import constants.Parameter;
import constants.Director;
import entities.Entity;
import entities.block.Brick;
import entities.block.Wall;
import gameplay.MapCreate;

public class ExplosionDirection {

    private int x_init, y_init;

    private int radius;

    private Director dir;

    protected BombExplosion[] explosions;

    public ExplosionDirection(int x, int y, Director dir, int r) {
        this.dir = dir;
        x_init = x;
        y_init = y;
        radius = r;
        explosions = new BombExplosion[calculateExplosionRadius()];
        createBombExplosion();
    }

    private int calculateExplosionRadius() {
        int r = 0;
        int x = x_init;
        int y = y_init;
        while (r < radius) {
            switch (dir) {
                case UP:
                    y -= Parameter.SCALED_SIZE;
                    break;
                case DOWN:
                    y += Parameter.SCALED_SIZE;
                    break;
                case LEFT:
                    x -= Parameter.SCALED_SIZE;
                    break;
                case RIGHT:
                    x += Parameter.SCALED_SIZE;
                    break;
            }
            Entity entity = MapCreate.getFixedEntityAt(x, y);
            if (entity instanceof Wall || entity instanceof Brick) {
                break;
            }
            r++;
        }
        return r;
    }

    private void createBombExplosion() {
        boolean last = false;
        int x = x_init;
        int y = y_init;

        for (int i = 0; i < explosions.length; i++) {
            last = (i == explosions.length - 1);

            switch (dir) {
                case UP:
                    y -= Parameter.SCALED_SIZE;
                    break;
                case DOWN:
                    y += Parameter.SCALED_SIZE;
                    break;
                case LEFT:
                    x -= Parameter.SCALED_SIZE;
                    break;
                case RIGHT:
                    x += Parameter.SCALED_SIZE;
                    break;
            }


            explosions[i] = new BombExplosion(x, y, dir, last);
            MapCreate.getTopLayer().add(explosions[i]);

        }

    }

    public BombExplosion[] getExplosions() {
        return explosions;
    }

    public void update() {
        for (BombExplosion explosion : explosions) {
            explosion.update();
        }
    }

}
