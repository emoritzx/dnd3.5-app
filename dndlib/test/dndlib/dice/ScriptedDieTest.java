/*
 * Think about the license.
 */
package dndlib.dice;

import dndlib.structures.Cycle;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.stream.IntStream;
import static org.testng.Assert.assertEquals;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

/**
 *
 * @author emori
 */
public class ScriptedDieTest {
    
    @DataProvider
    public Object[][] getData() {
        return new Object[][] {
                { Arrays.asList(1, 2, 3, 4, 5, 6) },
                { Collections.emptyList() },
        };
    }
    
    @Test(dataProvider = "getData")
    public void ofTest(List<Integer> list) {
        Die die = ScriptedDie.of(list);
        IntStream.range(0, list.size()).forEach(i -> assertEquals(die.roll(), (int) list.get(i)));
    }
    
    @Test(dataProvider = "getData")
    public void ofCycleTest(List<Integer> list) {
        Die die = ScriptedDie.of(list);
        IntStream.range(0, list.size() * 3)
                .forEach(i -> assertEquals(die.roll(), (int) list.get(i % list.size())));
    }
}
