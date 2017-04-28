/*
 * Think about the license.
 */
package dndlib.dice;

/**
 * A MemoizedDie is initialized with a single roll of a provided Die. All future
 * rolls of this die will return the same result as the initial roll of the
 * input Die.
 *
 * @author emori
 */
public class MemoizedDie implements Die {

    private final int size;
    private final int value;

    /**
     * Constructor.
     * @param die the input die 
     */
    public MemoizedDie(Die die) {
        this.size = die.getSize();
        this.value = die.roll();
    }
    
    @Override
    public int getSize() {
        return size;
    }

    @Override
    public int roll() {
        return value;
    }
}
