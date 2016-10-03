/*
 * Think about the license.
 */
package dndlib.dice;

import java.util.Collection;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 *
 * @author emori
 */
public class RerollEffect implements DiceEffect, Predicate<Integer> {

    private final Set<Integer> reroll;

    public RerollEffect(Collection<Integer> rolls) {
        reroll = rolls
            .stream()
            .collect(Collectors.toSet());
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
