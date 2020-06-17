package hexapawn.model;

import javafx.beans.property.ReadOnlyBooleanProperty;
import javafx.beans.property.ReadOnlyBooleanWrapper;
import javafx.beans.property.ReadOnlyObjectWrapper;

public class Figur {
    private final Spielerfarbe spielerfarbe;

    private final ReadOnlyBooleanWrapper ausgewaehlt;
    private final ReadOnlyObjectWrapper<Kachel> kachel;

    public Figur(final Kachel kachel, final Spielerfarbe spielerfarbe) {
        this.spielerfarbe = spielerfarbe;
        this.kachel = new ReadOnlyObjectWrapper<>(kachel);
        this.ausgewaehlt = new ReadOnlyBooleanWrapper(false);
    }

    public Spielerfarbe getSpielerfarbe() {
        return this.spielerfarbe;
    }

    public Koordinaten getKoordinaten() {
        final Kachel kachel = this.kachel.get();
        return kachel.getKoordinaten();
    }

    public Kachel getKachel() {
        return this.kachel.get();
    }

    public void setKachel(final Kachel kachel) {
        this.kachel.get().setFigur(null);
        this.kachel.set(kachel);
        kachel.setFigur(this);
    }

    public ReadOnlyBooleanProperty isAusgewaehlt() {
        return this.ausgewaehlt.getReadOnlyProperty();
    }

    public void setAusgewaehlt(final boolean ausgewaehlt) {
        this.ausgewaehlt.set(ausgewaehlt);
    }
}