package hexapawn;

import hexapawn.model.Spielfeld;
import hexapawn.view.Infobox;
import hexapawn.view.Spielbrett;
import javafx.geometry.Insets;
import javafx.scene.layout.HBox;

public class Inhalt extends HBox {
    private final Spielfeld spielfeld = new Spielfeld();

    public Inhalt() {
        this.setSpacing(16);
        this.setPadding(new Insets(16));
        this.getChildren().addAll(new Spielbrett(spielfeld), new Infobox(spielfeld));
    }
}