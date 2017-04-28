/**
 * Copyright (c) 2016-2017, Evan Moritz.
 * Licensed under the MIT License. See the accompanying LICENSE file for terms.
 */
package dndlib.dice;

/**
 * A uniform die that will return a random number between 1 and the number of
 * sides when rolled.
 *
 * @author emori
 */
public class StandardDie extends UniformDie {

    /**
     * Constructor.
     * @param sides the number of sides on the die
     */
    public StandardDie(int sides) {
        super(1, sides);
    }

    @Override
    public String toString() {
        return "d" + getSize();
    }
}
