/*
 * Think about the license.
 */
package dndlib.dice.effect;

import dndlib.dice.Die;
import java.util.function.Function;
import java.util.stream.Stream;

/**
 * A special effect that happens to a die. 
 * @author emori
 */
public interface DiceEffect extends Function<Die, Stream<Integer>>
{
    // woot
}
