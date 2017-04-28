/**
 * Copyright (c) 2016-2017, Evan Moritz.
 * Licensed under the MIT License. See the accompanying LICENSE file for terms.
 */
package dndlib.core;

import javafx.beans.property.IntegerProperty;

/**
 *
 * @author emori
 */
public interface NumberedObservable extends Numbered {
    IntegerProperty valueProperty();
}
