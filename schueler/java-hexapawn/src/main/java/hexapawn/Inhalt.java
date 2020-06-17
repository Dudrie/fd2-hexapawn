package hexapawn;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

public class Inhalt extends HBox {
    public Inhalt() {
        this.setSpacing(16);
        this.setPadding(new Insets(16));

        final Label placeholder = new Label("Hier kommt später das Spielfeld hin.");
        this.getChildren().add(placeholder);
    }
}