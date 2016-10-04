/*
 * Think about the license.
 */
package dndlib.dice;

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
}
