/*
 * Think about the license.
 */
package dndlib.core;

import java.util.function.Function;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

/**
 *
 * @author emori
 */
public class IntegerAttribute extends ValueAttribute {

    public IntegerAttribute(String name, Function<String, String> abbreviator) {
        super(name, abbreviator);
    }

    public IntegerAttribute(String name, String abbreviation) {
        super(name, abbreviation);
    }

    public IntegerAttribute(String name) {
        super(name);
    }
    
    private final SimpleIntegerProperty value = new SimpleIntegerProperty();

    public final int getValue() {
        return value.intValue();
    }

    public final void setValue(int value) {
        this.value.set(value);
    }

    @Override
    public IntegerProperty valueProperty() {
        return value;
    }

}
