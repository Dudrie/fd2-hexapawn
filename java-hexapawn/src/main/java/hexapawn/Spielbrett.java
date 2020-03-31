package hexapawn;

import javafx.scene.layout.Pane;

/**
 * Spielbrett
 */
public class Spielbrett extends Pane {

    public static final int KACHEL_LAENGE = 64;
    public static final int KACHAL_ANZAHL = 8;

    public Spielbrett() {
        this.setPrefSize(KACHEL_LAENGE * KACHAL_ANZAHL, KACHEL_LAENGE * KACHAL_ANZAHL);
    }
}