/*
 * Think about the license.
 */
package dndlib.dice;

import java.util.concurrent.ThreadLocalRandom;

/**
 *
 * @author emori
 */
public class UniformDie implements Die {

    private final int lower;
    private final int upper;

    public UniformDie(int lower, int upper) {
        if (lower > upper) {
            throw new IllegalArgumentException(String.format("%d > %d", lower, upper));
        }
        this.lower = lower;
        this.upper = upper;
    }
    
    public int getLower() {
        return lower;
    }
    
    public int getUpper() {
        return upper;
    }

    @Override
    public int getSize() {
        return upper - lower;
    }
    
    @Override
    public int roll() {
        return ThreadLocalRandom.current().nextInt(lower, upper + 1);
    }
    
}
