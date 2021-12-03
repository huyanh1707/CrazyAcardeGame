package gameplay;

import java.io.IOException;
import java.util.Objects;

import gamelogic.GameLoop;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class GameViewController {
    public void switchToMenu(MouseEvent event) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/menu/Menu.fxml")));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root, 850, 624);
        MapCreate.pause = true;
        Canvas canvas = new Canvas();
        GraphicsContext graphicsContext = canvas.getGraphicsContext2D();
        GameLoop.start(graphicsContext);
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.show();
    }
}