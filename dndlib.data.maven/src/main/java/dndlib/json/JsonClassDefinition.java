/*
 * Think about the license.
 */
package dndlib.json;

import dndlib.character.ClassDefinition;
import dndlib.core.Enhancement;
import dndlib.dice.Die;
import dndlib.dice.MemoizedDie;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.BiFunction;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import javax.json.JsonObject;
import javax.json.JsonString;

/**
 *
 * @author emori
 */
public final class JsonClassDefinition {

    private static final String DIE_ID = "hitDie";
    private static final String ENHANCEMENT_ID = "enhancements";
    private static final String NAME_ID = "name";
    private static final String SKILLMOD_ID = "skillModifier";
    private static final String SKILLS_ID = "skills";
    
    private final List<ClassDefinition> list;

    public JsonClassDefinition(JsonObject json, BiFunction<Integer, Integer, Die> dieConverter) {
        String name = json.getString(NAME_ID);
        int skillModifier = json.getInt(SKILLMOD_ID);
        Set<String> skills = new HashSet<>(JsonList.fromStringArray(json.getJsonArray(SKILLS_ID), JsonString::getString));
        List<Enhancement> enhancements = JsonEnhancement.fromList(json.getJsonArray(ENHANCEMENT_ID));
        this.list = IntStream
            .rangeClosed(1, enhancements.size())
            .mapToObj(i -> new ClassDefinition(
                name,
                new MemoizedDie(dieConverter.apply(i, json.getInt(DIE_ID))),
                skillModifier,
                skills,
                enhancements.get(i - 1)))
            .collect(Collectors.toList());
    }
    
    public ClassDefinition getClassForLevel(int level) {
        return list.get(level - 1);
    }
}
