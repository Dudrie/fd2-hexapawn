package com.github.dudrie.hexapawn.model;

/**
 * Kachel
 */
public class Kachel {
    private final Position position;

    public Kachel(final Position position) {
        this.position = position;
    }

    public Position getPosition() {
        return this.position;
    }
}