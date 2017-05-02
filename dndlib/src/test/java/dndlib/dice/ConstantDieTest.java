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

    /**
     * Test of toString method, of class ConstantDie.
     * Covers toString def-use path [1,2]
     * and Constructor def-use path [1,2]
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
     * Covers levelAdjustment def-use path [1,2,3]
     */
    @Test
    public void testLevelAdjustment1() {
        BiFunction<Integer, Integer, Die> adjFnc
                = ConstantDie.levelAdjustment((in) -> Math.floorDiv(in, 3));
        assertEquals(6, adjFnc.apply(1, 6).getSize());
    }
    
    /**
     * Test of levelAdjustment method, of class ConstantDie.
     * Covers levelAdjustment def-use path [1,2,4]
     */
    @Test
    public void testLevelAdjustment2() {
        BiFunction<Integer, Integer, Die> adjFnc
                = ConstantDie.levelAdjustment((in) -> Math.floorDiv(in, 3));
        assertEquals(2, adjFnc.apply(2, 6).getSize());
    }
    

}
