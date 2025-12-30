package sample.util;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.net.URL;

public final class AudioManager {

    private static MediaPlayer bgmPlayer;
    private static String currentBgmPath;
    private static double bgmVolume = 0.5;
    private static double sfxVolume = 0.7;
    private static Media load(String path) {
        URL url = AudioManager.class.getResource(path);
        if (url == null)
            throw new RuntimeException("Audio not found: " + path);
        return new Media(url.toExternalForm());
    }

    public static void playMenuBGM() {
        playBGM("/audio/main_sound.mp3");
    }

    public static void playGameBGM() {
        playBGM("/audio/game_sound.mp3");
    }

    private static void playBGM(String path) {
        if (bgmPlayer != null && path.equals(currentBgmPath)) {
            return;
        }

        stopBGM();
        bgmPlayer = new MediaPlayer(load(path));
        currentBgmPath = path;
        bgmPlayer.setCycleCount(MediaPlayer.INDEFINITE);
        bgmPlayer.setVolume(bgmVolume);
        bgmPlayer.play();
    }

    public static void stopBGM() {
        if (bgmPlayer != null) {
            bgmPlayer.stop();
            bgmPlayer.dispose();
            bgmPlayer = null;
            currentBgmPath = null;
        }
    }

    public static void playClick() {
        playSFX("/audio/clik_sound.mp3");
    }

    public static void playSlide() {
        playSFX("/audio/move_sound.mp3");
    }

    public static void playWin() {
        playSFX("/audio/win_sound.mp3");
    }

    private static void playSFX(String path) {
        MediaPlayer sfx = new MediaPlayer(load(path));
        sfx.setVolume(sfxVolume);
        sfx.play();
        sfx.setOnEndOfMedia(sfx::dispose);
    }

}
