package sample.controller;

import javafx.fxml.FXML;
import sample.config.PuzzleMode;
import sample.model.LevelConfig;
import sample.util.AudioManager;
import sample.util.SceneManager;

public class MainMenu {

    @FXML private void onNumberMode() {
        AudioManager.playClick();
        LevelConfig.configureNumberMode(4);
        SceneManager.switchScene("/sample/view/LevelSelect.fxml");
    }

    @FXML private void onImageMode() {
        AudioManager.playClick();
        LevelConfig.setMode(PuzzleMode.IMAGE);
        SceneManager.switchScene("/sample/view/LevelSelect.fxml");
    }

    @FXML private void onExit() {
        System.exit(0);
    }
}