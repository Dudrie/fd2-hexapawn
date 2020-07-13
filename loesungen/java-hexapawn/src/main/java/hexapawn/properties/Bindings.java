package hexapawn.properties;

import javafx.beans.binding.NumberBinding;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class Bindings {
    public static void main(String[] args) {
        final IntegerProperty zahl1 = new SimpleIntegerProperty(1);
        final IntegerProperty zahl2 = new SimpleIntegerProperty(2);

        final NumberBinding summe = zahl1.add(zahl2);
        System.out.println("Summe: " + summe.getValue());

        zahl1.set(4);
        System.out.println("Summe: " + summe.getValue());

        final IntegerProperty zahl3 = new SimpleIntegerProperty(3);
        final IntegerProperty zahl4 = new SimpleIntegerProperty(4);

        final NumberBinding ergebnis = zahl1.multiply(zahl2).add(zahl3.multiply(zahl4));
        System.out.println("Produkt: " + ergebnis.getValue());

        zahl1.set(6);
        System.out.println("Produkt: " + ergebnis.getValue());
    }
}