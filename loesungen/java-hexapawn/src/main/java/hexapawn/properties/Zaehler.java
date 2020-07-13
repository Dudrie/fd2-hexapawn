package hexapawn.properties;

import javafx.application.Application;
import javafx.beans.property.ReadOnlyIntegerWrapper;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

/**
 * JavaFX App
 */
public class Zaehler extends Application {
    private final ReadOnlyIntegerWrapper zaehlerstand = new ReadOnlyIntegerWrapper(0);

    @Override
    public void start(final Stage stage) {
        final Label zaehlerstandText = new Label();
        final Button erhoeheButton = new Button("+");
        final Button vermindereButton = new Button("-");

        zaehlerstandText.textProperty().bind(zaehlerstand.asString());
        erhoeheButton.setOnAction((e) -> {
            final int neuerWert = zaehlerstand.get() + 1;
            zaehlerstand.set(neuerWert);
        });
        vermindereButton.setOnAction((e) -> {
            final int neuerWert = zaehlerstand.get() - 1;
            zaehlerstand.set(neuerWert);
        });

        final HBox box = new HBox(vermindereButton, zaehlerstandText, erhoeheButton);
        zaehlerstandText.setPadding(new Insets(0, 8, 0, 8));
        box.setPadding(new Insets(16));

        final Scene scene = new Scene(box);

        stage.setTitle("Zähler mit Properties");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }

}