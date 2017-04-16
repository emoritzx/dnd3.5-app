/*
 * Think about the license.
 */
package dndlib.dice;

import java.util.concurrent.ThreadLocalRandom;

/**
 *
 * @author emori
 */
public class UniformDie extends ScriptedDie {

    public UniformDie(int lower, int upper) {
        super(
            upper - lower + 1,
            () -> ThreadLocalRandom.current().nextInt(lower, upper + 1)
        );
        if (lower > upper) {
            throw new IllegalArgumentException(String.format("%d > %d", lower, upper));
        }
    }
}
