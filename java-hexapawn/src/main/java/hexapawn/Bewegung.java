package hexapawn;

import java.util.Optional;

/**
 * Bewegung
 */
public class Bewegung {
    private final Figur figur;
    private final Kachel startKachel;
    private final Kachel endKachel;

    private boolean erlaubt;
    private Optional<Figur> geschlageneFigur;

    public Bewegung(final Figur figur, final Kachel startKachel, final Kachel endKachel) {
        this.figur = figur;
        this.startKachel = startKachel;
        this.endKachel = endKachel;

        this.geschlageneFigur = endKachel.hatFigur() ? Optional.of(endKachel.getFigur()) : Optional.empty();
        this.erlaubt = istErlaubteBewegung();
    }

    public void ausfuehren() {
        if (!this.erlaubt) {
            return;
        }

        this.figur.setKoordinaten(endKachel.getKoordinaten());
        this.startKachel.setFigur(null);
        this.endKachel.setFigur(figur);
    }

    public Figur getFigur() {
        return this.figur;
    }

    public Kachel getEndKachel() {
        return this.endKachel;
    }

    public boolean istErlaubt() {
        return this.erlaubt;
    }

    public Optional<Figur> getGeschlageneFigur() {
        return this.geschlageneFigur;
    }

    private boolean istErlaubteBewegung() {
        final int dx = this.endKachel.getKoordinaten().getX() - figur.getKoordinaten().getX();
        final int dy = this.endKachel.getKoordinaten().getY() - figur.getKoordinaten().getY();

        if (this.endKachel.hatFigur()) {
            return Math.abs(dx) == 1 && dy == figur.getSpielerfarbe().richtung;
        } else {
            return dx == 0 && dy == figur.getSpielerfarbe().richtung;
        }
    }

}