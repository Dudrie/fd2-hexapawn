package hexapawn.view;

import static hexapawn.model.Spielfeld.KACHELN_PRO_REIHE;

import java.util.List;

import hexapawn.model.Kachel;
import hexapawn.model.Spielfeld;
import javafx.scene.Group;
import javafx.scene.layout.Pane;

public class Spielbrett extends Pane {
    public static final int KACHEL_LAENGE = 128;

    private final Group spielfeldKacheln;
    private final Spielfeld spielfeld;

    public Spielbrett(final Spielfeld spielfeld) {
        super();

        this.spielfeld = spielfeld;
        this.spielfeldKacheln = new Group();

        this.spielfeld.getKacheln().addListener((obs, alt, neu) -> baueSpielfeldAusKacheln(neu));

        baueSpielfeldAusKacheln(this.spielfeld.getKacheln());

        this.setPrefSize(KACHEL_LAENGE * KACHELN_PRO_REIHE, KACHEL_LAENGE * KACHELN_PRO_REIHE);
        this.getChildren().add(spielfeldKacheln);
    }

    private void baueSpielfeldAusKacheln(final List<Kachel> kacheln) {
        this.spielfeldKacheln.getChildren().clear();

        kacheln.forEach(kachel -> {
            final SpielbrettKachel spielbrettKachel = new SpielbrettKachel(kachel, spielfeld);

            this.spielfeldKacheln.getChildren().add(spielbrettKachel);
        });
    }
}