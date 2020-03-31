package hexapawn;

import java.util.Optional;

import javafx.scene.Group;
import javafx.scene.layout.Pane;

/**
 * Spielbrett
 */
public class Spielbrett extends Pane {

    public static final int KACHEL_LAENGE = 128;
    public static final int KACHELN_PRO_REIHE = 3;

    private final Group kacheln;
    private final Group figuren;

    private Optional<Figur> ausgewaehlteFigur;

    public Spielbrett() {
        this.setPrefSize(KACHEL_LAENGE * KACHELN_PRO_REIHE, KACHEL_LAENGE * KACHELN_PRO_REIHE);

        this.kacheln = new Group();
        this.figuren = new Group();
        this.ausgewaehlteFigur = Optional.empty();

        initSpielfeld();

        getChildren().addAll(kacheln, figuren);
    }

    private void initSpielfeld() {
        assert this.kacheln != null;
        assert this.figuren != null;

        for (int x = 0; x < KACHELN_PRO_REIHE; x++) {
            for (int y = 0; y < KACHELN_PRO_REIHE; y++) {
                final Kachel kachel = new Kachel(new Koordinaten(x, y));

                if (y == 0) {
                    stelleFigurAufKachel(kachel, Spielerfarbe.ROT);
                } else if (y == 2) {
                    stelleFigurAufKachel(kachel, Spielerfarbe.BLAU);
                }

                kachel.setOnMouseClicked(e -> {
                    bewegeFigurZuKachel(kachel);
                });
                this.kacheln.getChildren().add(kachel);
            }
        }
    }

    private void bewegeFigurZuKachel(final Kachel kachel) {
        if (this.ausgewaehlteFigur.isEmpty()) {
            return;
        }

        // TODO: Implement me.
    }

    private void stelleFigurAufKachel(final Kachel kachel, final Spielerfarbe farbe) {
        final Figur figur = new Figur(kachel.getKoordinaten(), farbe);

        kachel.setFigur(figur);

        figur.setOnMouseClicked(e -> {
            if (this.ausgewaehlteFigur.isEmpty()) {
                waehleFigurAus(figur);
            } else if (this.ausgewaehlteFigur.get().equals(figur)) {
                waehleFigurAb();
            }
        });

        this.figuren.getChildren().add(figur);
    }

    private void waehleFigurAus(final Figur figur) {
        figur.setAusgewaehlt(true);
        this.ausgewaehlteFigur = Optional.of(figur);
    }

    private void waehleFigurAb() {
        if (!this.ausgewaehlteFigur.isPresent()) {
            return;
        }

        this.ausgewaehlteFigur.get().setAusgewaehlt(false);
        this.ausgewaehlteFigur = Optional.empty();
    }
}