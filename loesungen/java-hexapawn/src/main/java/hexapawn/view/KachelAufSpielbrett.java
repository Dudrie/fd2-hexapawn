package hexapawn.view;

import static hexapawn.view.Spielbrett.KACHEL_LAENGE;

import java.util.Optional;

import hexapawn.model.Figur;
import hexapawn.model.Kachel;
import hexapawn.model.Koordinaten;
import hexapawn.service.SpielService;
import javafx.beans.value.ObservableValue;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;

public class KachelAufSpielbrett extends StackPane {
    private final Kachel kachel;
    private final SpielService spielfeld;

    public KachelAufSpielbrett(final Kachel kachel, final SpielService spielfeld) {
        super();

        this.kachel = kachel;
        this.spielfeld = spielfeld;

        kachel.getFigurProperty().addListener(this::onFigurGeandert);
        aendereAngezeigteFigur(kachel.getFigur());

        setPrefSize(KACHEL_LAENGE, KACHEL_LAENGE);
        setBorder(new Border(
                new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));

        final Koordinaten pixel = konvertiereKoordinatenZuPixeln();
        relocate(pixel.getX(), pixel.getY());

        setOnMouseClicked(this::onKachelAngeklickt);
    }

    private void onKachelAngeklickt(final MouseEvent e) {
        final Optional<Figur> figur = this.kachel.getFigur();

        if (figur.isPresent()) {
            if (!spielfeld.isEineFigurAusgewaehlt()) {
                spielfeld.waehleFigurAus(figur.get());
            } else if (spielfeld.isFigurAusgewaehlt(figur.get())) {
                spielfeld.waehleFigurAb();
            } else {
                spielfeld.bewegeAuswahlZuZiel(this.kachel);
            }
        } else if (spielfeld.isEineFigurAusgewaehlt()) {
            spielfeld.bewegeAuswahlZuZiel(this.kachel);
        }
    }

    private void onFigurGeandert(final ObservableValue<? extends Optional<Figur>> observable,
            final Optional<Figur> oldValue, final Optional<Figur> newValue) {
        aendereAngezeigteFigur(newValue);
    }

    private void aendereAngezeigteFigur(final Optional<Figur> figur) {
        this.getChildren().clear();

        if (figur.isPresent()) {
            this.getChildren().add(new Spielfigur(figur.get()));
        }
    }

    private Koordinaten konvertiereKoordinatenZuPixeln() {
        assert this.kachel != null;
        final Koordinaten koordinaten = this.kachel.getKoordinaten();

        return new Koordinaten(koordinaten.getX() * KACHEL_LAENGE, koordinaten.getY() * KACHEL_LAENGE);
    }
}