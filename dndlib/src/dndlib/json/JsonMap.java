/*
 * Think about the license.
 */
package dndlib.json;

import java.util.Collections;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import javax.json.JsonNumber;
import javax.json.JsonObject;
import javax.json.JsonValue;

/**
 *
 * @author emori
 */
public class JsonMap {
    
    public static <T, J extends JsonValue> Map<String, T> from(JsonObject json, Function<J, T> converter) {
        return json == null
            ? Collections.emptyMap()
            : json
                .entrySet()
                .stream()
                .collect(Collectors.toMap(
                    entry -> entry.getKey(),
                    entry -> converter.apply((J) entry.getValue())
                ));
    }
    
    public static <T> Map<String, T> fromNumber(JsonObject json, Function<JsonNumber, T> converter) {
        return from(json, converter);
    }
}
