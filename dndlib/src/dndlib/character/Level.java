/*
 * Think about the license.
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
