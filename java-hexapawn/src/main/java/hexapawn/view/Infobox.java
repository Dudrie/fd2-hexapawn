package hexapawn.view;

import hexapawn.model.Spielfeld;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class Infobox extends VBox {
    private final Spielfeld spielfeld;
    private final Label gewinnerLabel;

    public Infobox(final Spielfeld spielfeld) {
        this.gewinnerLabel = new Label();
        this.spielfeld = spielfeld;

        initGewinnerLabel();
        initAktuellerSpielerText();
    }

    private void initGewinnerLabel() {
        this.gewinnerLabel.setText("");
        this.getChildren().add(gewinnerLabel);

        spielfeld.getGewinnerProperty().addListener((obs, alt, neu) -> {
            if (neu.isPresent()) {
                this.gewinnerLabel.setText("Gewinner: " + neu.get().toString());
            } else {
                this.gewinnerLabel.setText("");
            }
        });
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