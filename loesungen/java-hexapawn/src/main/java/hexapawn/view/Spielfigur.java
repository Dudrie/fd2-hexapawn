package hexapawn.view;

import static hexapawn.view.Spielbrett.KACHEL_LAENGE;

import hexapawn.model.Figur;
import javafx.beans.value.ObservableValue;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Ellipse;

public class Spielfigur extends StackPane {
    private final Ellipse kreis;
    private final Figur figur;

    public Spielfigur(final Figur figur) {
        super();

        this.figur = figur;
        this.kreis = new Ellipse(getRadius(), getRadius());

        this.setMouseTransparent(true);
        this.figur.isAusgewaehlt().addListener(this::onChangeFigurAusgewaehlt);

        initStyle();
    }

    private void onChangeFigurAusgewaehlt(final ObservableValue<? extends Boolean> observable, final boolean oldValue,
            final boolean newValue) {
        if (newValue) {
            setFigurFarbe(Color.ORANGE);
        } else {
            setFigurFarbe(this.figur.getSpielerfarbe().getFarbe());
        }
    }

    private void initStyle() {
        this.kreis.setStroke(Color.BLACK);
        this.kreis.setStrokeWidth(4);

        setFigurFarbe(getFigurFarbe());
        getChildren().add(kreis);
    }

    private void setFigurFarbe(final Color farbe) {
        this.kreis.setFill(farbe);
    }

    private Color getFigurFarbe() {
        assert this.figur != null;

        if (this.figur.isAusgewaehlt().get()) {
            return Color.ORANGE;
        }

        return this.figur.getSpielerfarbe().getFarbe();
    }

    private double getRadius() {
        return KACHEL_LAENGE * 0.33;
    }
}