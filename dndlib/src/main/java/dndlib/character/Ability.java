/**
 * Copyright (c) 2016-2017, Evan Moritz.
 * Licensed under the MIT License. See the accompanying LICENSE file for terms.
 */
package dndlib.character;

import dndlib.core.Named;
import dndlib.core.NumberedObservable;
import dndlib.structures.NumberEntity;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

/**
 *
 * @author emori
 */
public class Ability extends NumberEntity implements Named, NumberedObservable {

    public static final int DEFAULT_VALUE = 10;
    
    public static String abbreviate(String string) {
        return (string.length() <= 3)
                ? string
                : string.substring(0, 3).toUpperCase();
    }

    private final Named name;
    private final IntegerProperty modifier = new SimpleIntegerProperty();

    public Ability(Named name) {
        this(name, DEFAULT_VALUE);
    }
    
    public Ability(Named name, int value) {
        this.name = name;
        modifier.bind(super.valueProperty().subtract(10).divide(2));
        setValue(value);
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

    public void adjust(int amount) {
        setValue(getValue() + amount);
    }

    @Override
    public String toString() {
        int mod = getModifier();
        char sign = mod < 0
            ? '-'
            : '+';
        return String.format("%s: %d (%c%d)",
            getAbbreviation(),
            getValue(),
            sign,
            mod);
    }
}
