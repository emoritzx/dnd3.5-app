/**
 * Copyright (c) 2016-2017, Evan Moritz.
 * Licensed under the MIT License. See the accompanying LICENSE file for terms.
 */
package dndlib.core;

import java.util.function.Function;

/**
 *
 * @author emori
 */
public interface Named {
    String getName();
    String getAbbreviation();
    
    public static Named create(String name, String abbr) {
        return new Named() {
            @Override
            public String getAbbreviation() {
                return abbr;
            }

            @Override
            public String getName() {
                return name;
            }
        };
    }
    
    public static Named create(String name, Function<String, String> abbreviator) {
        return create(name, abbreviator.apply(name));
    }
}
