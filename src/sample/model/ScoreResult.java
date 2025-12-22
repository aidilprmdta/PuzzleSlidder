package sample.model;

public class ScoreResult {

    private static int moves;
    private static int time;
    private static int stars;

    private ScoreResult() {}

    public static void setResult(int m, int t, int s) {
        moves = m;
        time = t;
        stars = s;
    }

    public static void reset() {
        moves = 0;
        time = 0;
        stars = 0;
    }

    public static int getMoves() {
        return moves;
    }

    public static int getSeconds() {
        return time;
    }

    public static int getStars() {
        return stars;
    }
}
