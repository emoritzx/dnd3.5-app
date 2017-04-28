/*
 * Think about the license.
 */
package dndlib.dice.effect;

import dndlib.dice.Die;
import java.util.stream.Stream;

/**
 * An implementation of the DiceEffect interface to model a fractional effect. A
 * fractional effect adjusts the actual roll value by multiplying the value by
 * the provided fraction. This is only ever a single roll.
 *
 * @author emori
 */
public class FractionalEffect implements DiceEffect {

    private final double fraction;

    /**
     * Constructor.
     *
     * @param fraction the fraction to multiply the roll value by
     */
    public FractionalEffect(double fraction) {
        this.fraction = fraction;
    }

    @Override
    public Stream<Integer> apply(Die t) {
        return Stream.of((int) (t.roll() * fraction));
    }
}
