package sample.util;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class audioManager {

    private static MediaPlayer bgmPlayer;

    private static double musicVolume = 0.5;
    private static double sfxVolume = 0.5;

    // ================= BGM =================

    public static void playBGM(String path) {
        if (bgmPlayer != null) {
            bgmPlayer.stop();
        }

        var url = audioManager.class.getResource(path);
        if (url == null) {
            System.err.println("BGM not found: " + path);
            return;
        }

        Media media = new Media(url.toString());
        bgmPlayer = new MediaPlayer(media);
        bgmPlayer.setVolume(musicVolume);
        bgmPlayer.setCycleCount(MediaPlayer.INDEFINITE);
        bgmPlayer.play();
    }

    public static void stopBGM() {
        if (bgmPlayer != null) {
            bgmPlayer.stop();
        }
    }

    // ================= SFX =================

    public static void playSFX(String path) {
        var url = audioManager.class.getResource(path);
        if (url == null) {
            System.err.println("SFX not found: " + path);
            return;
        }

        Media media = new Media(url.toString());
        MediaPlayer sfxPlayer = new MediaPlayer(media);
        sfxPlayer.setVolume(sfxVolume);
        sfxPlayer.play();
    }

    // ================= VOLUME =================

    public static double getMusicVolume() {
        return musicVolume;
    }

    public static void setMusicVolume(double volume) {
        musicVolume = volume;
        if (bgmPlayer != null) {
            bgmPlayer.setVolume(volume);
        }
    }

    public static double getSfxVolume() {
        return sfxVolume;
    }

    public static void setSfxVolume(double volume) {
        sfxVolume = volume;
    }
}
