/*
 * Think about the license.
 */
package dndlib.json;

import dndlib.core.NamedHashMap;
import java.util.Collections;
import java.util.Map;
import java.util.function.BiFunction;
import java.util.function.Function;
import javax.json.JsonNumber;
import javax.json.JsonObject;
import javax.json.JsonValue;

/**
 *
 * @author emori
 */
public class JsonMap {
    
    public static <T, J extends JsonValue> Map<String, T> from(JsonObject json, BiFunction<String, J, T> converter) {
        return json == null
            ? Collections.emptyMap()
            : json
                .entrySet()
                .stream()
                .collect(NamedHashMap.toLinkedMap(
                    entry -> entry.getKey(),
                    entry -> converter.apply(entry.getKey(), (J) entry.getValue())
                ));
    }
    
    public static <T> Map<String, T> fromNumber(JsonObject json, Function<JsonNumber, T> converter) {
        return from(
            json,
            (String name, JsonNumber number) -> converter.apply(number)
        );
    }
    
    public static <T> Map<String, T> fromNumber(JsonObject json, BiFunction<String, JsonNumber, T> converter) {
        return from(json, converter);
    }
}
