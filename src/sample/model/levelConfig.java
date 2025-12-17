package sample.model;

import sample.config.puzzleMode;

public class levelConfig {

    private static puzzleMode mode = puzzleMode.NUMBER;
    private static int gridSize = 4;
    private static int level = 1;
    private static String imagePath;

    // MODE
    public static void setMode(puzzleMode m) {
        mode = m;
    }

    public static puzzleMode getMode() {
        return mode;
    }

    // GRID
    public static void setGridSize(int size) {
        gridSize = size;
    }

    public static int getGridSize() {
        return gridSize;
    }

    // LEVEL
    public static void setLevel(int lvl) {
        level = lvl;
    }

    public static int getLevel() {
        return level;
    }

    // IMAGE
    public static void setImagePath(String path) {
        imagePath = path;
    }

    public static String getImagePath() {
        return imagePath;
    }
}

