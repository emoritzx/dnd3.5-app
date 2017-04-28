/**
 * Copyright (c) 2016-2017, Evan Moritz.
 * Licensed under the MIT License. See the accompanying LICENSE file for terms.
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
