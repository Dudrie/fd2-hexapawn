package hexapawn.model;

import java.util.Optional;

import javafx.beans.property.ReadOnlyObjectProperty;
import javafx.beans.property.ReadOnlyObjectWrapper;

public class Kachel {
    private final ReadOnlyObjectWrapper<Optional<Figur>> figur;
    private final Koordinaten koordinaten;

    public Kachel(final Koordinaten koordinaten) {
        this(koordinaten, null);
    }

    public Kachel(final Koordinaten koordinaten, final Figur figur) {
        this.koordinaten = koordinaten;
        this.figur = new ReadOnlyObjectWrapper<Optional<Figur>>(Optional.ofNullable(figur));

    }

    public Koordinaten getKoordinaten() {
        return this.koordinaten;
    }

    public boolean hatFigur() {
        return this.figur.get().isPresent();
    }

    public ReadOnlyObjectProperty<Optional<Figur>> getFigurProperty() {
        return this.figur.getReadOnlyProperty();
    }

    public Optional<Figur> getFigur() {
        return this.figur.get();
    }

    public void setFigur(final Figur figur) {
        this.figur.set(Optional.ofNullable(figur));
    }
}