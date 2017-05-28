/**
 * Copyright (c) 2016-2017, Evan Moritz.
 * Licensed under the MIT License. See the accompanying LICENSE file for terms.
 */
package dndlib.character;

import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;
import javafx.beans.property.IntegerProperty;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author emori
 */
public class BonusSpellCalculationTest {
    
    public BonusSpellCalculationTest() {
    }

    /**
     * Test of atLevel method, of class BonusSpellCalculation.
     */
    @Test
    public void testAtLevel() {
        System.out.println("atLevel");
        int level = 0;
        BonusSpellCalculation instance = null;
        IntegerProperty expResult = null;
        IntegerProperty result = instance.atLevel(level);
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }

    /**
     * Test of calculate method, of class BonusSpellCalculation.
     */
    @Test
    public void testCalculate() {
        System.out.println("calculate");
        int modifier = 5;
        List<Integer> expected = Arrays.asList(0, 2, 1, 1, 1, 1, 0, 0, 0, 0);
        IntStream
            .rangeClosed(0, 9)
            .forEach(level -> {
                int result = BonusSpellCalculation.calculate(modifier, level);
                System.out.printf("Spell level %d: %d%n", level, result);
                assertEquals((int)expected.get(level), result);
            });
    }
    
}
