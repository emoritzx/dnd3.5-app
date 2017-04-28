/**
 * Copyright (c) 2016-2017, Evan Moritz.
 * Licensed under the MIT License. See the accompanying LICENSE file for terms.
 */
package dndlib.dice;

/**
 * The Die class represents a physical die for generating bounded random numbers.
 * @author emori
 */
public interface Die {
    
    /**
     * Generates a bounded random number.
     * @return the random number the Die generated
     */
    public int roll();
    
    /**
     * Returns the number of sides for the die
     * @return the number of sides for the die
     */
    public int getSize();
}
