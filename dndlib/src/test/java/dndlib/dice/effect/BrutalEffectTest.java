/**
 * Copyright (c) 2016-2017, Evan Moritz.
 * Licensed under the MIT License. See the accompanying LICENSE file for terms.
 */
package dndlib.dice.effect;

import dndlib.dice.Die;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.junit.experimental.theories.DataPoints;
import org.junit.experimental.theories.Theory;
import static org.mockito.Mockito.when;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.Test;
import org.junit.experimental.theories.Theories;
import org.junit.runner.RunWith;
import static org.mockito.Mockito.mock;

/**
 * Test cases for the BrutalEffect class
 *
 * @author sldur
 */
@RunWith(Theories.class)
public class BrutalEffectTest {

    @DataPoints
    public static Set[] sets = new Set[]{
        new HashSet(Arrays.asList(1)),
        new HashSet(Arrays.asList(1, 6)),
        new HashSet(Arrays.asList(3, 4, 5))
    };

    /**
     * Test of apply method, of class BrutalEffect.
     * Covers Constructor def-use path [1,2]
     * @param rollSet the set of rolls that will cause an additional roll
     */
    @Theory
    public void testApplyTrue(Set<Integer> rollSet) {
        BrutalEffect effect = new BrutalEffect(rollSet);
        Die die = mock(Die.class);
        List<Integer> rollList = new ArrayList<>(rollSet);
        int first = rollList.get(0);
        int last = rollList.get(rollList.size() - 1);
        int middle = rollList.get(Math.floorDiv(rollList.size(), 2));
        when(die.roll()).thenReturn(last, first, middle, 7);

        Stream<Integer> stream = effect.apply(die);
        List<Integer> rolls = stream.collect(Collectors.toList());
        assertEquals(4, rolls.size());
        assertEquals(last, rolls.get(0).intValue());
        assertEquals(first, rolls.get(1).intValue());
        assertEquals(middle, rolls.get(2).intValue());
        assertEquals(7, rolls.get(3).intValue());
    }

    /**
     * Test of apply method, of class BrutalEffect.
     * Covers the apply def-use path [1,2,3,4]
     * and Constructor def-use path [1,2]
     */
    @Test
    public void testApplyFalse() {
        BrutalEffect effect = new BrutalEffect(new HashSet());
        Die die = mock(Die.class);
        when(die.roll()).thenReturn(1);

        Stream<Integer> stream = effect.apply(die);
        List<Integer> rolls = stream.collect(Collectors.toList());
        assertEquals(1, rolls.size());
        assertEquals(1, rolls.get(0).intValue());
    }

    /**
     * Test of test method, of class BrutalEffect. All expected values will be
     * true.
     * Covers the test def-use path [1,2]
     *
     * @param rollSet the set of rolls that will return true
     */
    @Theory
    public void testTestTrue(Set<Integer> rollSet) {
        BrutalEffect effect = new BrutalEffect(rollSet);
        rollSet.stream().forEach((effectValue) -> {
            assertTrue(effect.test(effectValue));
        });
    }

    /**
     * Test of test method, of class BrutalEffect. All expected values will be
     * true.
     *
     * @param rollSet the set of rolls that will return true
     */
    @Theory
    public void testTestFalse(Set<Integer> rollSet) {
        BrutalEffect effect = new BrutalEffect(rollSet);
        Arrays.asList(0, 7, 12, 2).stream().forEach((roll) -> {
            assertFalse(effect.test(roll));
        });
    }
}
