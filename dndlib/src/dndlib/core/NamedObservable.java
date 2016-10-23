/*
 * Think about the license.
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
