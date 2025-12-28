package sample.util;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.net.URL;

public final class AudioManager {

    private static MediaPlayer bgmPlayer;
    private static String currentBgmPath;
    private static double bgmVolume = 0.5;
    private static double sfxVolume = 0.7;
    private AudioManager() {}
    private static Media load(String path) {
        URL url = AudioManager.class.getResource(path);
        if (url == null)
            throw new RuntimeException("Audio not found: " + path);
        return new Media(url.toExternalForm());
    }

    public static void playMenuBGM() {
        playBGM("/audio/bgm.wav");
    }

    public static void playGameBGM() {
        playBGM("/audio/game.mp3");
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

    public static void setBgmVolume(double volume) {
        bgmVolume = clamp(volume);
        if (bgmPlayer != null)
            bgmPlayer.setVolume(bgmVolume);
    }

    public static double getBgmVolume() {
        return bgmVolume;
    }

    public static void playClick() {
        playSFX("/audio/mouse-klik.mp3");
    }

    public static void playSlide() {
        playSFX("/audio/whoosh-wind.mp3");
    }

    public static void playWin() {
        playSFX("/audio/bereal.mp3");
    }

    private static void playSFX(String path) {
        MediaPlayer sfx = new MediaPlayer(load(path));
        sfx.setVolume(sfxVolume);
        sfx.play();
        sfx.setOnEndOfMedia(sfx::dispose);
    }

    public static void setSfxVolume(double volume) {
        sfxVolume = clamp(volume);
    }

    public static double getSfxVolume() {
        return sfxVolume;
    }

    private static double clamp(double v) {
        return Math.max(0, Math.min(1, v));
    }
}
