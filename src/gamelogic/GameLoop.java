package gamelogic;

import constants.Parameter;
import entities.Entity;
import entities.SinglePlayer;
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
                }
                System.out.println(now);
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
        SinglePlayer.getPlayer().update();
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
        SinglePlayer.getPlayer().render(graphicsContext);
    }
}
