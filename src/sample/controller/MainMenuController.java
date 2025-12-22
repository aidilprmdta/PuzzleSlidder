package sample.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import sample.config.PuzzleMode;
import sample.model.LevelConfig;
import sample.util.AudioManager;
import sample.util.SceneManager;

public class MainMenuController {

    @FXML private Button btnStart, btnNumber, btnImage, btnSettings, btnExit;

    @FXML
    public void initialize() {
        AudioManager.playMenuBGM();
        AudioManager.stopBGM();
    }

    @FXML
    private void onStart() {
        AudioManager.playClick();
        AudioManager.stopBGM();
        SceneManager.switchScene("/sample/view/level_select.fxml");
    }

    @FXML
    private void onNumberMode() {
        AudioManager.playClick();
        LevelConfig.configureNumberMode(4);
        SceneManager.switchScene("/sample/view/game.fxml");
    }

    @FXML
    private void onImageMode() {
        AudioManager.playClick();
        LevelConfig.setMode(PuzzleMode.IMAGE);
        SceneManager.switchScene("/sample/view/level_select.fxml");
    }

    @FXML
    private void onSettings() {
        AudioManager.playClick();
        SceneManager.switchScene("/sample/view/settings.fxml");
    }

    @FXML
    private void onExit() {
        AudioManager.stopBGM();
        System.exit(0);
    }
}
