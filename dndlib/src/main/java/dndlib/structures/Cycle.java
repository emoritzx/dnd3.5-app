/**
 * Copyright (c) 2016-2017, Evan Moritz.
 * Licensed under the MIT License. See the accompanying LICENSE file for terms.
 */
package dndlib.structures;

import java.util.Collection;
import java.util.Iterator;
import java.util.function.Supplier;

/**
 *
 * @author emori
 */
public final class Cycle {
    
    private Cycle() throws InstantiationException {
        throw new InstantiationException();
    }
    
    public static <T> Iterator<T> iterator(Collection<T> collection) {
        return new Iterator<T>() {

            private Iterator<T> iterator = collection.iterator();
            
            @Override
            public boolean hasNext() {
                return !collection.isEmpty();
            }

            @Override
            public T next() {
                if (!iterator.hasNext()) {
                    iterator = collection.iterator();
                }
                return iterator.next();
            }
        };
    }
    
    public static <T> Supplier<T> supplier(Collection<T> collection) {
        return iterator(collection)::next;
    }
}
