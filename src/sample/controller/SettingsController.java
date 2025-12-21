package sample.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Slider;
import sample.util.SceneManager;

public class SettingsController {

    @FXML
    private Slider musicSlider, sfxSlider;

    @FXML
    public void onBack() {
        SceneManager.switchScene("/sample/view/main_menu.fxml");
    }

    // Optional: bisa ditambahkan listener untuk volume
    @FXML
    public void initialize() {
        musicSlider.setValue(0.5);
        sfxSlider.setValue(0.5);
    }
}
