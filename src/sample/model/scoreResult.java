package sample.model;

public class scoreResult {

    private static int moves;
    private static int seconds;
    private static int stars;

    public static void setResult(int m, int s, int star) {
        moves = m;
        seconds = s;
        stars = star;
    }

    public static int getMoves() {
        return moves;
    }

    public static int getSeconds() {
        return seconds;
    }

    public static int getStars() {
        return stars;
    }
}
