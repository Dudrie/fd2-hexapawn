package com.github.dudrie.hexapawn.model;

/**
 * Figur
 */
public class Figur {
    private final Spieler spieler;
    private Kachel aktuellesFeld;

    public Figur(final Spieler spieler, final Kachel startfeld) {
        this.spieler = spieler;
        this.aktuellesFeld = startfeld;
    }

    public Spieler getSpieler() {
        return this.spieler;
    }

    public Kachel getAktuellesFeld() {
        return this.aktuellesFeld;
    }

    public void setAktuellesFeld(Kachel aktuellesFeld) {
        this.aktuellesFeld = aktuellesFeld;
    }

}