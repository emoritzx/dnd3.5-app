package dndlib.dice;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Supplier;
import static org.junit.Assert.assertEquals;
import org.junit.Test;
import org.junit.experimental.theories.DataPoints;
import org.junit.experimental.theories.Theories;
import org.junit.experimental.theories.Theory;
import org.junit.runner.RunWith;

/**
 * Test cases for the ScriptedDie class.
 *
 * @author sldur
 */
@RunWith(Theories.class)
public class ScriptedDieTest {

    public static class TestItem {

        int size;
        Supplier<Supplier<Integer>> scriptSupp;

        /**
         * Test object to obtain expected values and instantiate ScriptedDie objects
         * @param size the size of the die
         * @param supplier a supplier of suppliers for roll values
         */
        public TestItem(int size, Supplier<Supplier<Integer>> supplier) {
            this.size = size;
            this.scriptSupp = supplier;
        }
    }

    @DataPoints
    public static TestItem[] cases = new TestItem[]{
        new TestItem(4, () -> Arrays.asList(1, 4, 6, 2, 3).iterator()::next),
        new TestItem(5, () -> () -> 5)
    };

    /**
     * Test of getSize method, of class ScriptedDie.
     *
     * @param testCase
     */
    @Theory
    public void testGetSize(TestItem testCase) {
        ScriptedDie die = new ScriptedDie(testCase.size, testCase.scriptSupp.get());
        assertEquals(testCase.size, die.getSize());
    }

    /**
     * Test of roll method, of class ScriptedDie.
     *
     * @param testCase
     */
    @Theory
    public void testRoll(TestItem testCase) {
        ScriptedDie die = new ScriptedDie(testCase.size, testCase.scriptSupp.get());
        Supplier<Integer> supplier = testCase.scriptSupp.get();
        int expected = supplier.get();
        assertEquals(expected, die.roll());
        expected = supplier.get();
        assertEquals(expected, die.roll());
    }

    @DataPoints
    public static List[] lists = new List[]{
        Arrays.asList(2),
        Arrays.asList(4, 7, 8, 1)

    };

    /**
     * Test of of method, of class ScriptedDie.
     *
     * @param script the list of rolls to script
     */
    @Theory
    public void testOf(List<Integer> script) {
        int expected = script.get(0);
        Die die = ScriptedDie.of(script);
        assertEquals(expected, die.roll());
        if (script.size() > 1) {
            expected = script.get(1);
        }
        assertEquals(expected, die.roll());
    }

    /**
     * Test of of method, of class ScriptedDie. This test guarantees that an
     * IllegalArgumentException will be thrown if the list is empty.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testEmptyList() {
        Die die = ScriptedDie.of(new ArrayList<>());
    }
}
