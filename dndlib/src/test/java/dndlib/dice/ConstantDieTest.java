package dndlib.dice;

import java.util.function.BiFunction;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Test cases for the ConstantDie class.
 *
 * @author sldur
 */
public class ConstantDieTest {

    public ConstantDieTest() {
    }

    /**
     * Test of toString method, of class ConstantDie.
     */
    @Test
    public void testToStringSix() {
        ConstantDie six = new ConstantDie(6);
        String expected = "constant " + 6;
        assertEquals(expected, six.toString());   
    }
    
    /**
     * Test of toString method, of class ConstantDie.
     */
    @Test
    public void testToStringFour(){
        ConstantDie four = new ConstantDie(4);
        String expected = "constant " + 4;
        assertEquals(expected, four.toString()); 
    }

    /**
     * Test of levelAdjustment method, of class ConstantDie.
     */
    @Test
    public void testLevelAdjustment() {
       BiFunction<Integer, Integer, Die> adjFnc = 
               ConstantDie.levelAdjustment((in)-> Math.floorDiv(in, 3));
       ConstantDie die6 = new ConstantDie(6);
       assertEquals(die6.getSize(), adjFnc.apply(1, 6).getSize());
       ConstantDie die2 = new ConstantDie(2);
       assertEquals(die2.getSize(), adjFnc.apply(2, 6).getSize());
    }

}
