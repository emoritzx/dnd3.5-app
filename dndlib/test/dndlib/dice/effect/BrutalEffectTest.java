/*
 * Think about the license.
 */
package dndlib.dice.effect;

import dndlib.dice.Die;
import dndlib.dice.ScriptedDie;
import dndlib.dice.effect.DiceEffect;
import dndlib.dice.effect.BrutalEffect;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

/**
 *
 * @author emori
 */
public class BrutalEffectTest {
    
    @DataProvider
    public Object[][] getData() {
        return new Object[][] {
            { Stream.of(1, 2).collect(Collectors.toSet()), Arrays.asList(1, 2, 3) },
            { Stream.of(3).collect(Collectors.toSet()), Arrays.asList(3, 3, 3, 3, 3, 3, 4) },
        };
    }
    
    @Test(dataProvider = "getData")
    public void applyTest(Set<Integer> brutal, List<Integer> rolls) {
        DiceEffect effect = new BrutalEffect(brutal);
        Die die = ScriptedDie.of(rolls);
        List<Integer> results = effect.apply(die).collect(Collectors.toList());
        assertEquals(results.size(), rolls.size());
        IntStream.range(0, results.size())
                .forEach(i -> assertEquals(results.get(i), rolls.get(i)));
    }
    
    @Test(dataProvider = "getData")
    public void testTest(Set<Integer> brutal, List<Integer> rolls) {
        Predicate<Integer> effect = new BrutalEffect(brutal);
        brutal.stream().forEach(i -> assertTrue(effect.test(i)));
    }
}
