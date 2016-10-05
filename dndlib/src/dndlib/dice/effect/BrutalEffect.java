/*
 * Think about the license.
 */
package dndlib.dice.effect;

import dndlib.dice.Die;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Stream;

/**
 *
 * @author emori
 */
public class BrutalEffect implements DiceEffect, Predicate<Integer> {

    private final Set<Integer> brutal;

    public BrutalEffect(Set<Integer> rolls) {
        this.brutal = rolls;
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