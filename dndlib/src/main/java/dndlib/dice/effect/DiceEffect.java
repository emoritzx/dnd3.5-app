/**
 * Copyright (c) 2016-2017, Evan Moritz.
 * Licensed under the MIT License. See the accompanying LICENSE file for terms.
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
