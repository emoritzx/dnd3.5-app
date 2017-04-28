/**
 * Copyright (c) 2016-2017, Evan Moritz.
 * Licensed under the MIT License. See the accompanying LICENSE file for terms.
 */
package dndlib.dice;

import java.util.concurrent.ThreadLocalRandom;

/**
 * An implementation of the Die interface that returns a random value from a
 * uniform distribution of numbers within the specified range when rolled.
 *
 * @author emori
 */
public class UniformDie extends ScriptedDie {

    /**
     * Constructor.
     *
     * @param lower the lower bound of the range of numbers returned (inclusive)
     * @param upper the upper bound of the range of numbers returned (inclusive)
     * @throws IllegalArgumentException if lower greater than upper
     */
    public UniformDie(int lower, int upper) {
        super(
                upper - lower + 1,
                () -> ThreadLocalRandom.current().nextInt(lower, upper + 1)
        );
        if (lower > upper) {
            throw new IllegalArgumentException(String.format("%d > %d", lower, upper));
        }
    }
}
