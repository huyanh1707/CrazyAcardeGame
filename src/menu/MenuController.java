package menu;

import gamelogic.SoundEffect;
import gameplay.*;
import javafx.animation.TranslateTransition;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.net.URL;
import java.util.*;

public class MenuController implements Initializable {

    @FXML
    private Button SinglePlayerButton, ExitButton, MultiplayerButton, Button3, TutorialButton;

    @FXML
    private AnchorPane GamePane, Pane2, Pane3, TutorialPane;

    @FXML
    private RadioButton blue, red, green;

    @FXML
    public void PlayGame(MouseEvent event) throws Exception {
        Stage gameStage = (Stage)((Node)event.getSource()).getScene().getWindow();
        Pane appRoot = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/gameview/GameView.fxml")));
        Scene scene = new Scene(appRoot);
        MapCreate.initGame(appRoot, scene);
        gameStage.setScene(scene);
        gameStage.centerOnScreen();
        gameStage.show();
        SoundEffect.BACKGROUND.stop();
        SoundEffect.GAMESTART.play(false);
        SoundEffect.GAMEPLAY.play(true);
    }

    @FXML
    public void PlayMultiPlayerGame(MouseEvent event) throws Exception {
        Stage gameStage = (Stage)((Node)event.getSource()).getScene().getWindow();
        Pane appRoot = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/gameview/GameMPView.fxml")));
        Scene scene = new Scene(appRoot);
        MapCreate.initMultiPlayerGame(appRoot, scene);
        gameStage.setScene(scene);
        gameStage.centerOnScreen();
        gameStage.show();
        SoundEffect.BACKGROUND.stop();
        SoundEffect.GAMESTART.play(false);
        SoundEffect.GAMEPLAY.play(true);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        SinglePlayerButton.setOnAction(event -> {
            if(Pane2.getTranslateX() == -720) {
                moveChildPane(Pane2);
            }
            if(Pane3.getTranslateX() == -720) {
                moveChildPane(Pane3);
            }
            else if(TutorialPane.getTranslateX() == -720) {
                moveChildPane(TutorialPane);
            }
            moveChildPane(GamePane);
        });
        MultiplayerButton.setOnAction(event -> {
            if(GamePane.getTranslateX() == -720) {
                moveChildPane(GamePane);
            }
            if(Pane3.getTranslateX() == -720) {
                moveChildPane(Pane3);
            }
            else if(TutorialPane.getTranslateX() == -720) {
                moveChildPane(TutorialPane);
            }
            moveChildPane(Pane2);
        });
        Button3.setOnAction(event -> {
            if(GamePane.getTranslateX() == -720) {
                moveChildPane(GamePane);
            }
            if(Pane2.getTranslateX() == -720) {
                moveChildPane(Pane2);
            }
            else if(TutorialPane.getTranslateX() == -720) {
                moveChildPane(TutorialPane);
            }
            moveChildPane(Pane3);
        });
        TutorialButton.setOnAction(event -> {
            if(GamePane.getTranslateX() == -720) {
                moveChildPane(GamePane);
            }
            if(Pane3.getTranslateX() == -720) {
                moveChildPane(Pane3);
            }
            else if(Pane2.getTranslateX() == -720) {
                moveChildPane(Pane2);
            }
            moveChildPane(TutorialPane);
        });
        ExitButton.setOnAction(event -> System.exit(0));
    }

    private void moveChildPane(AnchorPane childPane) {
        TranslateTransition slide = new TranslateTransition();
        slide.setDuration(Duration.seconds(0.3));
        slide.setNode(childPane);
        if((childPane.getTranslateX() == 0)) {
            slide.setToX(-720);
        } else {
            slide.setToX(0);
        }
        slide.play();
    }
}

