/*
 * Think about the license.
 */
package dndlib.dice;

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
