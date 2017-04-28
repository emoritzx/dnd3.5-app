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
 * An implementation of the DiceEffect interface that represents a brutal
 * effect. When a number within the specified set is rolled, an additional die
 * roll will occur.
 *
 * @author emori
 */
public class BrutalEffect implements DiceEffect, Predicate<Integer> {

    /**
     * the set of die values that will cause an additional roll
     */
    private final Set<Integer> brutal;

    /**
     * Constructor.
     *
     * @param rolls the set of die values that will cause an additional roll
     */
    public BrutalEffect(Set<Integer> rolls) {
        this.brutal = rolls;
    }

    @Override
    public Stream<Integer> apply(Die die) {
        List<Integer> rolls = new ArrayList<>();
        int roll;
        do {
            roll = die.roll();
            rolls.add(roll);
        } while (test(roll));
        return rolls.stream();
    }

    /**
     * Tests whether the provided value is contained within the set of values
     * that will cause an additional roll.
     *
     * @param roll the current roll value
     * @return boolean indicating whether the current roll is contained in the
     * set of values that cause an additional roll
     */
    @Override
    public boolean test(Integer roll) {
        return brutal.contains(roll);
    }

}
