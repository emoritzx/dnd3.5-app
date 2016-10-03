/*
 * Think about the license.
 */
package dndlib.dice;

import static org.testng.Assert.*;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

/**
 *
 * @author emori
 */
public class StandardDieTest {
    
    @DataProvider
    public Object[][] getLegalSizes() {
        return new Object[][]{
            {1},
            {2},
            {10},
            {1000},
            {120},
        };
    }
 
    @DataProvider
    public Object[][] getIllegalSizes() {
        return new Object[][]{
            {0},
            {-1},
            {-2},
            {-20},
        };
    }
    
    @Test(dataProvider = "getIllegalSizes", expectedExceptions = IllegalArgumentException.class)
    public void invalidSizeTest(int size) {
        Die standard = new StandardDie(size);
    }
    
    @Test(dataProvider = "getLegalSizes")
    public void getSizeTest(int size) {
        StandardDie die = new StandardDie(size);
        int actual = die.getSize();
        assertEquals(actual, size);
    }
    
    @Test(dataProvider = "getLegalSizes")
    public void toStringTest(int size) {
        Die constant = new StandardDie(size);
        String actual = constant.toString();
        assertEquals(actual, "d" + size);
    }
}