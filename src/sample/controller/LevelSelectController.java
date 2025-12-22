package sample.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import sample.model.LevelConfig;
import sample.util.SceneManager;

import java.util.Map;

public class LevelSelectController {

    @FXML private GridPane numberGrid;
    @FXML private GridPane imageGrid;

    record ImageLevel(int grid, String imagePath) {}

    private static final Map<Integer, ImageLevel> IMAGE_LEVELS = Map.of(
            1, new ImageLevel(3, "/images/level1.png"),
            2, new ImageLevel(4, "/images/level2.jpg"),
            3, new ImageLevel(5, "/images/level3.jpg"),
            4, new ImageLevel(5, "/images/level4.png"),
            5, new ImageLevel(5, "/images/level5.jpg"),
            6, new ImageLevel(5, "/images/level6.png"),
            7, new ImageLevel(5, "/images/level7.jpg"),
            8, new ImageLevel(5, "/images/level8.jpg"),
            9, new ImageLevel(5, "/images/level9.jpg")
    );

    private static final Map<Integer, Integer> NUMBER_LEVELS = Map.of(
            1, 3,
            2, 3,
            3, 4,
            4, 4,
            5, 5,
            6, 5
    );

    @FXML
    public void initialize() {
        createNumberButtons();
        createImageButtons();
    }

    @FXML
    public void onBack() {
        SceneManager.switchScene("/sample/view/main_menu.fxml");
    }

    /* =======================
       NUMBER MODE
       ======================= */
    private void createNumberButtons() {
        int col = 0;
        int row = 0;

        for (int level = 1; level <= NUMBER_LEVELS.size(); level++) {

            int grid = NUMBER_LEVELS.get(level);
            final int selectedLevel = level;

            Button btn = new Button("Level " + level + "\n" + grid + " x " + grid);
            btn.setPrefSize(150, 100);

            btn.setOnAction(e -> {
                LevelConfig.configureNumberMode(grid);
                LevelConfig.setLevel(selectedLevel);
                SceneManager.switchScene("/sample/view/game.fxml");
            });

            numberGrid.add(btn, col, row);

            col++;
            if (col == 3) {
                col = 0;
                row++;
            }
        }
    }

    /* =======================
       IMAGE MODE
       ======================= */
    private void createImageButtons() {
        int level = 1;

        for (int r = 0; r < 3; r++) {
            for (int c = 0; c < 3; c++) {

                final int selectedLevel = level++;
                ImageLevel data = IMAGE_LEVELS.get(selectedLevel);

                Button btn = new Button("Level " + selectedLevel);
                btn.setPrefSize(150, 100);

                btn.setOnAction(e -> {
                    LevelConfig.configureImageMode(
                            data.grid(),
                            data.imagePath()
                    );
                    LevelConfig.setLevel(selectedLevel);
                    SceneManager.switchScene("/sample/view/game.fxml");
                });

                imageGrid.add(btn, c, r);
            }
        }
    }
}
