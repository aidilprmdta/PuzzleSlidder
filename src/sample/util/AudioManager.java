package sample.util;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class AudioManager {

    private static MediaPlayer bgmPlayer;
    private static double musicVolume = 0.5;
    private static double sfxVolume = 0.5;

    // BGM
    public static void playBGM(String path) {
        if (bgmPlayer != null) bgmPlayer.stop();
        var url = AudioManager.class.getResource(path);
        if (url == null) { System.err.println("BGM not found: " + path); return; }
        bgmPlayer = new MediaPlayer(new Media(url.toString()));
        bgmPlayer.setVolume(musicVolume);
        bgmPlayer.setCycleCount(MediaPlayer.INDEFINITE);
        bgmPlayer.play();
    }

    public static void stopBGM() {
        if (bgmPlayer != null) bgmPlayer.stop();
    }

    // SFX
    public static void playSFX(String path) {
        var url = AudioManager.class.getResource(path);
        if (url == null) { System.err.println("SFX not found: " + path); return; }
        MediaPlayer sfx = new MediaPlayer(new Media(url.toString()));
        sfx.setVolume(sfxVolume);
        sfx.play();
    }

    public static double getMusicVolume() { return musicVolume; }
    public static void setMusicVolume(double vol) {
        musicVolume = vol;
        if (bgmPlayer != null) bgmPlayer.setVolume(vol);
    }

    public static double getSfxVolume() { return sfxVolume; }
    public static void setSfxVolume(double vol) { sfxVolume = vol; }
}
