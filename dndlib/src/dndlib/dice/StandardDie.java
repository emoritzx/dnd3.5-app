/*
 * Think about the license.
 */
package dndlib.dice;

/**
 *
 * @author emori
 */
public class StandardDie extends UniformDie {
    
    public StandardDie(int sides) {
        super(1, sides);
    }

    @Override
    public String toString() {
        return "d" + getUpper();
    }
}
