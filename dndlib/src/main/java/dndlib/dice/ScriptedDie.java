/**
 * Copyright (c) 2016-2017, Evan Moritz.
 * Licensed under the MIT License. See the accompanying LICENSE file for terms.
 */
package dndlib.dice;

import dndlib.structures.Cycle;
import java.util.List;
import java.util.function.Supplier;

/**
 * An implementation of the Die interface that will return numbers in a scripted
 * cycle. The provided set of values will be returned in the order they are
 * provided, and then once all values have been used, it will cycle back to the
 * beginning if the provided set of values.
 *
 * @author emori
 */
public class ScriptedDie implements Die {

    private final int size;
    
    private final Supplier<Integer> supplier;
    
    /**
     * Constructor.
     *
     * @param size the size of the die
     * @param supplier the script of numbers to return from this die
     */
    public ScriptedDie(int size, Supplier<Integer> supplier) {
        this.supplier = supplier;
        this.size = size;
    }

    @Override
    public int getSize() {
        return size;
    }

    @Override
    public int roll() {
        return supplier.get();
    }
    
    /**
     * Convenience method for creating a ScriptedDie from a collection of integers.
     * @param rolls the values to be returned for the rolls of the die
     * @return a ScriptedDie backed by the provided script of roll values.
     */
    public static Die of(List<Integer> rolls) {
        if(rolls.isEmpty()){
            throw new IllegalArgumentException("List cannot be empty");
        }
        return new ScriptedDie(rolls.size(), Cycle.supplier(rolls));
    }
}
