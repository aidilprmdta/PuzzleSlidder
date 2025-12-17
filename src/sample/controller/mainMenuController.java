package sample.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import sample.util.sceneManager;

public class mainMenuController {

    @FXML
    public void onNumberMode(ActionEvent event) {
        sceneManager.switchScene("/sample/view/level_select.fxml");
    }

    @FXML
    public void onImageMode(ActionEvent event) {
        sceneManager.switchScene("/sample/view/level_select.fxml");
    }

    @FXML
    public void onSettings(ActionEvent event) {
        sceneManager.switchScene("/sample/view/settings.fxml");
    }

    @FXML
    public void onStart(ActionEvent event) {
        sceneManager.switchScene("/sample/view/level_select.fxml");
    }

    @FXML
    public void onExit(ActionEvent event) {
        System.exit(0);
    }

}
