package hexapawn;

import static hexapawn.Spielbrett.KACHEL_LAENGE;

import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Ellipse;

/**
 * Figur
 */
public class Figur extends StackPane {

    private final Spielerfarbe spielerfarbe;
    private final Ellipse figur;

    private Koordinaten koordinaten;
    private boolean ausgewaehlt;

    public Figur(final Koordinaten koordinaten, final Spielerfarbe spielerfarbe) {
        this.koordinaten = koordinaten;
        this.spielerfarbe = spielerfarbe;
        this.ausgewaehlt = false;
        this.figur = new Ellipse(getRadius(), getRadius());

        initStyle();
        initPosition();
    }

    public Spielerfarbe getSpielerfarbe() {
        return this.spielerfarbe;
    }

    public Koordinaten getKoordinaten() {
        return this.koordinaten;
    }

    public void setKoordinaten(final Koordinaten koordinaten) {
        this.koordinaten = koordinaten;
    }

    public boolean isAusgewaehlt() {
        return this.ausgewaehlt;
    }

    public void setAusgewaehlt(final boolean ausgewaehlt) {
        this.ausgewaehlt = ausgewaehlt;
        this.figur.setFill(getFigurfarbe());
    }

    private void initStyle() {
        this.figur.setFill(getFigurfarbe());

        this.figur.setStroke(Color.BLACK);
        this.figur.setStrokeWidth(4);

        getChildren().add(figur);
    }

    private void initPosition() {
        final Koordinaten pixel = getKoordinatenZuPixel();

        relocate(pixel.getX(), pixel.getY());
    }

    private Color getFigurfarbe() {
        assert this.spielerfarbe != null;

        if (this.ausgewaehlt) {
            return Color.ORANGE;
        }

        return this.spielerfarbe == Spielerfarbe.ROT ? Color.RED : Color.BLUE;
    }

    private Koordinaten getKoordinatenZuPixel() {
        assert this.koordinaten != null;

        final double radius = getRadius();
        final double x = this.koordinaten.getX() * KACHEL_LAENGE + radius / 2;
        final double y = this.koordinaten.getY() * KACHEL_LAENGE + radius / 2;

        return new Koordinaten((int) x, (int) y);
    }

    private double getRadius() {
        return KACHEL_LAENGE * 0.33;
    }
}