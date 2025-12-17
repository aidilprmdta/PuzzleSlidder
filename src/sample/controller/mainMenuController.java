package sample.controller;

import javafx.application.Platform;
import sample.util.sceneManager;

public class mainMenuController {

    public void onStart() {
        sceneManager.switchScene("view/level_select.fxml", "Select Level");
    }

    public void onSettings() {
        sceneManager.switchScene("view/settings.fxml", "Settings");
    }

    public void onExit() {
        Platform.exit();
    }
}
