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
public class SimpleNumberEntity extends AbstractNumberEntity {

    public SimpleNumberEntity(String name, Function<String, String> abbreviator) {
        super(name, abbreviator);
    }

    public SimpleNumberEntity(String name, String abbreviation) {
        super(name, abbreviation);
    }

    public SimpleNumberEntity(String name) {
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
