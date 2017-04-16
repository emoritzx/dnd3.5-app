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
 *
 * @author emori
 */
public class ExplodingEffect implements DiceEffect, BiPredicate<Die, Integer> {

    @Override
    public Stream<Integer> apply(Die die) {
        List<Integer> rolls = new ArrayList<>();
        int roll;
        do
        {
            roll = die.roll();
            rolls.add(roll);
        } while (test(die, roll));
        return rolls.stream();
    }

    @Override
    public boolean test(Die die, Integer roll) {
        return roll == die.getSize();
    }
    
}
