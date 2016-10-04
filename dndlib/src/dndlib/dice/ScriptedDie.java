/*
 * Think about the license.
 */
package dndlib.dice;

import dndlib.core.Cycle;
import java.util.Collection;
import java.util.function.Supplier;

/**
 *
 * @author emori
 */
public class ScriptedDie implements Die {

    private final int size;
    
    private final Supplier<Integer> supplier;
    
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
    
    public static Die of(Collection<Integer> rolls) {
        return new ScriptedDie(rolls.size(), Cycle.supplier(rolls));
    }
}
