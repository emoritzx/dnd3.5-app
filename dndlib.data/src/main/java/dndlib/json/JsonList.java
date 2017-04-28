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
 * Deserializes JSON data in the form of homogenous single-level arrays
 * 
 * Example:
 * <code>
 * [
 *     value1,
 *     value2,
 *     value3
 * ]
 * </code>
 * 
 * @author E. Moritz
 */

public final class JsonList {

    /**
     * Deserialize a JSON array with the given converter function
     * 
     * @param <T> Value type
     * @param <J> JSON data type
     * @param json JSON array
     * @param converter JsonValue to value type converter
     * @return List of values
     */
    public static <T, J extends JsonValue> List<T> fromArray(JsonArray json, Function<J, T> converter) {
        return json == null
            ? Collections.emptyList()
            : json
                .stream()
                .map(value -> converter.apply((J) value))
                .collect(Collectors.collectingAndThen(
                    Collectors.toList(),
                    Collections::unmodifiableList));
    }

    /**
     * Deserialize a JSON array of strings with the given converter function
     * 
     * This is a specialization of JsonList.fromArray() for the JsonString type.
     * 
     * @param <T> Value type
     * @param json JSON array
     * @param converter JsonString to value converter
     * @return List of values
     */
    public static <T> List<T> fromStringArray(JsonArray json, Function<JsonString, T> converter) {
        return fromArray(json, converter);
    }
}
