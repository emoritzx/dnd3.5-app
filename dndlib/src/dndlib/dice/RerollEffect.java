/*
 * Think about the license.
 */
package dndlib.dice;

import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Stream;

/**
 *
 * @author emori
 */
public class RerollEffect implements DiceEffect, Predicate<Integer> {

    private final Set<Integer> reroll;

    public RerollEffect(Set<Integer> rolls) {
        reroll = rolls;
    }
    
    @Override
    public Stream<Integer> apply(Die die) {
        int roll;
        do
        {
            roll = die.roll();
        } while (test(roll));
        return Stream.of(roll);
    }

    @Override
    public boolean test(Integer roll) {
        return reroll.contains(roll);
    }
    
}
