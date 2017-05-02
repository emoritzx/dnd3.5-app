/**
 * Copyright (c) 2016-2017, Evan Moritz.
 * Licensed under the MIT License. See the accompanying LICENSE file for terms.
 */
package dndlib.dice;

import static org.junit.Assert.*;
import org.junit.Test;
import org.junit.experimental.theories.DataPoints;
import org.junit.experimental.theories.Theories;
import org.junit.experimental.theories.Theory;
import org.junit.runner.RunWith;

/**
 * Test cases for the StandardDie class
 *
 * @author sldur
 */
@RunWith(Theories.class)
public class StandardDieTest {

    @DataPoints
    public static int[] dieSizes = new int[]{
        1, 6, 20, 10
    };

    /**
     * Test of roll method, of class StandardDie.
     *
     * @param size the size of the die
     */
    @Theory
    public void testRoll(int size) {
        StandardDie die = new StandardDie(size);
        int roll = die.roll();
        assertTrue(roll >= 1 && roll <= size);
    }

    /**
     * Test of getSize method, of class StandardDie.
     *
     * @param size the size of the die
     */
    @Theory
    public void testGetSize(int size) {
        StandardDie die = new StandardDie(size);
        assertEquals(size, die.getSize());
    }

    /**
     * Test of toString method, of class StandardDie.
     * Covers the toString def-use path [1,2]
     * and Constructor def-use path [1,2]
     *
     * @param size the size of the die
     */
    @Theory
    public void testToString(int size) {
        StandardDie die = new StandardDie(size);
        String expected = "d" + size;
        assertEquals(expected, die.toString());
    }

    /**
     * Test of StandardDie constructor for IllegalArgumentException
     */
    @Test(expected = IllegalArgumentException.class)
    public void testIllegalArgumentException() {
        StandardDie die = new StandardDie(0);
    }

}
