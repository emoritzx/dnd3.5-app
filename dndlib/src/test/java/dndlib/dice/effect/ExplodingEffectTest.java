/**
 * Copyright (c) 2016-2017, Evan Moritz.
 * Licensed under the MIT License. See the accompanying LICENSE file for terms.
 */
package dndlib.dice.effect;

import dndlib.dice.Die;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.Test;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Test cases for the ExplodingEffect class
 * @author sldur
 */
public class ExplodingEffectTest {

    private ExplodingEffect effect = new ExplodingEffect();
    /**
     * Test of apply method, of class ExplodingEffect.
     * Covers apply def-use path [1,2,3,4]
     */
    @Test
    public void testApplySingleRoll() {
        Die die = mock(Die.class);
        when(die.getSize()).thenReturn(4);
        when(die.roll()).thenReturn(3);
        Stream<Integer> rollStream = effect.apply(die);
        List<Integer> rolls = rollStream.collect(Collectors.toList());
        assertEquals(1, rolls.size());
        assertEquals(3, rolls.get(0).intValue());
    }
    
    /**
     * Test of apply method, of class ExplodingEffect.
     */
    @Test
    public void testApplyMultiRoll() {
        Die die = mock(Die.class);
        when(die.getSize()).thenReturn(4);
        when(die.roll()).thenReturn(4,5);
        Stream<Integer> rollStream = effect.apply(die);
        List<Integer> rolls = rollStream.collect(Collectors.toList());
        assertEquals(2, rolls.size());
        assertEquals(4, rolls.get(0).intValue());
        assertEquals(5, rolls.get(1).intValue());
    }

    /**
     * Test of test method, of class ExplodingEffect.
     * Covers test def-use path [1,2]
     */
    @Test
    public void testTest() {
       Die die = mock(Die.class);
       when(die.getSize()).thenReturn(12);
       assertTrue(effect.test(die, 12));
       assertFalse(effect.test(die, 11));
    }
    
}
