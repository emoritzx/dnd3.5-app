/*
 * Think about the license.
 */
package dndlib.dice.effect;

import dndlib.dice.Die;
import java.util.ArrayList;
import java.util.List;
import java.util.function.BiPredicate;
import java.util.stream.Stream;

/**
 * An implementation of the DiceEffect interface that represents an exploding
 * effect. When the maximum value of the die is rolled, an additional die roll
 * will occur.
 *
 * @author emori
 */
public class ExplodingEffect implements DiceEffect, BiPredicate<Die, Integer> {

    @Override
    public Stream<Integer> apply(Die die) {
        List<Integer> rolls = new ArrayList<>();
        int roll;
        do {
            roll = die.roll();
            rolls.add(roll);
        } while (test(die, roll));
        return rolls.stream();
    }

    /**
     * Tests whether the provided value is the maximum value of the die.
     *
     * @param die the die rolled
     * @param roll the current roll value
     * @return boolean indicating whether the current roll is the maximum value
     * of the die
     */
    @Override
    public boolean test(Die die, Integer roll) {
        return roll == die.getSize();
    }

}
