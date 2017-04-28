/**
 * Copyright (c) 2016-2017, Evan Moritz.
 * Licensed under the MIT License. See the accompanying LICENSE file for terms.
 */
package dndlib.structures;

import dndlib.core.NumberedObservable;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

/**
 *
 * @author emori
 */
public class NumberEntity implements NumberedObservable {
    
    private final SimpleIntegerProperty value;

    public NumberEntity() {
        this(0);
    }

    public NumberEntity(int value) {
        this.value = new SimpleIntegerProperty(value);
    }
    
    @Override
    public final int getValue() {
        return value.intValue();
    }

    protected final void setValue(int value) {
        this.value.setValue(value);
    }

    @Override
    public IntegerProperty valueProperty() {
        return value;
    }

}
