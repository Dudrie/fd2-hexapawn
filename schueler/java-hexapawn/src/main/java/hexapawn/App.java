package hexapawn;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Hexapawn App
 */
public class App extends Application {

    @Override
    public void start(final Stage stage) {
        final Scene scene = new Scene(new Inhalt());

        stage.setTitle("Java Hexapawn");
        stage.setScene(scene);

        stage.setMinWidth(400);
        stage.setMinHeight(150);

        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }

}