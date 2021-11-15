package controller;

import gameplay.Parameter;
import entities.Entity;
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
            }
        };
        timer.start();
    }

    private static void updateGame() {
    }

    private static void renderGame(GraphicsContext graphicsContext) {
        for (Entity entity : MapCreate.getBoardLayer()) {
            entity.render(graphicsContext);
        }
    }
}