/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dndlib.json;

import dndlib.character.Ability;
import dndlib.character.ClassDefinition;
import dndlib.character.Level;
import dndlib.core.Enhancement;
import java.util.List;
import java.util.Map;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.stream.IntStream;
import javax.json.JsonArray;
import javax.json.JsonObject;

/**
 * Deserializes JSON data into a Character
 *
 * Example:
 * <code>
 * {
 *     "abilities": <JsonMap:Ability>,
 *     "levels": <JsonList:(ClassDefinition, JsonList:Enhancement)>,
 *     "name": <String>,
 *     "race": <String>
 * }
 * </code>
 * 
 * @author E. Moritz
 */
public final class JsonCharacter {

    /** JSON key: abilities */
    public static final String ABILITY_ID = "abilities";

    /** JSON key: class */
    public static final String CLASS_ID = "class";

    /** JSON key: enhancements */
    public static final String ENHANCEMENT_ID = "enhancements";

    /** JSON key: levels */
    public static final String LEVEL_ID = "levels";

    /** JSON key: name */
    public static final String NAME_ID = "name";

    /** JSON key: race */
    public static final String RACE_ID = "race";

    /**
     * Deserializes a JSON object into a Character
     * 
     * @param json JSON object
     * @param raceConverter Race name to Enhancement converter
     * @param classConverter (Class name, level) to ClassDefinition converter
     * @return Character
     */
    public static dndlib.character.Character from(
        JsonObject json,
        Function<String, Enhancement> raceConverter,
        BiFunction<String, Integer, ClassDefinition> classConverter)
    {
        String name = json.getString(NAME_ID);
        Map<String, Ability> abilities = JsonAbility.fromLongForm(json.getJsonObject(ABILITY_ID));
        Enhancement race = raceConverter.apply(json.getString(RACE_ID));
        dndlib.character.Character c = new dndlib.character.Character(name, abilities, race);
        JsonArray levels = json.getJsonArray(LEVEL_ID);
        IntStream
            .rangeClosed(1, levels.size())
            .mapToObj(level -> parseLevel(level, levels.getJsonObject(level - 1), classConverter))
            .forEach(c::addLevel);
        return c;
    }

    /**
     * Deserializes a JSON object into a Level
     * 
     * @param level Level ordinal
     * @param json JSON object
     * @param classConverter (Class name, level) to ClassDefinition converter
     * @return Level
     */
    public static Level parseLevel(
        int level,
        JsonObject json, 
        BiFunction<String, Integer, ClassDefinition> classConverter)
    {
        String className = json.getString(CLASS_ID);
        ClassDefinition clazz = classConverter.apply(className, level);
        List<Enhancement> enhancements = JsonEnhancement.fromList(json.getJsonArray(ENHANCEMENT_ID));
        return Level.create(clazz, enhancements.toArray(new Enhancement[enhancements.size()]));
    }
}
