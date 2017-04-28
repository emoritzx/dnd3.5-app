/**
 * Copyright (c) 2016-2017, Evan Moritz.
 * Licensed under the MIT License. See the accompanying LICENSE file for terms.
 */
package dndlib.character;

import dndlib.core.Enhancement;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author emori
 */
public interface Level {

    public ClassDefinition getClassDefinition();
    public List<Enhancement> getEnhancements();
    
    public static Level create(ClassDefinition clazz, Enhancement ... enhancements) {
        List<Enhancement> list = Arrays.asList(enhancements);
        
        return new Level() {
            
            @Override
            public ClassDefinition getClassDefinition() {
                return clazz;
            }

            @Override
            public List<Enhancement> getEnhancements() {
                return list;
            }
        };
    }
}
