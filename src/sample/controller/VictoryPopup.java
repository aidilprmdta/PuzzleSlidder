package sample.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import sample.model.ScoreResult;
import sample.util.AudioManager;
import sample.util.SceneManager;

public class VictoryPopup {

    @FXML private Label moveLabel;
    @FXML private Label timeLabel;
    @FXML private Label starLabel;
    @FXML public void initialize() {

        AudioManager.playWin();

        moveLabel.setText("Moves: " + ScoreResult.getMoves());
        int sec = ScoreResult.getSeconds();
        timeLabel.setText(
                String.format("Time: %02d:%02d", sec / 60, sec % 60)
        );

        starLabel.setText("★".repeat(Math.max(0, ScoreResult.getStars())));
    }

    @FXML private void onContinue() {
        SceneManager.switchScene("/sample/view/MainMenu.fxml");
    }

    @FXML private void onReplay() {
        ScoreResult.reset();
        SceneManager.switchScene("/sample/view/GameScreen.fxml");
    }

    @FXML private void onExit() {
        System.exit(0);
    }
}
