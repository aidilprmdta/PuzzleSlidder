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

    private Timeline timer;
    private int seconds = 0;

    private final Random random = new Random();

    @FXML
    public void initialize() {
        size = levelConfig.getGridSize();
        board = new Button[size][size];
        emptyRow = size - 1;
        emptyCol = size - 1;

        createBoard();
        shuffle();
        startTimer();
    }

    // ================= BOARD =================

    private void createBoard() {
        int value = 1;

        for (int r = 0; r < size; r++) {
            for (int c = 0; c < size; c++) {

                Button tile;

                if (r == size - 1 && c == size - 1) {
                    tile = new Button("");
                    tile.setDisable(true);
                    tile.setVisible(false);
                    emptyRow = r;
                    emptyCol = c;
                } else {
                    tile = new Button(String.valueOf(value++));
                    tile.setOnAction(e -> moveTile(
                            GridPane.getRowIndex(tile),
                            GridPane.getColumnIndex(tile)
                    ));
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

                if (r == size - 1 && c == size - 1) {
                    return tile != null && tile.getText().isEmpty();
                }

                if (tile == null || tile.getText().isEmpty()) return false;

                int value = Integer.parseInt(tile.getText());
                if (value != expected++) return false;
            }
        }
        return true;
    }

    private void onWin() {
        timer.stop();

        int stars = calculateStars();

        scoreResult.setResult(moves, seconds, stars);

        sceneManager.switchScene("view/win_popup.fxml", "You Win!");

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
        sceneManager.switchScene("view/main_menu.fxml", "Puzzle Slider");
    }
}