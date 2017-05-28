/**
 * Copyright (c) 2016-2017, Evan Moritz.
 * Licensed under the MIT License. See the accompanying LICENSE file for terms.
 */
package dndlib.character;

import static dndlib.character.SpellRangeCalculation.*;
import static org.testng.Assert.*;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

/**
 *
 * @author emori
 */
public class SpellRangeCalculationNGTest {
    
    @DataProvider
    public static Object[][] data() {
        return new Object[][] {
            { "CLOSE", CLOSE_BASE, CLOSE_MOD, 2, 35 },
            { "MEDIUM", MEDIUM_BASE, MEDIUM_MOD, 4, 140 },
            { "LONG", LONG_BASE, LONG_MOD, 4, 560 },
        };
    }

    /**
     * Test of calculate method, of class SpellRangeCalculation.
     */
    @Test(dataProvider = "data")
    public void testCalculate(String name, int base, int mod, int level, int expected) {
        System.out.println("calculate");
        int result = SpellRangeCalculation.calculate(base, mod, level);
        System.out.printf("%s: %d'%n", name, result);
        assertEquals(result, expected);
    }
    
}
