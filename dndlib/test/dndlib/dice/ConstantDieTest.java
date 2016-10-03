/*
 * Think about the license.
 */
package dndlib.dice;

import org.testng.annotations.Test;
import static org.testng.Assert.*;

/**
 *
 * @author emori
 */
public class ConstantDieTest {
    
    @Test
    public void rollTest() {
        int expected = 37;
        Die constant = new ConstantDie(expected);
        int actual = constant.roll();
        assertEquals(actual, expected);
    }
    
    @Test
    public void sizeTest() {
        int expected = 31;
        Die constant = new ConstantDie(expected);
        int actual = constant.getSize();
        assertEquals(actual, expected);
    }
    
    @Test
    public void toStringTest() {
        int size = 29;
        Die constant = new ConstantDie(size);
        String actual = constant.toString();
        assertEquals(actual, "constant " + size);
    }
}
