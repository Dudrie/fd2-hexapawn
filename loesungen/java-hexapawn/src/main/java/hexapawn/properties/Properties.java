package hexapawn.properties;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ReadOnlyIntegerWrapper;
import javafx.beans.property.SimpleIntegerProperty;

public class Properties {
    public static void main(String[] args) {
        final IntegerProperty zahl = new SimpleIntegerProperty(1);

        zahl.addListener((observable, alterWert, neuerWert) -> {
            System.out.println("Aktueller Wert: " + neuerWert);
        });

        zahl.set(2);
        zahl.set(8);
        zahl.set(4);

        final ReadOnlyIntegerWrapper nurLesbareZahl = new ReadOnlyIntegerWrapper(0);

        nurLesbareZahl.getReadOnlyProperty().addListener((o, a, n) -> {
            System.out.println("Aktueller, lesbarer Wert: " + n);
        });

        nurLesbareZahl.set(3);
        nurLesbareZahl.set(5);
        nurLesbareZahl.set(9);
    }
}