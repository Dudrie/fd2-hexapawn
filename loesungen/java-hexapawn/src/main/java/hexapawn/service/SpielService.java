package hexapawn.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import hexapawn.model.Figur;
import hexapawn.model.Kachel;
import hexapawn.model.Koordinaten;
import hexapawn.model.Spielerfarbe;
import javafx.beans.property.ReadOnlyListProperty;
import javafx.beans.property.ReadOnlyListWrapper;
import javafx.beans.property.ReadOnlyObjectProperty;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.FXCollections;

public class SpielService {
    public static final int KACHELN_PRO_REIHE = 3;

    private final ReadOnlyListWrapper<Kachel> kacheln = new ReadOnlyListWrapper<>(FXCollections.observableArrayList());
    private final ReadOnlyObjectWrapper<Optional<Figur>> ausgewaehlteFigur = new ReadOnlyObjectWrapper<>(
            Optional.empty());
    private final ReadOnlyObjectWrapper<Spielerfarbe> aktuellerSpieler = new ReadOnlyObjectWrapper<>(Spielerfarbe.BLAU);
    private final ReadOnlyObjectWrapper<Optional<Spielerfarbe>> gewinner = new ReadOnlyObjectWrapper<>(
            Optional.empty());

    public SpielService() {
        initSpielfeld();
    }

    public void starteSpielNeu() {
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

        final boolean isErlaubteBewegung = isValideBewegung(ausgewaehlteFigur.get(), kachel);

        if (isErlaubteBewegung) {
            bewegeFigurZuKachel(ausgewaehlteFigur.get(), kachel);
            pruefeGewinner(kachel);
            wechsleSpieler();
        }
    }

    public boolean isEineFigurAusgewaehlt() {
        return this.ausgewaehlteFigur.get().isPresent();
    }

    private void wechsleSpieler() {
        this.aktuellerSpieler.set(getNaechsterSpieler());
    }

    private Spielerfarbe getNaechsterSpieler() {
        final Spielerfarbe aktuellerSpieler = this.aktuellerSpieler.get();

        if (aktuellerSpieler.equals(Spielerfarbe.BLAU)) {
            return Spielerfarbe.ROT;
        } else {
            return Spielerfarbe.BLAU;
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
        final Spielerfarbe gegner = getNaechsterSpieler();
        final List<Kachel> gegnerischeKacheln = kacheln.stream().filter(kachel -> {
            final Optional<Figur> figur = kachel.getFigur();
            return figur.isPresent() && figur.get().getSpielerfarbe().equals(gegner);
        }).collect(Collectors.toList());
        final List<Kachel> moeglicheEndkacheln = new ArrayList<>();

        for (final Kachel kachel : gegnerischeKacheln) {
            final List<Kachel> endKacheln = berechneAlleValidenBewegungen(kachel);
            moeglicheEndkacheln.addAll(endKacheln);
        }

        if (moeglicheEndkacheln.size() == 0) {
            this.gewinner.setValue(Optional.of(aktuellerSpieler));
        }
    }

    private List<Kachel> berechneAlleValidenBewegungen(final Kachel startKachel) {
        final Optional<Figur> figur = startKachel.getFigur();
        final ArrayList<Kachel> kacheln = new ArrayList<>();

        if (figur.isEmpty()) {
            return kacheln;
        }

        final Spielerfarbe spieler = figur.get().getSpielerfarbe();
        final int y = startKachel.getKoordinaten().getY() + spieler.getRichtung();

        for (int x = 0; x < 3; x++) {
            final Optional<Kachel> zielKachel = getKachelnBeiKoordinaten(new Koordinaten(x, y));

            if (zielKachel.isPresent()) {
                final boolean isErlaubteBewegung = this.isValideBewegung(startKachel.getFigur().get(),
                        zielKachel.get());

                if (isErlaubteBewegung) {
                    kacheln.add(zielKachel.get());
                }
            }
        }

        return kacheln;
    }

    private Optional<Kachel> getKachelnBeiKoordinaten(final Koordinaten koordinaten) {
        return kacheln.stream().filter(kachel -> kachel.getKoordinaten().equals(koordinaten)).findAny();
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

    private boolean isValideBewegung(final Figur figurStartkachel, final Kachel zielKachel) {
        final Koordinaten figurKoordinaten = figurStartkachel.getKoordinaten();

        final int dx = zielKachel.getKoordinaten().getX() - figurKoordinaten.getX();
        final int dy = zielKachel.getKoordinaten().getY() - figurKoordinaten.getY();

        if (hatZielkachelFigurAndererFarbe(zielKachel, figurStartkachel.getSpielerfarbe())) {
            return Math.abs(dx) == 1 && dy == figurStartkachel.getSpielerfarbe().getRichtung();
        } else if (!zielKachel.hatFigur()) {
            return dx == 0 && dy == figurStartkachel.getSpielerfarbe().getRichtung();
        } else {
            return false;
        }
    }

    private void bewegeFigurZuKachel(final Figur figur, final Kachel zielKachel) {
        figur.setKachel(zielKachel);
        waehleFigurAb();
    }

    private boolean hatZielkachelFigurAndererFarbe(final Kachel zielKachel, final Spielerfarbe spielerfarbe) {
        if (!zielKachel.hatFigur()) {
            return false;
        }

        final Figur zielFigur = zielKachel.getFigur().get();

        return !zielFigur.getSpielerfarbe().equals(spielerfarbe);
    }

    private void initSpielfeld() {
        final List<Kachel> kacheln = new ArrayList<>();

        for (int x = 0; x < KACHELN_PRO_REIHE; x++) {
            for (int y = 0; y < KACHELN_PRO_REIHE; y++) {
                final Kachel kachel = new Kachel(new Koordinaten(x, y));

                if (y == 0) {
                    stelleFigurAufKachel(kachel, Spielerfarbe.ROT);
                } else if (y == 2) {
                    stelleFigurAufKachel(kachel, Spielerfarbe.BLAU);
                }

                kacheln.add(kachel);
            }
        }

        this.kacheln.set(FXCollections.observableArrayList(kacheln));
        this.ausgewaehlteFigur.set(Optional.empty());
        this.aktuellerSpieler.set(Spielerfarbe.BLAU);
        this.gewinner.set(Optional.empty());
    }

    private void stelleFigurAufKachel(final Kachel kachel, final Spielerfarbe farbe) {
        final Figur figur = new Figur(kachel, farbe);

        kachel.setFigur(figur);
    }
}