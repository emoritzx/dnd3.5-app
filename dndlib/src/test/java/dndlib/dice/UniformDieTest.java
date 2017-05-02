/**
 * Copyright (c) 2016-2017, Evan Moritz.
 * Licensed under the MIT License. See the accompanying LICENSE file for terms.
 */
package dndlib.dice;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Test cases for the UniformDie class
 * @author sldur
 */
public class UniformDieTest {
    
    /**
     * Test case for the UniformDie constructor
     * with a single value for both lower and upper
     */
    @Test
    public void testWithSingleValueRange() {
        UniformDie die = new UniformDie(1,1);
        assertEquals(1, die.roll());
        assertEquals(1, die.roll());
    }
    
    /**
     * Test case for the UniformDie constructor
     * with a small range of values
     */
    @Test
    public void testWithTwoValueRange(){
        UniformDie die = new UniformDie(2,3);
        int roll = die.roll();
        assertTrue(roll == 2 || roll == 3);
    }
    
    /**
     * Test case for the UniformDie constructor
     * with a larger range of values
     */
    @Test
    public void testWithLargerRange(){
        UniformDie die = new UniformDie(3,23);
        int roll = die.roll();
        assertTrue(roll >= 3 && roll <= 23);
    }
    
    /**
     * Test case for the UniformDie constructor
     * Covers Constructor def-use path [1,2,3,4]
     */
    @Test(expected=IllegalArgumentException.class)
    public void testIllegalArg(){
        UniformDie die = new UniformDie(5,2);
    }
    
}
