package gamelogic;

import constants.Director;
import entities.SinglePlayer;
import javafx.scene.input.KeyCode;

import java.util.List;

public class KeyInput {

    public void playerKeyHandler() {
        List<KeyCode> keyBoardInputs = KeyController.getPlayerController();
        SinglePlayer player = SinglePlayer.getPlayer();

        if (keyBoardInputs.contains(KeyCode.UP) || keyBoardInputs.contains(KeyCode.W)) {
            player.move(player.getSpeed(), Director.UP);
        }

        if (keyBoardInputs.contains(KeyCode.DOWN) || keyBoardInputs.contains(KeyCode.S)) {
            player.move(player.getSpeed(), Director.DOWN);
        }

        if (keyBoardInputs.contains(KeyCode.LEFT) || keyBoardInputs.contains(KeyCode.A)) {
            player.move(player.getSpeed(), Director.LEFT);
        }

        if (keyBoardInputs.contains(KeyCode.RIGHT) || keyBoardInputs.contains(KeyCode.D)) {
            player.move(player.getSpeed(), Director.RIGHT);
        }

        if (!keyBoardInputs.contains(KeyCode.UP) && !keyBoardInputs.contains(KeyCode.W)
                && !keyBoardInputs.contains(KeyCode.DOWN) && !keyBoardInputs.contains(KeyCode.S)
                && !keyBoardInputs.contains(KeyCode.LEFT) && !keyBoardInputs.contains(KeyCode.A)
                && !keyBoardInputs.contains(KeyCode.RIGHT) && !keyBoardInputs.contains(KeyCode.D)) {
            player.move(0, Director.DOWN);
        }

        if (keyBoardInputs.contains(KeyCode.SPACE)) {
            player.placeBomb();
        }
    }
}
