/*
 * Think about the license.
 */
package dndlib.core;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collector;
import java.util.stream.Collectors;

/**
 *
 * @author emori
 */
public class NamedHashMap<T extends Named> extends HashMap<String, T> {

    public T put(T item) {
        return put(item.getName(), item);
    }
    
    public static <T extends Named> Collector<T, ?, Map<String, T>> collector() {
        return toLinkedMap(
            named -> named.getName(),
            Function.identity()
        );
    }
    
    public static <T, K, U> Collector<T, ?, Map<K,U>> toLinkedMap(
        Function<? super T, ? extends K> keyMapper,
        Function<? super T, ? extends U> valueMapper)
    {
        return Collectors.toMap(keyMapper, valueMapper, 
                (u, v) -> {
                    throw new IllegalStateException(String.format("Duplicate key %s", u));
                },
                LinkedHashMap::new);
    }
}
