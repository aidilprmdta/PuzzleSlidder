package sample.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import sample.model.scoreResult;
import sample.util.sceneManager;

public class winPopupController {

    @FXML
    private Label moveLabel, timeLabel, starLabel;

    @FXML
    public void initialize() {

        moveLabel.setText("Moves: " + scoreResult.getMoves());

        int sec = scoreResult.getSeconds();
        int min = sec / 60;
        int s = sec % 60;
        timeLabel.setText(String.format("Time: %02d:%02d", min, s));

        starLabel.setText("★".repeat(scoreResult.getStars()));
    }

    public void onContinue() {
        sceneManager.switchScene("view/level_select.fxml", "Select Level");
    }

    public void onReplay() {
        sceneManager.switchScene("view/game.fxml", "Puzzle Game");
    }

    public void onExit() {
        sceneManager.switchScene("view/main_menu.fxml", "Puzzle Slider");
    }
}