/*
 * Think about the license.
 */
package dndlib.dice;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 *
 * @author emori
 */
public class BrutalEffect implements DiceEffect, Predicate<Integer> {

    private final Set<Integer> brutal;

    public BrutalEffect(Collection<Integer> rolls) {
        this.brutal = rolls.stream()
            .collect(Collectors.toSet());
    }
    
    @Override
    public Stream<Integer> apply(Die die) {
        List<Integer> rolls = new ArrayList<>();
        int roll;
        do
        {
            roll = die.roll();
            rolls.add(roll);
        } while (test(roll));
        return rolls.stream();
    }

    @Override
    public boolean test(Integer roll) {
        return brutal.contains(roll);
    }
    
}
