package hexapawn;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * JavaFX App
 */
public class App extends Application {

    @Override
    public void start(final Stage stage) {
        final Scene scene = new Scene(new Spielbrett());

        stage.setTitle("Java Hexapawn");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }

}