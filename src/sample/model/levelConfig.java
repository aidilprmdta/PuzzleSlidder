package sample.model;

public class levelConfig {

    private static int gridSize = 3;

    public static void setLevel(int level) {
        gridSize = level + 2; // level 1 → 3x3
    }

    public static int getGridSize() {
        return gridSize;
    }
}
