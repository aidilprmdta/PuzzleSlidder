package sample.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import sample.util.SceneManager;
import sample.config.PuzzleMode;
import sample.model.LevelConfig;
import sample.util.ImageRepository;

public class LevelSelectController {

    @FXML private GridPane gridPane;

    @FXML
    public void initialize() {
        createLevelButtons();
    }

    @FXML
    public void onBack() {
        SceneManager.switchScene("/sample/view/main_menu.fxml");
    }

    private void createLevelButtons() {
        int level = 1;
        int rows = 3;
        int cols = 3;

        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                Button btn = new Button("Level " + level);
                btn.setPrefSize(150, 100); // Lebar dan tinggi desktop

                final int selectedLevel = level;
                btn.setOnAction(e -> {
                    LevelConfig.setLevel(selectedLevel);
                    int gridSize = switch (selectedLevel) {
                        case 1,2,3 -> 4;
                        case 4,5,6 -> 6;
                        default -> 8;
                    };
                    LevelConfig.setGridSize(gridSize);

                    if (LevelConfig.getMode() == PuzzleMode.IMAGE) {
                        String img = ImageRepository.getImageByGrid(gridSize);
                        LevelConfig.setImagePath(img);
                    } else {
                        LevelConfig.setImagePath(null);
                    }

                    SceneManager.switchScene("/sample/view/game.fxml");
                });

                gridPane.add(btn, c, r);
                level++;
            }
        }
    }
}
