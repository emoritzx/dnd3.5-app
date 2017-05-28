/**
 * Copyright (c) 2016-2017, Evan Moritz.
 * Licensed under the MIT License. See the accompanying LICENSE file for terms.
 */
package dndlib.character;

/**
 *
 * @author emori
 */
public class SpellRangeCalculation {

    public static final int CLOSE_BASE = 25;
    public static final int CLOSE_MOD = 5;
    public static final int MEDIUM_BASE = 100;
    public static final int MEDIUM_MOD = 10;
    public static final int LONG_BASE = 400;
    public static final int LONG_MOD = 40;

    public static int calculate(int base, int modifier, int level) {
        return base + (modifier * level);
    }
}
