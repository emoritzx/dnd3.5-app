/*
 * Think about the license.
 */
package dndlib.json;

import java.util.Collections;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import javax.json.JsonArray;
import javax.json.JsonString;
import javax.json.JsonValue;

/**
 *
 * @author emori
 */
public class JsonList {
    /**
     * 
     * @param <T>
     * @param <J>
     * @param json
     * @param converter
     * @return 
     */
    public static <T, J extends JsonValue> List<T> fromArray(JsonArray json, Function<J, T> converter) {
        return json == null
            ? Collections.emptyList()
            : json
                .stream()
                .map(value -> converter.apply((J) value))
                .collect(Collectors.toList());
    }
    
    /**
     * 
     * @param <T>
     * @param json
     * @param converter
     * @return 
     */
    public static <T> List<T> fromStringArray(JsonArray json, Function<JsonString, T> converter) {
        return fromArray(json, converter);
    }
}
