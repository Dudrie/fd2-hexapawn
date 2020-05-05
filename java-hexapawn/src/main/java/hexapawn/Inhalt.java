package hexapawn;

import javafx.scene.layout.HBox;

public class Inhalt extends HBox {
    public Inhalt() {
        this.setSpacing(16);
        this.getChildren().addAll(new Spielbrett(), new Infobox());
    }
}