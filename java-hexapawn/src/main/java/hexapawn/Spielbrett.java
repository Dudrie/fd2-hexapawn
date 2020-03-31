package hexapawn;

import java.util.Collection;
import java.util.HashSet;
import java.util.Optional;

import javafx.scene.Group;
import javafx.scene.layout.Pane;

/**
 * Spielbrett
 */
public class Spielbrett extends Pane {

    public static final int KACHEL_LAENGE = 128;
    public static final int KACHELN_PRO_REIHE = 3;

    private final Group kachelGruppe;
    private final Group figuren;

    private final Collection<Kachel> kacheln;

    private Optional<Figur> ausgewaehlteFigur;

    public Spielbrett() {
        this.setPrefSize(KACHEL_LAENGE * KACHELN_PRO_REIHE, KACHEL_LAENGE * KACHELN_PRO_REIHE);

        this.kachelGruppe = new Group();
        this.kacheln = new HashSet<>();
        this.figuren = new Group();
        this.ausgewaehlteFigur = Optional.empty();

        initSpielfeld();

        getChildren().addAll(kachelGruppe, figuren);
    }

    private void initSpielfeld() {
        assert this.kachelGruppe != null;
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
                    if (kachel.hatFigur()) {
                        if (this.ausgewaehlteFigur.isEmpty()) {
                            waehleFigurAus(kachel.getFigur());
                        } else if (this.ausgewaehlteFigur.get().equals(kachel.getFigur())) {
                            waehleFigurAb();
                        } else {
                            bewegeFigurZuKachel(kachel);
                        }
                    } else if (this.ausgewaehlteFigur.isPresent()) {
                        bewegeFigurZuKachel(kachel);
                    }
                });

                this.kacheln.add(kachel);
                this.kachelGruppe.getChildren().add(kachel);
            }
        }
    }

    private void bewegeFigurZuKachel(final Kachel kachel) {
        if (this.ausgewaehlteFigur.isEmpty()) {
            return;
        }

        final Figur figur = this.ausgewaehlteFigur.get();
        final Kachel startKachel = getKachelBeiKoordinaten(figur.getKoordinaten());
        final Bewegung bewegung = new Bewegung(figur, startKachel, kachel);

        if (bewegung.istErlaubt()) {
            bewegung.ausfuehren();

            if (bewegung.getGeschlageneFigur().isPresent()) {
                this.figuren.getChildren().remove(bewegung.getGeschlageneFigur().get());
            }

            waehleFigurAb();
        }
        // TODO: Implement me.
    }

    private void stelleFigurAufKachel(final Kachel kachel, final Spielerfarbe farbe) {
        final Figur figur = new Figur(kachel.getKoordinaten(), farbe);

        kachel.setFigur(figur);
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

    private Kachel getKachelBeiKoordinaten(final Koordinaten koordinaten) {
        for (final var kachel : this.kacheln) {
            if (kachel.getKoordinaten().equals(koordinaten)) {
                return kachel;
            }
        }

        throw new RuntimeException("Es existiert an der gegebenen Position keine Kachel.");
    }
}