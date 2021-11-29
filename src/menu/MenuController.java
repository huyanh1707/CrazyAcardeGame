package menu;

import gameplay.*;
import javafx.animation.TranslateTransition;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class MenuController implements Initializable {

    @FXML
    private Button PlayButton, ExitButton, Button2, Button3, TutorialButton;

    @FXML
    private AnchorPane GamePane, Pane2, Pane3, TutorialPane;

    private boolean isHidden;

    @FXML
    public void PlayGame(MouseEvent event) throws Exception {
        Stage gameStage = (Stage)((Node)event.getSource()).getScene().getWindow();
        Pane appRoot = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/gameview/GameView.fxml")));
        Scene scene = new Scene(appRoot);
        MapCreate.initGame(appRoot, scene);
        gameStage.setScene(scene);
        gameStage.centerOnScreen();
        gameStage.show();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Button2.setOnAction(event -> {
            moveChildPane(Pane2);
        });
        Button3.setOnAction(event -> {
            moveChildPane(Pane3);
        });
        TutorialButton.setOnAction(event -> {
            moveChildPane(TutorialPane);
        });
        ExitButton.setOnAction(event -> System.exit(0));
    }

    private void moveChildPane(AnchorPane childPane) {
        TranslateTransition slide = new TranslateTransition();
        slide.setDuration(Duration.seconds(0.3));
        slide.setNode(childPane);
        if(isHidden) {
            slide.setToX(-720);
            isHidden = false;
        } else {
            slide.setToX(0);
            isHidden = true;
        }
        slide.play();
    }
}

