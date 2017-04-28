/*
 * Think about the license.
 */
package dndlib.json;

import dndlib.character.ClassDefinition;
import dndlib.core.Enhancement;
import dndlib.dice.Die;
import dndlib.dice.MemoizedDie;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.BiFunction;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import javax.json.JsonObject;
import javax.json.JsonString;

/**
 * Deserializes a JSON object into a list of ClassDefinitions by level
 * 
 * Example:
 * <code>
 * {
 *     "hitDie": <Integer>,
 *     "enhancements": <JsonList:Enhancement>,
 *     "name": <String>,
 *     "skillModifier": <Integer>,
 *     "skills": <JsonList:String>
 * }
 * </code>
 * 
 * @author E. Moritz
 */
public final class JsonClassDefinition {

    /** JSON key: hitDie */
    public static final String DIE_ID = "hitDie";

    /** JSON key: enhancements */
    public static final String ENHANCEMENT_ID = "enhancements";

    /** JSON key: name */
    public static final String NAME_ID = "name";

    /** JSON key: skillModifier */
    public static final String SKILLMOD_ID = "skillModifier";

    /** JSON key: skills */
    public static final String SKILLS_ID = "skills";
    
    // private member fields
    private final List<ClassDefinition> list;

    /**
     * Constructor
     * 
     * Parses the values from the JSON object
     * 
     * @param json JSON object
     * @param dieConverter (level, hitDie) to Die converter
     */
    public JsonClassDefinition(JsonObject json, BiFunction<Integer, Integer, Die> dieConverter) {
        String name = json.getString(NAME_ID);
        int skillModifier = json.getInt(SKILLMOD_ID);
        Set<String> skills = Collections.unmodifiableSet(new HashSet<>(JsonList.fromStringArray(json.getJsonArray(SKILLS_ID), JsonString::getString)));
        List<Enhancement> enhancements = JsonEnhancement.fromList(json.getJsonArray(ENHANCEMENT_ID));
        list = IntStream
            .rangeClosed(1, enhancements.size())
            .mapToObj(i -> new ClassDefinition(
                name,
                new MemoizedDie(dieConverter.apply(i, json.getInt(DIE_ID))),
                skillModifier,
                skills,
                enhancements.get(i - 1)))
            .collect(Collectors.collectingAndThen(
                Collectors.toList(),
                Collections::unmodifiableList));
    }

    /**
     * Get class definition for level
     * 
     * @param level 1-indexed level ordinal
     * @return Class definition for level
     * @throws java.lang.IndexOutOfBoundsException if level is outside the range (1, size())
     */
    public ClassDefinition getClassForLevel(int level) {
        return list.get(level - 1);
    }

    /**
     * Get number of available level definitions for class
     * @return Class definition level size
     */
    public int size() {
        return list.size();
    }
}
