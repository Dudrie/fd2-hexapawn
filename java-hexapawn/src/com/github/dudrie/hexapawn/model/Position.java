package com.github.dudrie.hexapawn.model;

/**
 * Position
 */
public class Position {
    private final int zeile;
    private final int spalte;

    public Position(final int zeile, final int spalte) {
        this.zeile = zeile;
        this.spalte = spalte;
    }

    public int getZeile() {
        return this.zeile;
    }

    public int getSpalte() {
        return this.spalte;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Position)) {
            return false;
        }

        final Position position = (Position) obj;
        return position.zeile == this.zeile && position.spalte == this.spalte;
    }

}