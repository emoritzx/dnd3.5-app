/*
 * Think about the license.
 */
package dndlib.dice;

import java.util.function.BiFunction;
import java.util.function.Function;

/**
 *
 * @author emori
 */
public class ConstantDie extends ScriptedDie {

    public ConstantDie(int value) {
        super(value, () -> value);
    }
    
    @Override
    public String toString() {
        return "constant " + getSize();
    }
    
    public static BiFunction<Integer, Integer, Die> levelAdjustment(Function<Integer, Integer> func) {
        return (level, size) -> 
            level == 1
                ? new ConstantDie(size)
                : new ConstantDie(func.apply(size));
    }
}
