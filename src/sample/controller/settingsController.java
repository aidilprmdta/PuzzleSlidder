package sample.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Slider;
import sample.util.audioManager;
import sample.util.sceneManager;

public class settingsController {

    @FXML
    private Slider musicSlider, sfxSlider;

    @FXML
    public void initialize() {
        musicSlider.setValue(audioManager.getMusicVolume());
        sfxSlider.setValue(audioManager.getSfxVolume());

        musicSlider.valueProperty().addListener((obs, oldVal, newVal) ->
                audioManager.setMusicVolume(newVal.doubleValue()));

        sfxSlider.valueProperty().addListener((obs, oldVal, newVal) ->
                audioManager.setSfxVolume(newVal.doubleValue()));
    }

    public void onBack() {
        sceneManager.switchScene("/sample/view/main_menu.fxml");
    }
}
