package sample.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import sample.model.ScoreResult;
import sample.util.SceneManager;

public class WinPopupController {

    @FXML
    private Label starLabel, moveLabel, timeLabel;

    @FXML
    public void initialize() {
        moveLabel.setText("Moves: " + ScoreResult.getMoves());
        timeLabel.setText("Time: " + formatTime(ScoreResult.getTime()));
        starLabel.setText("★★★"); // Bisa disesuaikan dengan stars
    }

    private String formatTime(int seconds) {
        int min = seconds / 60;
        int sec = seconds % 60;
        return String.format("%02d:%02d", min, sec);
    }

    @FXML
    public void onContinue() {
        SceneManager.switchScene("/sample/view/level_select.fxml");
    }

    @FXML
    public void onReplay() {
        SceneManager.switchScene("/sample/view/game.fxml");
    }

    @FXML
    public void onExit() {
        SceneManager.switchScene("/sample/view/main_menu.fxml");
    }
}
