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
public abstract class AbstractNumberEntity extends Entity {

    public AbstractNumberEntity(String name, Function<String, String> abbreviator) {
        super(name, abbreviator);
    }

    public AbstractNumberEntity(String name, String abbreviation) {
        super(name, abbreviation);
    }

    public AbstractNumberEntity(String name) {
        super(name);
    }
    public abstract IntegerProperty valueProperty();
}
