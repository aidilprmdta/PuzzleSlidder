package sample.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import sample.model.levelConfig;
import sample.util.sceneManager;

public class levelSelectController {

    @FXML
    private GridPane gridPane;

    @FXML
    public void initialize() {
        createLevelButtons();
    }

    private void createLevelButtons() {

        int level = 1;

        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {

                Button btn = new Button("Level " + level);
                btn.setPrefSize(120, 60);

                final int selectedLevel = level;

                btn.setOnAction(e -> {
                    levelConfig.setLevel(selectedLevel);
                    sceneManager.switchScene("/sample/view/game.fxml");
                });

                gridPane.add(btn, col, row);
                level++;
            }
        }
    }

    public void onBack() {
        sceneManager.switchScene("/sample/view/main_menu.fxml");
    }
}
