package hexapawn.view;

import hexapawn.model.Spielfeld;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class Infobox extends VBox {
    private final Spielfeld spielfeld;
    private final Label gewinnerLabel;
    private final HBox aktuellerSpielerBox;

    public Infobox(final Spielfeld spielfeld) {
        this.gewinnerLabel = new Label();
        this.aktuellerSpielerBox = new HBox();
        this.spielfeld = spielfeld;

        initGewinnerLabel();
        initAktuellerSpielerText();

        final Button neustartButton = new Button("Spiel neustarten");
        neustartButton.setOnAction(e -> {
            this.spielfeld.starteSpielNeu();
        });
        this.getChildren().add(neustartButton);
    }

    private void initGewinnerLabel() {
        this.gewinnerLabel.setText("");
        this.gewinnerLabel.setManaged(false);
        this.getChildren().add(gewinnerLabel);

        spielfeld.getGewinnerProperty().addListener((obs, alt, neu) -> {
            if (neu.isPresent()) {
                this.gewinnerLabel.setText("Gewinner: " + neu.get().toString());
                this.gewinnerLabel.setManaged(true);
                this.aktuellerSpielerBox.setVisible(false);
            } else {
                this.gewinnerLabel.setText("");
                this.gewinnerLabel.setManaged(false);
                this.aktuellerSpielerBox.setVisible(true);
            }
        });
    }

    private void initAktuellerSpielerText() {
        final Label titel = new Label("Aktueller Spieler: ");
        final Label spielerLabel = new Label();

        spielerLabel.textProperty().bind(spielfeld.getAktuellerSpieler().asString());

        aktuellerSpielerBox.getChildren().addAll(titel, spielerLabel);
        this.getChildren().add(aktuellerSpielerBox);
    }
}