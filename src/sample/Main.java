package sample;

import javafx.application.Application;
import javafx.stage.Stage;
import sample.util.SceneManager;

public class Main extends Application {

    @Override
    public void start(Stage stage) {
        SceneManager.setStage(stage); // Set stage di SceneManager
        SceneManager.switchScene("/sample/view/main_menu.fxml");
        stage.setTitle("Puzzle Slider");
        stage.setWidth(800);   // Lebar desktop
        stage.setHeight(600);  // Tinggi desktop
        stage.setResizable(false);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
