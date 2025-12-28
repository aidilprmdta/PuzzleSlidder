package sample.model;

import sample.config.PuzzleMode;

public class LevelConfig {

    private static PuzzleMode mode = PuzzleMode.NUMBER;
    private static int gridSize = 4;
    private static int level = 1;
    private static String imagePath;

    public static void setMode(PuzzleMode m) {
        mode = m;
    }

    public static PuzzleMode getMode() {
        return mode;
    }

    public static int getGridSize() {
        return gridSize;
    }

    public static void setLevel(int l) {
        level = l;
    }

    public static String getImagePath() {
        return imagePath;
    }

    public static void configureImageMode(int grid, String path) {
        mode = PuzzleMode.IMAGE;
        gridSize = grid;
        imagePath = path;
    }

    public static void configureNumberMode(int grid) {
        mode = PuzzleMode.NUMBER;
        gridSize = grid;
        imagePath = null;
    }
}
