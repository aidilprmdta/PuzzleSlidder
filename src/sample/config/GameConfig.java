package sample.config;

public final class GameConfig {

    private static PuzzleMode mode = PuzzleMode.NUMBER;

    public static PuzzleMode getMode() {
        return mode;
    }

    private GameConfig() {
        // prevent instantiation
    }

    public static void setMode(PuzzleMode m) {
        if (m == null) {
            throw new IllegalArgumentException("PuzzleMode tidak boleh null");
        }
        mode = m;
    }

    public static void reset() {
        mode = PuzzleMode.NUMBER;
    }
}
