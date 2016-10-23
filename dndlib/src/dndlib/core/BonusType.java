/*
 * Think about the license.
 */
package dndlib.core;

import java.util.function.IntBinaryOperator;

/**
 *
 * @author emori
 */
public interface BonusType extends Named {
    IntBinaryOperator getOperator();
}
