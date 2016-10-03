/*
 * Think about the license.
 */
package dndlib.dice;

import java.util.stream.IntStream;
import static org.testng.Assert.*;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

/**
 *
 * @author emori
 */
public class UniformDieTest {
    
    @DataProvider
    public Object[][] getLegalBounds() {
        return new Object[][]{
            {-10, 10},
            {1, 5},
            {50, 100000},
            {1, 1},
            {0, 0},
            {-1, 0}
        };
    }
    
    @DataProvider
    public Object[][] getIllegalBounds() {
        return new Object[][]{
            {0, -1},
            {2, -10},
            {-10, -12}
        };
    }

    @Test(dataProvider = "getIllegalBounds", expectedExceptions = IllegalArgumentException.class)
    public void invalidBoundsTest(int lower, int upper) {
        UniformDie uniformDie = new UniformDie(lower, upper);
    }
    
    @Test(dataProvider = "getLegalBounds")
    public void rollTest(int lower, int upper) {
        UniformDie die = new UniformDie(lower, upper);
        IntStream
            .range(0, 1000)
            .map(i -> die.roll())
            .forEach(roll -> {
                assertTrue(roll >= lower);
                assertTrue(roll <= upper);
            });
    }
    
    @Test(dataProvider = "getLegalBounds")
    public void getLowerTest(int lower, int upper) {
        UniformDie die = new UniformDie(lower, upper);
        int actual = die.getLower();
        assertEquals(actual, lower);
    }
    
    @Test(dataProvider = "getLegalBounds")
    public void getUpperTest(int lower, int upper) {
        UniformDie die = new UniformDie(lower, upper);
        int actual = die.getUpper();
        assertEquals(actual, upper);
    }
    
    @Test(dataProvider = "getLegalBounds")
    public void getSizeTest(int lower, int upper) {
        int expected = upper - lower + 1;
        UniformDie die = new UniformDie(lower, upper);
        int actual = die.getSize();
        assertEquals(actual, expected);
    }
}
