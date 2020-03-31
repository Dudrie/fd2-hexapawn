package hexapawn;

import static hexapawn.Spielbrett.KACHEL_LAENGE;

import java.util.Optional;

import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Region;
import javafx.scene.paint.Color;

/**
 * Kachel
 */
public class Kachel extends Region {
    private final Koordinaten koordinaten;
    private Optional<Figur> figur;

    public Kachel(final Koordinaten koordinaten) {
        this(koordinaten, null);
    }

    public Kachel(final Koordinaten koordinaten, final Figur figur) {
        this.koordinaten = koordinaten;
        this.figur = Optional.ofNullable(figur);

        final Koordinaten pixel = getKoordinatenZuPixel();

        setPrefSize(KACHEL_LAENGE, KACHEL_LAENGE);
        setBorder(new Border(
                new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));

        relocate(pixel.getX(), pixel.getY());
    }

    public Koordinaten getKoordinaten() {
        return this.koordinaten;
    }

    public boolean hatFigur() {
        return this.figur.isPresent();
    }

    public Figur getFigur() {
        return this.figur.get();
    }

    public void setFigur(final Figur figur) {
        this.figur = Optional.ofNullable(figur);
    }

    private Koordinaten getKoordinatenZuPixel() {
        return new Koordinaten(this.koordinaten.getX() * KACHEL_LAENGE, this.koordinaten.getY() * KACHEL_LAENGE);
    }
}