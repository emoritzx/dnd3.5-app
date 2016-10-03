package dndlib.character;

import dndlib.core.SimpleNumberEntity;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

/**
 *
 * @author emori
 */
public class Ability extends SimpleNumberEntity {

    public static String abbreviate(String string) {
        return (string.length() <= 3)
                ? string
                : string.substring(0, 3).toUpperCase();
    }

    private final IntegerProperty modifier = new SimpleIntegerProperty();

    public Ability(String name) {
        super(name, Ability::abbreviate);
        modifier.bind(super.valueProperty().subtract(10).divide(2));
    }

    public final int getModifier() {
        return modifier.intValue();
    }

    public IntegerProperty modifierProperty() {
        return modifier;
    }
}
