/*
 * Think about the license.
 */
package dndlib.core;

import java.util.function.Function;
import javafx.beans.property.IntegerProperty;

/**
 *
 * @author emori
 */
public abstract class ValueAttribute extends Attribute {

    public ValueAttribute(String name, Function<String, String> abbreviator) {
        super(name, abbreviator);
    }

    public ValueAttribute(String name, String abbreviation) {
        super(name, abbreviation);
    }

    public ValueAttribute(String name) {
        super(name);
    }
    public abstract IntegerProperty valueProperty();
}
