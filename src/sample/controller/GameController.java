package sample.controller;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.geometry.Rectangle2D;
import javafx.util.Duration;
import sample.config.PuzzleMode;
import sample.model.LevelConfig;
import sample.model.ScoreResult;
import sample.util.AudioManager;
import sample.util.SceneManager;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GameController {

    @FXML private GridPane levelGrid;
    @FXML private Label moveLabel, timeLabel;

    private Button[][] board;
    private int size;
    private int emptyRow, emptyCol;
    private int moves;
    private int seconds;

    private Image puzzleImage;
    private Timeline timer;
    private final Random random = new Random();
    private boolean gameFinished;

    @FXML
    public void initialize() {
        size = LevelConfig.getGridSize();

        levelGrid.getChildren().clear();
        moves = 0;
        seconds = 0;
        gameFinished = false;

        moveLabel.setText("Moves: 0");
        timeLabel.setText("Time: 00:00");

        if (LevelConfig.getMode() == PuzzleMode.IMAGE) {
            String path = LevelConfig.getImagePath();
            if (path == null)
                throw new RuntimeException("Image path not set");
            puzzleImage = new Image(getClass().getResource(path).toExternalForm());
        }

        board = new Button[size][size];
        emptyRow = size - 1;
        emptyCol = size - 1;

        createBoard();
        shuffle();
        startTimer();

        AudioManager.stopBGM();
        AudioManager.playGameBGM();

        if (LevelConfig.getMode() == PuzzleMode.IMAGE) {
            String path = LevelConfig.getImagePath();
            if (path == null) {
                System.err.println("⚠ IMAGE MODE tanpa image path, fallback ke NUMBER");
                LevelConfig.setMode(PuzzleMode.NUMBER);
            } else {
                puzzleImage = new Image(getClass().getResource(path).toExternalForm());
            }
        }

        System.out.println("MODE = " + LevelConfig.getMode());
    }

    private void createBoard() {
        int value = 1;
        double tileSize = 120;

        for (int r = 0; r < size; r++) {
            for (int c = 0; c < size; c++) {

                Button tile;

                if (r == size - 1 && c == size - 1) {
                    tile = new Button();
                    tile.setUserData(0);
                    tile.setDisable(true);
                    tile.setVisible(false);
                    emptyRow = r;
                    emptyCol = c;
                } else {
                    if (LevelConfig.getMode() == PuzzleMode.NUMBER) {
                        tile = createNumberTile(value++);
                    } else {
                        tile = createImageTile(r, c);
                    }

                    tile.setOnAction(e -> {
                        Integer row = GridPane.getRowIndex(tile);
                        Integer col = GridPane.getColumnIndex(tile);
                        moveTile(row, col);
                    });
                }

                tile.setPrefSize(tileSize, tileSize);
                board[r][c] = tile;
                levelGrid.add(tile, c, r);
            }
        }
    }

    private Button createNumberTile(int value) {
        Button btn = new Button(String.valueOf(value));
        btn.setUserData(value);
        btn.setStyle("-fx-font-size: 24px; -fx-font-weight: bold;");
        return btn;
    }

    private Button createImageTile(int r, int c) {
        double w = puzzleImage.getWidth() / size;
        double h = puzzleImage.getHeight() / size;

        ImageView iv = new ImageView(puzzleImage);
        iv.setViewport(new Rectangle2D(c * w, r * h, w, h));
        iv.setFitWidth(120);
        iv.setFitHeight(120);

        Button btn = new Button();
        btn.setGraphic(iv);
        btn.setText(null);
        btn.setUserData(r * size + c + 1);
        btn.setStyle("-fx-padding: 0;");
        return btn;
    }

    private void moveTile(int r, int c) {
        if (gameFinished) return;
        if (!isValidMove(r, c)) return;

        swap(r, c);

        moves++;
        moveLabel.setText("Moves: " + moves);
        AudioManager.playSlide();

        if (isSolved()) onWin();
    }

    private boolean isValidMove(int r, int c) {
        return Math.abs(r - emptyRow) + Math.abs(c - emptyCol) == 1;
    }

    private void shuffle() {
        for (int i = 0; i < size * size * 10; i++) {
            List<int[]> neighbors = getNeighbors();
            int[] pos = neighbors.get(random.nextInt(neighbors.size()));
            swap(pos[0], pos[1]);
        }
    }

    private void swap(int r, int c) {
        Button tile = board[r][c];
        Button empty = board[emptyRow][emptyCol];

        board[emptyRow][emptyCol] = tile;
        board[r][c] = empty;

        GridPane.setRowIndex(tile, emptyRow);
        GridPane.setColumnIndex(tile, emptyCol);
        GridPane.setRowIndex(empty, r);
        GridPane.setColumnIndex(empty, c);

        emptyRow = r;
        emptyCol = c;
    }

    private List<int[]> getNeighbors() {
        List<int[]> list = new ArrayList<>();
        int[][] dirs = {{1,0},{-1,0},{0,1},{0,-1}};
        for (int[] d : dirs) {
            int r = emptyRow + d[0];
            int c = emptyCol + d[1];
            if (r >= 0 && r < size && c >= 0 && c < size)
                list.add(new int[]{r, c});
        }
        return list;
    }

    private void startTimer() {
        if (timer != null) timer.stop();

        timer = new Timeline(new KeyFrame(Duration.seconds(1), e -> {
            seconds++;
            timeLabel.setText(
                    String.format("Time: %02d:%02d", seconds / 60, seconds % 60)
            );
        }));
        timer.setCycleCount(Timeline.INDEFINITE);
        timer.play();
    }

    private boolean isSolved() {
        int expected = 1;
        for (int r = 0; r < size; r++) {
            for (int c = 0; c < size; c++) {
                int data = (int) board[r][c].getUserData();
                if (r == size - 1 && c == size - 1)
                    return data == 0;
                if (data != expected++) return false;
            }
        }
        return true;
    }

    private void onWin() {
        gameFinished = true;
        timer.stop();

        AudioManager.stopBGM();
        AudioManager.playWin();

        ScoreResult.setResult(moves, seconds, calculateStars());
        SceneManager.switchScene("/sample/view/win_popup.fxml");
    }

    private int calculateStars() {
        int stars = 3;
        if (moves > size * size * 2) stars--;
        if (seconds > size * size * 5) stars--;
        return Math.max(stars, 1);
    }

    @FXML
    public void onShuffle() {
        moves = 0;
        seconds = 0;
        gameFinished = false;

        moveLabel.setText("Moves: 0");
        timeLabel.setText("Time: 00:00");

        shuffle();
        startTimer();
    }

    @FXML
    public void onExit() {
        if (timer != null) timer.stop();
        AudioManager.stopBGM();
        SceneManager.switchScene("/sample/view/main_menu.fxml");
    }
}
