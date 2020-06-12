package hexapawn.model;

import java.util.List;
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
    private final ReadOnlyObjectWrapper<Optional<Spielerfarbe>> gewinner = new ReadOnlyObjectWrapper<>(
            Optional.empty());

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

    public ReadOnlyObjectProperty<Optional<Spielerfarbe>> getGewinnerProperty() {
        return this.gewinner.getReadOnlyProperty();
    }

    public void waehleFigurAus(final Figur figur) {
        if (gewinner.get().isPresent()) {
            return;
        }

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
        if (gewinner.get().isPresent()) {
            return;
        }

        final Optional<Figur> ausgewaehlteFigur = this.ausgewaehlteFigur.get();

        if (ausgewaehlteFigur.isEmpty()) {
            return;
        }

        final Bewegung bewegung = generiereBewegung(kachel);

        if (bewegung.isErlaubt()) {
            bewegung.ausfuehren();
            pruefeGewinner(bewegung.getZielKachel());
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

    private void pruefeGewinner(final Kachel zielKachel) {
        final Spielerfarbe aktuellerSpieler = this.aktuellerSpieler.get();
        final int letzteReihe = 1 + aktuellerSpieler.getRichtung();
        final List<Kachel> kacheln = this.kacheln.get();

        // Der aktuelle Spieler hat die für ihn letzte Reihe erreicht und gewinnt.

        if (zielKachel.getKoordinaten().getY() == letzteReihe) {
            gewinner.set(Optional.of(aktuellerSpieler));
            return;
        }

        // Der aktuelle Spieler ist der einzige mit Figuren auf dem Feld.
        if (hatSpielfeldNurFigurenEinerFarbe()) {
            gewinner.set(Optional.of(aktuellerSpieler));
            return;
        }

        // Der Gegner kann keinen Zug mehr durchführen.
        // TODO: IMPLEMENT ME
    }

    private boolean hatSpielfeldNurFigurenEinerFarbe() {
        final List<Kachel> kacheln = this.kacheln.get();
        final Spielerfarbe aktuellerSpieler = this.aktuellerSpieler.get();

        for (final Kachel kachel : kacheln) {
            if (kachel.hatFigur() && !kachel.getFigur().get().getSpielerfarbe().equals(aktuellerSpieler)) {
                return false;
            }
        }

        return true;
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