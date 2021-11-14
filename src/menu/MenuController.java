package menu;

import gameplay.*;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class MenuController implements Initializable {

    @FXML
    private Button AboutButton, ExitButton;

    @FXML
    private AnchorPane AboutPane;

    @FXML
    public void PlayGame(ActionEvent event) throws Exception {
        Stage gameStage = (Stage)((Node)event.getSource()).getScene().getWindow();
        Pane appRoot = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/gameplay/GameView.fxml")));
        MapCreate.initGame(appRoot);
        Scene scene = new Scene(appRoot);
        gameStage.setScene(scene);
        gameStage.show();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ExitButton.setOnMouseClicked(event -> System.exit(0));

        AboutPane.setTranslateX(976);
        AboutButton.setOnMouseClicked(event -> {
            TranslateTransition slide = new TranslateTransition();
            slide.setDuration(Duration.seconds(0.4));
            slide.setNode(AboutPane);
            slide.setToX(0);
            slide.play();
            AboutPane.setTranslateX(-176);
        });
    }
}

