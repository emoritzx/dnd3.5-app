/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dndlib.json;

import dndlib.character.Ability;
import dndlib.core.Named;
import dndlib.core.NamedHashMap;
import java.util.Collections;
import java.util.Map;
import java.util.stream.Collectors;
import javax.json.JsonArray;
import javax.json.JsonObject;

/**
 * Deserializes JSON data into a map of abilities
 * 
 * Short form:
 * <code>
 * [
 *     "ability1",
 *     "ability2",
 *     "ability3"
 * ]
 * </code>
 * 
 * Long form:
 * <code>
 * {
 *     "ability1": value1,
 *     "ability2": value2,
 *     "ability3": value3
 * }
 * </code>
 * 
 * @author E. Moritz
 */
public final class JsonAbility {

    /**
     * Deserializes a JsonArray into a map of abilities
     * 
     * Calls the default Ability constructor (which initializes the Ability with a default value)
     * 
     * @param json JSON array
     * @return Map of ability name to Ability
     */
    public static Map<String, Ability> fromShortForm(JsonArray json) {
        return JsonList
            .fromStringArray(
                json,
                name -> new Ability(Named.create(name.getString(), Ability::abbreviate)))
            .stream()
            .collect(Collectors.collectingAndThen(
                NamedHashMap.collector(),
                Collections::unmodifiableMap));
    }
    

    /**
     * Deserializes a JsonObject into a map of abilities
     * 
     * @param json JSON object
     * @return Map of ability name to Ability
     */
    public static Map<String, Ability> fromLongForm(JsonObject json) {
        return JsonMap
            .fromNumber(
                json,
                (name, number) -> new Ability(
                    Named.create(name, Ability::abbreviate),
                    number.intValue()));
    }
}
