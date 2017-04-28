/**
 * Copyright (c) 2016-2017, Evan Moritz.
 * Licensed under the MIT License. See the accompanying LICENSE file for terms.
 */
package dndlib.core;

import javafx.beans.property.StringProperty;

/**
 *
 * @author emori
 */
public interface NamedObservable extends Named {
    StringProperty nameProperty();
    StringProperty abbreviationProperty();
}
