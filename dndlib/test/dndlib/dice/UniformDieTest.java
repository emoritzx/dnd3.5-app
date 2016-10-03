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
    public Object[][] getData() {
        return new Object[][]{
            {-10, 10},
            {1, 5},
            {50, 100000}
        };
    }

    
    
    @Test(dataProvider = "getData")
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
    
    @Test(dataProvider = "getData")
    public void getLowerTest(int lower, int upper) {
        UniformDie die = new UniformDie(lower, upper);
        int actual = die.getLower();
        assertEquals(actual, lower);
    }
    
    @Test(dataProvider = "getData")
    public void getUpperTest(int lower, int upper) {
        UniformDie die = new UniformDie(lower, upper);
        int actual = die.getUpper();
        assertEquals(actual, upper);
    }
    
    @Test(dataProvider = "getData")
    public void getSizeTest(int lower, int upper) {
        int expected = upper - lower + 1;
        UniformDie die = new UniformDie(lower, upper);
        int actual = die.getSize();
        assertEquals(actual, expected);
    }
}
