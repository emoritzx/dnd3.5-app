/*
 * Think about the license.
 */
package dndlib.dice.effect;

import dndlib.dice.Die;
import java.util.function.Function;
import java.util.stream.Stream;

/**
 *
 * @author emori
 */
public interface DiceEffect extends Function<Die, Stream<Integer>>
{
    // woot
}
