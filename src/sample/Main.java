package sample;

import javafx.application.Application;
import javafx.stage.Stage;
import sample.util.AudioManager;
import sample.util.SceneManager;

public class Main extends Application {

    @Override
    public void start(Stage stage) {
        SceneManager.setStage(stage);
        stage.setTitle("Puzzle Slider");
        AudioManager.playMenuBGM();
        SceneManager.switchScene("/sample/view/MainMenu.fxml");

        stage.setWidth(900);
        stage.setHeight(500);
        stage.setResizable(false);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
