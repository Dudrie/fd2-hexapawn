package hexapawn;

/**
 * Koordinaten
 */
public class Koordinaten {

    private final int x;
    private final int y;

    public Koordinaten(final int x, final int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }

    @Override
    public boolean equals(final Object obj) {
        if (!(obj instanceof Koordinaten)) {
            return false;
        }

        final Koordinaten koords = (Koordinaten) obj;

        return koords.getX() == this.x && koords.getY() == this.y;
    }
}