/**
 * Copyright (c) 2016-2017, Evan Moritz.
 * Licensed under the MIT License. See the accompanying LICENSE file for terms.
 */
package dndlib.structures;

import dndlib.core.NamedObservable;
import java.util.function.Function;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author emori
 */
public class NameEntity implements NamedObservable {

    private final StringProperty name;
    private final StringProperty abbreviation;

    public NameEntity(String name) {
        this(name, Function.identity());
    }

    public NameEntity(String name, Function<String, String> abbreviator) {
        this(name, abbreviator.apply(name));
    }

    public NameEntity(String name, String abbreviation) {
        this.name = new SimpleStringProperty(name);
        this.abbreviation = new SimpleStringProperty(abbreviation);
    }

    @Override
    public final String getAbbreviation() {
        return abbreviation.get();
    }

    @Override
    public StringProperty abbreviationProperty() {
        return abbreviation;
    }

    @Override
    public final String getName() {
        return name.get();
    }

    @Override
    public StringProperty nameProperty() {
        return name;
    }

    public final void setName(String name) {
        setName(name, Function.identity());
    }

    public final void setName(String name, Function<String, String> abbreviator) {
        setName(name, abbreviator.apply(name));
    }

    public final void setName(String name, String abbreviation) {
        this.name.setValue(name);
        this.abbreviation.setValue(name);
    }

    @Override
    public String toString() {
        return name.get();
    }
}
