package sample;

import javafx.application.Application;
import javafx.stage.Stage;
import sample.util.sceneManager;

public class main extends Application {

    @Override
    public void start(Stage stage) {
        sceneManager.init(stage);
        sceneManager.switchScene("/sample/view/main_menu.fxml");
    }

    public static void main(String[] args) {
        launch(args);
    }
}
