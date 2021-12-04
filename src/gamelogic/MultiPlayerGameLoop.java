package gamelogic;

import constants.Parameter;
import entities.Entity;
import entities.Player;
import javafx.animation.AnimationTimer;
import javafx.scene.canvas.GraphicsContext;
import gameplay.MultiPlayerMapCreate;

public class MultiPlayerGameLoop {

    public static void start(GraphicsContext graphicsContext) {
        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                graphicsContext.clearRect(0, 0
                        , MultiPlayerMapCreate.mapWidth * Parameter.SCALED_SIZE
                        , MultiPlayerMapCreate.mapHeight * Parameter.SCALED_SIZE);
                updateGame();
                renderGame(graphicsContext);
            }
        };
        timer.start();
    }

    private static void updateGame() {
        for (int i = 0; i < MultiPlayerMapCreate.getMidLayer().size(); i++) {
            MultiPlayerMapCreate.getMidLayer().get(i).update();
        }
        for (int i = 0; i < MultiPlayerMapCreate.getTopLayer().size(); i++) {
            MultiPlayerMapCreate.getTopLayer().get(i).update();
        }
        Player.getPlayer().update();
        MultiPlayerMapCreate.removeEntity();
    }

    private static void renderGame(GraphicsContext graphicsContext) {
        for (Entity entity : MultiPlayerMapCreate.getBoardLayer()) {
            entity.render(graphicsContext);
        }
        for (Entity entity : MultiPlayerMapCreate.getMidLayer()) {
            entity.render(graphicsContext);
        }
        for (Entity entity : MultiPlayerMapCreate.getTopLayer()) {
            entity.render(graphicsContext);
        }
        Player.getPlayer().render(graphicsContext);
    }
}
