package sample.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import sample.util.SceneManager;
import sample.config.PuzzleMode;
import sample.model.LevelConfig;

public class MainMenuController {

    @FXML
    public void onNumberMode(ActionEvent event) {
        LevelConfig.setMode(PuzzleMode.NUMBER);
        SceneManager.switchScene("/sample/view/level_select.fxml");
    }

    @FXML
    public void onImageMode(ActionEvent event) {
        LevelConfig.setMode(PuzzleMode.IMAGE);
        SceneManager.switchScene("/sample/view/level_select.fxml");
    }

    @FXML
    public void onSettings(ActionEvent event) {
        SceneManager.switchScene("/sample/view/settings.fxml");
    }

    @FXML
    public void onStart(ActionEvent event) {
        SceneManager.switchScene("/sample/view/level_select.fxml");
    }

    @FXML
    public void onExit(ActionEvent event) {
        System.exit(0);
    }
}
