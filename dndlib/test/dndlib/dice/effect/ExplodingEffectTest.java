/*
 * Think about the license.
 */
package dndlib.dice.effect;

import dndlib.structures.Cycle;
import dndlib.dice.ConstantDie;
import dndlib.dice.Die;
import dndlib.dice.ScriptedDie;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

/**
 *
 * @author emori
 */
public class ExplodingEffectTest {
    
    @DataProvider
    public Object[][] getData() {
        return new Object[][] {
            { 6, Arrays.asList(6, 1) }
        };
    }
    
    @Test(dataProvider = "getData")
    public void testTest(int size, List<Integer> rolls) {
        Die die = new ConstantDie(size);
        ExplodingEffect effect = new ExplodingEffect();
        assertTrue(effect.test(die, size));
    }
    
    @Test(dataProvider = "getData")
    public void applyTest(int size, List<Integer> rolls) {
        Die die = new ScriptedDie(size, Cycle.supplier(rolls));
        DiceEffect effect = new ExplodingEffect();
        List<Integer> list = effect.apply(die).collect(Collectors.toList());
        IntStream.range(0, rolls.size()).forEach(i -> assertEquals(list.get(i), rolls.get(i)));
    }
}
