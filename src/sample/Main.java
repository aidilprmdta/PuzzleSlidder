package sample;

import javafx.application.Application;
import javafx.stage.Stage;
import sample.util.AudioManager;
import sample.util.SceneManager;

public class Main extends Application {

    @Override
    public void start(Stage stage) {
        // Set stage di SceneManager
        SceneManager.setStage(stage);
        stage.setTitle("Puzzle Slider");
        AudioManager.playMenuBGM();
        SceneManager.switchScene("/sample/view/main_menu.fxml");

        // Sesuaikan ukuran dan tampilkan
        stage.setWidth(800);   // ukuran desktop lebih besar
        stage.setHeight(700);
        stage.setResizable(false);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
