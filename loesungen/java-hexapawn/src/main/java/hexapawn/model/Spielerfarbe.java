package hexapawn.model;

import javafx.scene.paint.Color;

/**
 * Spielerfarbe
 */
public enum Spielerfarbe {
    ROT(1, Color.RED), BLAU(-1, Color.BLUE);

    private final int richtung;
    private final Color farbe;

    private Spielerfarbe(final int richtung, final Color farbe) {
        this.richtung = richtung;
        this.farbe = farbe;
    }

    public int getRichtung() {
        return this.richtung;
    }

    public Color getFarbe() {
        return this.farbe;
    }

    @Override
    public String toString() {
        switch (this) {
            case BLAU:
                return "Blau";
            case ROT:
                return "Rot";
            default:
                return "NICHT_GEFUNDEN";
        }
    }
}