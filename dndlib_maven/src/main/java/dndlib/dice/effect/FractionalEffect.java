/*
 * Think about the license.
 */
package dndlib.dice.effect;

import dndlib.dice.Die;
import java.util.stream.Stream;

/**
 *
 * @author emori
 */
public class FractionalEffect implements DiceEffect {

    private final double fraction;

    public FractionalEffect(double fraction) {
        this.fraction = fraction;
    }
    
    @Override
    public Stream<Integer> apply(Die t) {
        return Stream.of((int)(t.roll() * fraction));
    }
}
