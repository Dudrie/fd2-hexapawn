package com.github.dudrie.hexapawn.model;

import java.util.Collection;
import java.util.HashSet;

/**
 * Spieler
 */
public class Spieler {
    private final Spielerfarbe farbe;
    private final Collection<Figur> figuren;

    public Spieler(final Spielerfarbe farbe) {
        this.farbe = farbe;
        this.figuren = new HashSet<>();
    }

    public Spielerfarbe getFarbe() {
        return this.farbe;
    }

    public void fugeFigurHinzu(final Figur figur) {
        this.figuren.add(figur);
    }

}