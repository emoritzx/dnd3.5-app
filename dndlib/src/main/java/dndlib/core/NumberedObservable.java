/*
 * Think about the license.
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
