package sample.controller;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.util.Duration;
import sample.model.levelConfig;
import sample.util.audioManager;
import sample.util.sceneManager;
import sample.model.scoreResult;
import sample.config.puzzleMode;
import sample.util.imageRepository;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.geometry.Rectangle2D;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class gameController {

    @FXML
    private GridPane levelGrid;

    @FXML
    private Label moveLabel, timeLabel;

    private Button[][] board;
    private int size;
    private int emptyRow, emptyCol;
    private int moves = 0;
    private Image puzzleImage;

    private Timeline timer;
    private int seconds = 0;

    private final Random random = new Random();

    @FXML
    private void chooseNumberMode() {
        levelConfig.setMode(puzzleMode.NUMBER);
        levelConfig.setImagePath(null);
        sceneManager.switchScene("level_select.fxml");
    }

    @FXML
    private void chooseImageMode() {
        levelConfig.setMode(puzzleMode.IMAGE);
        sceneManager.switchScene("level_select.fxml");
    }

    @FXML
    private void startLevel(int size) {

        levelConfig.setGridSize(size);

        if (levelConfig.getMode() == puzzleMode.IMAGE) {
            String img = imageRepository.getImageByGrid(size);
            levelConfig.setImagePath(img);   // ⬅️ WAJIB
        }

        sceneManager.switchScene("game.fxml");
    }

    private void loadImagePuzzle(String imagePath, int gridSize) {

        if (imagePath == null) {
            throw new RuntimeException("IMAGE MODE tapi imagePath NULL");
        }

        var url = getClass().getResource(imagePath);

        if (url == null) {
            throw new RuntimeException("Image tidak ditemukan: " + imagePath);
        }

        puzzleImage = new Image(url.toExternalForm());
        this.size = gridSize;
    }

    @FXML
    public void initialize() {
        size = levelConfig.getGridSize();

        if (levelConfig.getMode() == puzzleMode.IMAGE) {
            loadImagePuzzle(levelConfig.getImagePath(), size);
        }

        board = new Button[size][size];
        emptyRow = size - 1;
        emptyCol = size - 1;

        createBoard();
        shuffle();
        startTimer();
    }

    public void onStart() {
        sceneManager.switchScene("/sample/view/level_select.fxml");
    }

    public void onImageMode() {
        levelConfig.setMode(puzzleMode.IMAGE);
        sceneManager.switchScene("/sample/view/level_select.fxml");
    }

    private Button createNumberTile(int r, int c, int value) {
        Button btn = new Button(String.valueOf(value));
        btn.setUserData(value);
        return btn;
    }

    private Button createImageTile(int r, int c) {
        double w = puzzleImage.getWidth() / size;
        double h = puzzleImage.getHeight() / size;

        ImageView iv = new ImageView(puzzleImage);
        iv.setViewport(new Rectangle2D(c * w, r * h, w, h));
        iv.setFitWidth(70);
        iv.setFitHeight(70);

        Button btn = new Button();
        btn.setGraphic(iv);
        btn.setUserData(r * size + c + 1);
        btn.setStyle("-fx-padding: 0;");

        return btn;
    }

    // ================= BOARD =================

    private void createBoard() {
        int value = 1;

        for (int r = 0; r < size; r++) {
            for (int c = 0; c < size; c++) {

                Button tile;

                // EMPTY TILE
                if (r == size - 1 && c == size - 1) {
                    tile = new Button();
                    tile.setUserData(0);
                    tile.setDisable(true);
                    tile.setVisible(false);
                    emptyRow = r;
                    emptyCol = c;
                }
                // NORMAL TILE
                else {
                    if (levelConfig.getMode() == puzzleMode.NUMBER) {
                        tile = createNumberTile(r, c, value);
                    } else {
                        tile = createImageTile(r, c);
                    }

                    tile.setOnAction(e -> {
                        Integer row = GridPane.getRowIndex(tile);
                        Integer col = GridPane.getColumnIndex(tile);

                        // fallback kalau null
                        int rr = row == null ? 0 : row;
                        int cc = col == null ? 0 : col;

                        moveTile(rr, cc);
                    });
                    value++;
                }

                tile.setPrefSize(70, 70);
                board[r][c] = tile;
                levelGrid.add(tile, c, r);
            }
        }
    }

    // ================= MOVE =================

    private void moveTile(int r, int c) {
        if (!isValidMove(r, c)) return;

        Button clicked = board[r][c];
        Button empty = board[emptyRow][emptyCol];

        board[emptyRow][emptyCol] = clicked;
        board[r][c] = empty;

        GridPane.setRowIndex(clicked, emptyRow);
        GridPane.setColumnIndex(clicked, emptyCol);

        GridPane.setRowIndex(empty, r);
        GridPane.setColumnIndex(empty, c);

        emptyRow = r;
        emptyCol = c;

        moves++;
        moveLabel.setText("Moves: " + moves);

        if (isSolved()) onWin();
    }

    private boolean isValidMove(int r, int c) {
        return Math.abs(r - emptyRow) + Math.abs(c - emptyCol) == 1;
    }
    // ================= SHUFFLE =================

    public void onShuffle() {
        shuffle();
        moves = 0;
        seconds = 0;
        moveLabel.setText("Moves: 0");
        timeLabel.setText("Time: 00:00");
    }

    private void shuffle() {
        for (int i = 0; i < size * size * 10; i++) {

            List<int[]> neighbors = getNeighbors();
            int[] pos = neighbors.get(random.nextInt(neighbors.size()));

            int r = pos[0];
            int c = pos[1];

            Button tile = board[r][c];
            Button empty = board[emptyRow][emptyCol];

            // swap di array
            board[emptyRow][emptyCol] = tile;
            board[r][c] = empty;

            // swap di UI
            GridPane.setRowIndex(tile, emptyRow);
            GridPane.setColumnIndex(tile, emptyCol);

            GridPane.setRowIndex(empty, r);
            GridPane.setColumnIndex(empty, c);

            emptyRow = r;
            emptyCol = c;
        }
    }

    private List<int[]> getNeighbors() {
        List<int[]> list = new ArrayList<>();
        int[][] dirs = {{1,0},{-1,0},{0,1},{0,-1}};

        for (int[] d : dirs) {
            int r = emptyRow + d[0];
            int c = emptyCol + d[1];

            if (r >= 0 && r < size && c >= 0 && c < size) {
                list.add(new int[]{r, c});
            }
        }
        return list;
    }

    // ================= TIMER =================

    private void startTimer() {
        timer = new Timeline(new KeyFrame(Duration.seconds(1), e -> {
            seconds++;
            int min = seconds / 60;
            int sec = seconds % 60;
            timeLabel.setText(String.format("Time: %02d:%02d", min, sec));
        }));
        timer.setCycleCount(Timeline.INDEFINITE);
        timer.play();
    }

    // ================= WIN =================

    private boolean isSolved() {
        int expected = 1;

        for (int r = 0; r < size; r++) {
            for (int c = 0; c < size; c++) {

                Button tile = board[r][c];
                int data = (int) tile.getUserData();

                if (r == size - 1 && c == size - 1) {
                    return data == 0;
                }

                if (data != expected++) return false;
            }
        }
        return true;
    }

    private void onWin() {
        timer.stop();

        int stars = calculateStars();

        scoreResult.setResult(moves, seconds, stars);

        sceneManager.switchScene("/sample/view/win_popup.fxml");

        audioManager.playSFX("assets/assets.resources.audio.style.audio/win.wav");

    }

    private int calculateStars() {
        int maxMoves;
        int maxTime;

        // Aturan dasar per level (simple & rasional)
        if (size == 4) {          // Level 1
            maxMoves = 50;
            maxTime = 120;
        } else if (size == 6) {   // Level 2
            maxMoves = 120;
            maxTime = 300;
        } else {                  // Level 3+
            maxMoves = 250;
            maxTime = 600;
        }

        int stars = 3;

        if (moves > maxMoves) stars--;
        if (seconds > maxTime) stars--;

        return Math.max(stars, 1);
    }

    // ================= EXIT =================

    public void onExit() {
        timer.stop();
        sceneManager.switchScene("/sample/view/main_menu.fxml");
    }
}