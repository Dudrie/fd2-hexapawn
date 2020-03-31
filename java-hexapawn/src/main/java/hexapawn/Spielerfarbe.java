package hexapawn;

/**
 * Spielerfarbe
 */
public enum Spielerfarbe {
    ROT(1), BLAU(-1);

    public final int richtung;

    private Spielerfarbe(final int richtung) {
        this.richtung = richtung;
    }

}