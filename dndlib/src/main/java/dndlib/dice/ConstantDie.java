/**
 * Copyright (c) 2016-2017, Evan Moritz.
 * Licensed under the MIT License. See the accompanying LICENSE file for terms.
 */
package dndlib.dice;

import java.util.function.BiFunction;
import java.util.function.Function;

/**
 * An implementation of the Die interface that always returns the same number
 * when rolled.
 *
 * @author emori
 */
public class ConstantDie extends ScriptedDie {

    /**
     * Constructor.
     * @param value the die should always return when rolled
     */
    public ConstantDie(int value) {
        super(value, () -> value);
    }
    
    @Override
    public String toString() {
        return "constant " + getSize();
    }
    
    /**
     * Returns a bifunction from level and size that performs an adjustment to
     * the die, as specified in the provided function, if the level is greater
     * than one. The level is specified as the first argument of the bifunction
     * and the size is the second argument.
     *
     * @param func the adjustment to apply to the die
     * @return function that returns an adjusted die based on the level and size provided
     */
    public static BiFunction<Integer, Integer, Die> levelAdjustment(Function<Integer, Integer> func) {
        return (level, size) -> 
            level == 1
                ? new ConstantDie(size)
                : new ConstantDie(func.apply(size));
    }
}
