/*
 * Think about the license.
 */
package dndlib.dice.effect;

import dndlib.dice.ConstantDie;
import dndlib.dice.Die;
import dndlib.dice.effect.FractionalEffect;
import dndlib.dice.effect.DiceEffect;
import java.util.List;
import java.util.stream.Collectors;
import static org.testng.Assert.assertEquals;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

/**
 *
 * @author emori
 */
public class FractionalEffectTest {
    
    @DataProvider
    public Object[][] getData() {
        return new Object[][] {
            {100, 0.75, 75},
            {0, 0.5, 0},
            {1, 0.49, 0},
            {1, 0.99, 0},
            {2, 0.51, 1},
            {2, 2.0, 4},
            {100, -0.5, -50},
            {-100, 0.67, -67}
        };
    }
    
    @Test(dataProvider = "getData")
    public void testTest(int size, double fraction, int expected) {
        Die die = new ConstantDie(size);
        DiceEffect effect = new FractionalEffect(fraction);
        List<Integer> result = effect.apply(die).collect(Collectors.toList());
        assertEquals(result.size(), 1);
        int actual = result.get(0);
        assertEquals(actual, expected);
    }
}
