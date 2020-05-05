package hexapawn.model;

import java.util.Optional;

import javafx.beans.property.ReadOnlyListProperty;
import javafx.beans.property.ReadOnlyListWrapper;
import javafx.beans.property.ReadOnlyObjectProperty;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.FXCollections;

public class Spielfeld {
    public static final int KACHELN_PRO_REIHE = 3;

    private final ReadOnlyListWrapper<Kachel> kacheln = new ReadOnlyListWrapper<>(FXCollections.observableArrayList());
    private final ReadOnlyObjectWrapper<Optional<Figur>> ausgewaehlteFigur = new ReadOnlyObjectWrapper<>(
            Optional.empty());
    private final ReadOnlyObjectWrapper<Spielerfarbe> aktuellerSpieler = new ReadOnlyObjectWrapper<>(Spielerfarbe.BLAU);

    public Spielfeld() {
        initSpielfeld();
    }

    public ReadOnlyListProperty<Kachel> getKacheln() {
        return this.kacheln.getReadOnlyProperty();
    }

    public ReadOnlyObjectProperty<Optional<Figur>> getAusgewaehlteFigur() {
        return this.ausgewaehlteFigur.getReadOnlyProperty();
    }

    public ReadOnlyObjectProperty<Spielerfarbe> getAktuellerSpieler() {
        return this.aktuellerSpieler.getReadOnlyProperty();
    }

    public void waehleFigurAus(final Figur figur) {
        if (!figur.getSpielerfarbe().equals(this.aktuellerSpieler.get())) {
            return;
        }

        final Optional<Figur> bisherAusgewaehlt = this.ausgewaehlteFigur.get();

        if (bisherAusgewaehlt.isPresent()) {
            bisherAusgewaehlt.get().setAusgewaehlt(false);
        }

        figur.setAusgewaehlt(true);
        this.ausgewaehlteFigur.set(Optional.of(figur));
    }

    public void waehleFigurAb() {
        assert this.ausgewaehlteFigur.get().isPresent();

        this.ausgewaehlteFigur.get().get().setAusgewaehlt(false);
        this.ausgewaehlteFigur.set(Optional.empty());
    }

    public boolean isFigurAusgewaehlt(final Figur figur) {
        final Optional<Figur> ausgewaehlt = this.ausgewaehlteFigur.get();

        if (ausgewaehlt.isEmpty()) {
            return false;
        }

        return ausgewaehlt.get().equals(figur);
    }

    public void bewegeAuswahlZuZiel(final Kachel kachel) {
        final Optional<Figur> ausgewaehlteFigur = this.ausgewaehlteFigur.get();

        if (ausgewaehlteFigur.isEmpty()) {
            return;
        }

        final Bewegung bewegung = generiereBewegung(kachel);

        if (bewegung.isErlaubt()) {
            bewegung.ausfuehren();
            wechsleSpieler();
        }
    }

    public boolean isEineFigurAusgewaehlt() {
        return this.ausgewaehlteFigur.get().isPresent();
    }

    private void wechsleSpieler() {
        final Spielerfarbe aktuellerSpieler = this.aktuellerSpieler.get();

        if (aktuellerSpieler.equals(Spielerfarbe.BLAU)) {
            this.aktuellerSpieler.set(Spielerfarbe.ROT);
        } else {
            this.aktuellerSpieler.set(Spielerfarbe.BLAU);
        }
    }

    private Bewegung generiereBewegung(final Kachel zielKachel) {
        assert this.isEineFigurAusgewaehlt();

        final Figur figur = this.getAusgewaehlteFigur().get().get();

        return new Bewegung(this, figur, zielKachel);
    }

    private void initSpielfeld() {
        this.kacheln.clear();
        this.ausgewaehlteFigur.set(Optional.empty());

        for (int x = 0; x < KACHELN_PRO_REIHE; x++) {
            for (int y = 0; y < KACHELN_PRO_REIHE; y++) {
                final Kachel kachel = new Kachel(new Koordinaten(x, y));

                if (y == 0) {
                    stelleFigurAufKachel(kachel, Spielerfarbe.ROT);
                } else if (y == 2) {
                    stelleFigurAufKachel(kachel, Spielerfarbe.BLAU);
                }

                this.kacheln.add(kachel);
            }
        }
    }

    private void stelleFigurAufKachel(final Kachel kachel, final Spielerfarbe farbe) {
        final Figur figur = new Figur(kachel, farbe);

        kachel.setFigur(figur);
    }
}