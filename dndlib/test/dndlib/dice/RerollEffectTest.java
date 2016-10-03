/*
 * Think about the license.
 */
package dndlib.dice;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.IntStream;
import java.util.stream.Stream;
import static org.testng.Assert.*;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

/**
 *
 * @author emori
 */
public class RerollEffectTest {
    
    @DataProvider
    public Object[][] getRerolls() {
        return new Object[][]{
            { new HashSet<>(Arrays.asList(1, 2, 3, 4, 5)) }
        };
    }
    
    @Test(dataProvider = "getRerolls")
    public void testTest(Set<Integer> rerolls) {
        RerollEffect effect = new RerollEffect(rerolls);
        rerolls
            .stream()
            .forEach(i -> {
                Die constant = new ConstantDie(i);
                assertTrue(effect.test(constant.roll()));
            });
    }
    
    @Test(dataProvider = "getRerolls")
    public void applyTest(Set<Integer> rerolls) {
        int lower = rerolls.stream().min(Integer::compare).orElseThrow(IllegalArgumentException::new) - rerolls.size();
        int upper = rerolls.stream().max(Integer::compare).orElseThrow(IllegalArgumentException::new) + rerolls.size();
        Die die = new UniformDie(lower, upper);
        DiceEffect effect = new RerollEffect(rerolls);
        Stream<Integer> stream = IntStream
            .range(0, 1000)
            .boxed()
            .flatMap(i -> effect.apply(die));
        assertFalse(stream.anyMatch(i -> rerolls.contains(i)));
    }
    
}
