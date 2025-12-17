package sample.config;

public class gameConfig {

    private static puzzleMode mode = puzzleMode.NUMBER;

    public static void setMode(puzzleMode m) {
        mode = m;
    }

    public static puzzleMode getMode() {
        return mode;
    }
}
