package sample.util;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class SceneManager {

    private static Stage stage;

    public static void setStage(Stage s) {
        stage = s;
    }

    public static void switchScene(String fxml) {
        if (stage == null) {
            throw new IllegalStateException("Stage belum diset di SceneManager");
        }

        try {
            FXMLLoader loader =
                    new FXMLLoader(SceneManager.class.getResource(fxml));
            Parent root = loader.load();
            stage.setScene(new Scene(root));
            stage.sizeToScene();
            stage.centerOnScreen();
        } catch (Exception e) {
            throw new RuntimeException("Gagal load scene: " + fxml, e);
        }
    }
}
