package gamelogic;

import constants.Parameter;
import entities.Entity;
import entities.Player;
import javafx.animation.AnimationTimer;
import javafx.scene.canvas.GraphicsContext;
import gameplay.MapCreate;

public class GameLoop {
    public static void start(GraphicsContext graphicsContext) {
        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                graphicsContext.clearRect(0, 0
                        , MapCreate.mapWidth * Parameter.SCALED_SIZE
                        , MapCreate.mapHeight * Parameter.SCALED_SIZE);
                updateGame();
                renderGame(graphicsContext);
                if(MapCreate.pause) {
                    stop();

                    Player.getPlayer().resetBombCount();
                    Player.getPlayer().resetFlames();
                    Player.getPlayer().resetSpeed();
                }
            }
        };
        timer.start();
    }

    private static void updateGame() {
        for (int i = 0; i < MapCreate.getMidLayer().size(); i++) {
            MapCreate.getMidLayer().get(i).update();
        }
        for (int i = 0; i < MapCreate.getTopLayer().size(); i++) {
            MapCreate.getTopLayer().get(i).update();
        }
        for (int i = 0; i < MapCreate.getEnemyLayer().size(); i++) {
            MapCreate.getEnemyLayer().get(i).update();
        }
        Player.getPlayer().update();
        MapCreate.removeEntity();
    }

    private static void renderGame(GraphicsContext graphicsContext) {
        for (Entity entity : MapCreate.getBoardLayer()) {
            entity.render(graphicsContext);
        }
        for (Entity entity : MapCreate.getMidLayer()) {
            entity.render(graphicsContext);
        }
        for (Entity entity : MapCreate.getTopLayer()) {
            entity.render(graphicsContext);
        }
        for (Entity entity : MapCreate.getEnemyLayer()) {
            entity.render(graphicsContext);
        }
        Player.getPlayer().render(graphicsContext);
    }
}
