/*
 * Think about the license.
 */
package dndlib.core;

import java.util.Collection;
import java.util.Iterator;
import java.util.function.Supplier;

/**
 *
 * @author emori
 */
public class Cycle {
    
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
