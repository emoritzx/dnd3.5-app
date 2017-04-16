/*
 * Think about the license.
 */
package dndlib.dice;

/**
 *
 * @author emori
 */
public class MemoizedDie implements Die {

    private final int size;
    private final int value;

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
