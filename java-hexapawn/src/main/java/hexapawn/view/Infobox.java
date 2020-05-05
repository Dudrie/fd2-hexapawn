package hexapawn.view;

import hexapawn.model.Spielfeld;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class Infobox extends VBox {
    private final Spielfeld spielfeld;

    public Infobox(final Spielfeld spielfeld) {
        this.spielfeld = spielfeld;

        initAktuellerSpielerText();
    }

    private void initAktuellerSpielerText() {
        final HBox box = new HBox();
        final Label titel = new Label("Aktueller Spieler: ");
        final Label spielerLabel = new Label();

        spielerLabel.textProperty().bind(spielfeld.getAktuellerSpieler().asString());

        box.getChildren().addAll(titel, spielerLabel);
        this.getChildren().add(box);
    }
}