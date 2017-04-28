/**
 * Copyright (c) 2016-2017, Evan Moritz.
 * Licensed under the MIT License. See the accompanying LICENSE file for terms.
 */
package dndlib.json;

import dndlib.core.NamedHashMap;
import java.util.Collections;
import java.util.Map;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.stream.Collectors;
import javax.json.JsonNumber;
import javax.json.JsonObject;
import javax.json.JsonValue;

/**
 * Deserializes JSON data in the form of homogenous single-level maps
 * 
 * Example:
 * <code>
 * {
 *     "key1": value1,
 *     "key2": value2,
 *     "key3": value3
 * }
 * </code>
 * 
 * @author E. Moritz
 */
public final class JsonMap {

    /**
     * Deserialize a JSON map with the given converter function
     * 
     * For every key in the object, the converter function is called on the string representation of the value
     * and converted into the value type. The (key, value) pairs are collected into a Map.
     * The key is included as part of the converter function.
     * 
     * @param <T> Value type
     * @param <J> JSON data type
     * @param json JSON object
     * @param converter (String, JsonValue) to value type converter
     * @return Map from JSON key to value
     */
    public static <T, J extends JsonValue> Map<String, T> from(JsonObject json, BiFunction<String, J, T> converter) {
        return json == null
            ? Collections.emptyMap()
            : json
                .entrySet()
                .stream()
                .collect(Collectors.collectingAndThen(
                    NamedHashMap.toLinkedMap(
                        entry -> entry.getKey(),
                        entry -> converter.apply(entry.getKey(), (J) entry.getValue())),
                    Collections::unmodifiableMap));
    }

    /**
     * Deserialize a JSON map of numbers
     * 
     * This is a specialization of JsonMap.from() for the JsonNumber type.
     * 
     * @param <T> Value type
     * @param json JSON object
     * @param converter JsonNumber to value type converter
     * @return Map from JSON key to value
     */
    public static <T> Map<String, T> fromNumber(JsonObject json, Function<JsonNumber, T> converter) {
        return from(
            json,
            (String name, JsonNumber number) -> converter.apply(number)
        );
    }

    /**
     * Deserialize a JSON map of numbers
     * 
     * This is a specialization of JsonMap.from() for the JsonNumber type.
     * The key is included as part of the converter function.
     * 
     * @param <T> Value type
     * @param json JSON object
     * @param converter (String, JsonNumber) to value type converter
     * @return Map from JSON key to value
     */
    public static <T> Map<String, T> fromNumber(JsonObject json, BiFunction<String, JsonNumber, T> converter) {
        return from(json, converter);
    }
}
