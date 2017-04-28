package dndlib.dice;

import static org.junit.Assert.assertEquals;
import org.junit.experimental.theories.DataPoints;
import org.junit.experimental.theories.Theories;
import org.junit.experimental.theories.Theory;
import org.junit.runner.RunWith;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Test cases for the Memoized die class.
 *
 * @author sldur
 */
@RunWith(Theories.class)
public class MemoizedDieTest {

    @DataPoints
    public static int[] values = {15, 4, 7, 0};

    /**
     * Test of getSize method, of class MemoizedDie.
     *
     * @param size the size for the die that will be used to instantiate the
     * MemoizedDie
     */
    @Theory
    public void testGetSize(int size) {
        Die die = mock(Die.class);
        when(die.getSize()).thenReturn(size);
        MemoizedDie memoizedDie = new MemoizedDie(die);
        assertEquals(size, memoizedDie.getSize());
    }

    /**
     * Test of roll method, of class MemoizedDie.
     *
     * @param rollValue the value of the roll for the die that will be used to
     * instantiate the MemoizedDie
     */
    @Theory
    public void testRoll(int rollValue) {
        Die die = mock(Die.class);
        when(die.roll()).thenReturn(rollValue);
        MemoizedDie memoizedDie = new MemoizedDie(die);
        assertEquals(rollValue, memoizedDie.roll());
    }

}
