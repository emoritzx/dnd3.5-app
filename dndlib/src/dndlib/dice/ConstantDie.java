/*
 * Think about the license.
 */
package dndlib.dice;

/**
 *
 * @author emori
 */
public class ConstantDie implements Die {

    private final int value;

    public ConstantDie(int value) {
        this.value = value;
    }

    @Override
    public int roll() {
        return value;
    }

    @Override
    public int getSize() {
        return value;
    }
    
    @Override
    public String toString() {
        return "constant " + value;
    }
}
