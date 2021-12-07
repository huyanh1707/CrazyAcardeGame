package gameplay;

import java.awt.*;
import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

import gamelogic.GameLoop;
//import gamelogic.MultiPlayerGameLoop;
import gamelogic.SoundEffect;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class MultiPlayerController implements Initializable {
//    @FXML
//    private Label Life;
//    @FXML
//    private Label Power;
//    @FXML
//    private Label Bomb;
//    @FXML
//    private Label Enemies;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }

    public void switchToMenu(MouseEvent event) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/menu/Menu.fxml")));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root, 850, 624);
        MapCreate.pause2 = true;
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.show();
        SoundEffect.GAMEPLAY.stop();
        SoundEffect.BACKGROUND.play(true);
    }
}