package dndlib.character;

import dndlib.core.Named;
import dndlib.core.NamedObservable;
import dndlib.core.NumberedObservable;
import dndlib.structures.NumberEntity;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

/**
 *
 * @author emori
 */
public class Ability extends NumberEntity implements Named, NumberedObservable {

    public static String abbreviate(String string) {
        return (string.length() <= 3)
                ? string
                : string.substring(0, 3).toUpperCase();
    }

    private final Named name;
    private final IntegerProperty modifier = new SimpleIntegerProperty();
    
    public Ability(Named name) {
        this.name = name;
        modifier.bind(super.valueProperty().subtract(10).divide(2));
    }

    @Override
    public String getAbbreviation() {
        return name.getAbbreviation();
    }

    public final int getModifier() {
        return modifier.intValue();
    }

    @Override
    public String getName() {
        return name.getName();
    }

    public IntegerProperty modifierProperty() {
        return modifier;
    }
}
