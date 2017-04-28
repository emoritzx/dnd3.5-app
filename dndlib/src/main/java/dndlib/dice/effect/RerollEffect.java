/**
 * Copyright (c) 2016-2017, Evan Moritz.
 * Licensed under the MIT License. See the accompanying LICENSE file for terms.
 */
package dndlib.dice.effect;

import dndlib.dice.Die;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Stream;

/**
 * An implementation of the DiceEffect interface to model a reroll effect. A
 * reroll effect will cause a reroll if a roll value is contained within the set
 * of specified roll values. 
 *
 * @author emori
 */
public class RerollEffect implements DiceEffect, Predicate<Integer> {

    private final Set<Integer> reroll;

    /**
     * Constructor.
     * @param rolls the set of roll values that will cause a reroll 
     */
    public RerollEffect(Set<Integer> rolls) {
        reroll = rolls;
    }

    /**
     * Rerolls if a roll value is contained within the set
     * of specified roll values. Only the final roll value will be returned.
     * @param die the die to roll
     * @return the final roll value
     */
    @Override
    public Stream<Integer> apply(Die die) {
        int roll;
        do {
            roll = die.roll();
        } while (test(roll));
        return Stream.of(roll);
    }

    @Override
    public boolean test(Integer roll) {
        return reroll.contains(roll);
    }

}
