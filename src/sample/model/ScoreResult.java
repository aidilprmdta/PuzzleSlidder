package sample.model;

public class ScoreResult {

    private static int moves;
    private static int time;
    private static int stars;

    public static void setResult(int m, int t, int s) {
        moves = m;
        time = t;
        stars = s;
    }

    public static int getMoves() { return moves; }
    public static int getTime() { return time; }
    public static int getStars() { return stars; }
}
