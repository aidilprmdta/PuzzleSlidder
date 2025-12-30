package sample.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import sample.config.PuzzleMode;
import sample.model.LevelConfig;
import sample.util.AudioManager;
import sample.util.SceneManager;

import java.util.Map;

public class LevelSelect {

    @FXML private GridPane numberGrid;
    @FXML private GridPane imageGrid;

    record ImageLevel(int grid, String imagePath) {
    }

    private static final Map<Integer, ImageLevel> IMAGE_LEVELS = Map.of(
            1, new ImageLevel(3, "/images/Level1.jpeg"),
            2, new ImageLevel(4, "/images/Level2.jpeg"),
            3, new ImageLevel(5, "/images/Level3.jpeg"),
            4, new ImageLevel(5, "/images/Level4.jpeg"),
            5, new ImageLevel(5, "/images/Level5.jpeg"),
            6, new ImageLevel(5, "/images/Level6.jpeg"),
            7, new ImageLevel(5, "/images/Level7.jpeg"),
            8, new ImageLevel(5, "/images/Level8.jpeg"),
            9, new ImageLevel(5, "/images/Level9.jpeg")
    );

    private static final Map<Integer, Integer> NUMBER_LEVELS = Map.of(
            1, 2,
            2, 3,
            3, 4,
            4, 5,
            5, 6,
            6, 7
    );

    @FXML public void initialize() {
        PuzzleMode mode = LevelConfig.getMode();

        numberGrid.getChildren().clear();
        imageGrid.getChildren().clear();

        if (mode == PuzzleMode.NUMBER) {
            imageGrid.setVisible(false);
            createNumberButtons();
        } else {
            numberGrid.setVisible(false);
            createImageButtons();
        }

        System.out.println("LEVEL SELECT MODE = " + mode);
    }

    @FXML private void onBack() {
        AudioManager.playClick();
        SceneManager.switchScene("/sample/view/MainMenu.fxml");
    }

    private void createNumberButtons() {
        final int[] col = {0};
        final int[] row = {0};

        NUMBER_LEVELS.keySet().stream()
                .sorted()
                .forEach(level -> {
                    int grid = NUMBER_LEVELS.get(level);

                    Button btn = new Button("Level " + level + "\n" + grid + "x" + grid);
                    btn.getStyleClass().add("level-card");
                    btn.setPrefSize(150, 100);
                    btn.setOnAction(e -> {
                        AudioManager.playClick();
                        LevelConfig.setLevel(level);
                        LevelConfig.configureNumberMode(grid);
                        SceneManager.switchScene("/sample/view/GameScreen.fxml");
                    });

                    numberGrid.add(btn, col[0], row[0]);

                    col[0]++;
                    if (col[0] == 3) {
                        col[0] = 0;
                        row[0]++;
                    }
                });
    }

    private void createImageButtons() {
        final int[] col = {0};
        final int[] row = {0};

        IMAGE_LEVELS.keySet().stream()
                .sorted()
                .forEach(level -> {
                    ImageLevel data = IMAGE_LEVELS.get(level);

                    Button btn = new Button("Level " + level);
                    btn.getStyleClass().add("level-card");
                    btn.setPrefSize(150, 100);
                    btn.setOnAction(e -> {
                        AudioManager.playClick();
                        LevelConfig.setLevel(level);
                        LevelConfig.configureImageMode(
                                data.grid(),
                                data.imagePath()
                        );
                        SceneManager.switchScene("/sample/view/GameScreen.fxml");
                    });

                    imageGrid.add(btn, col[0], row[0]);

                    col[0]++;
                    if (col[0] == 3) {
                        col[0] = 0;
                        row[0]++;
                    }
                });
    }
}