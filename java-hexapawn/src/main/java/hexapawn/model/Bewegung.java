package hexapawn.model;

public class Bewegung {
    private final Spielfeld spielfeld;
    private final Figur figur;
    private final Kachel zielKachel;

    private boolean erlaubt;

    public Bewegung(final Spielfeld spielfeld, final Figur figur, final Kachel zielKachel) {
        this.spielfeld = spielfeld;
        this.figur = figur;
        this.zielKachel = zielKachel;

        this.erlaubt = this.isErlaubteBewegung();
    }

    public void ausfuehren() {
        if (!this.erlaubt) {
            return;
        }

        figur.setKachel(zielKachel);
        this.spielfeld.waehleFigurAb();
    }

    public boolean isErlaubt() {
        return this.erlaubt;
    }

    private boolean isErlaubteBewegung() {
        final Koordinaten figurKoordinaten = figur.getKoordinaten();

        final int dx = zielKachel.getKoordinaten().getX() - figurKoordinaten.getX();
        final int dy = zielKachel.getKoordinaten().getY() - figurKoordinaten.getY();

        if (hatZielkachelFigurAndererFarbe()) {
            return Math.abs(dx) == 1 && dy == figur.getSpielerfarbe().getRichtung();
        } else if (!zielKachel.hatFigur()) {
            return dx == 0 && dy == figur.getSpielerfarbe().getRichtung();
        } else {
            return false;
        }
    }

    private boolean hatZielkachelFigurAndererFarbe() {
        if (!zielKachel.hatFigur()) {
            return false;
        }

        final Figur zielFigur = zielKachel.getFigur().get();

        return !zielFigur.getSpielerfarbe().equals(figur.getSpielerfarbe());
    }
}