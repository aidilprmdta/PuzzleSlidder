package sample.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Slider;
import sample.util.AudioManager;
import sample.util.SceneManager;

public class SettingsController {

    @FXML private Slider musicSlider, sfxSlider;

    @FXML public void initialize() {
        musicSlider.setValue(AudioManager.getBgmVolume());
        sfxSlider.setValue(AudioManager.getSfxVolume());

        musicSlider.valueProperty().addListener((obs, o, n) ->
                AudioManager.setBgmVolume(n.doubleValue())
        );

        sfxSlider.valueProperty().addListener((obs, o, n) ->
                AudioManager.setSfxVolume(n.doubleValue())
        );
    }

    @FXML public void onBack() {
        SceneManager.switchScene("/sample/view/main_menu.fxml");
    }
}
