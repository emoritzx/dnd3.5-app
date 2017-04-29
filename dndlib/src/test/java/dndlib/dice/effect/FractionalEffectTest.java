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
import org.junit.Test;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 *
 * @author sldur
 */
public class FractionalEffectTest {

    /**
     * Test of apply method, of class FractionalEffect.
     */
    @Test
    public void testApply() {
        FractionalEffect effect = new FractionalEffect(0.75);
        Die die = mock(Die.class);
        when(die.roll()).thenReturn(8);
        Stream<Integer> rollStream = effect.apply(die);
        List<Integer> rolls = rollStream.collect(Collectors.toList());
        assertEquals(1, rolls.size());
        assertEquals(6, rolls.get(0).intValue());
    }
    
}
