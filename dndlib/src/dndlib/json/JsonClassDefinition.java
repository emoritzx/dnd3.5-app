/*
 * Think about the license.
 */
package dndlib.json;

import dndlib.character.ClassDefinition;
import dndlib.dice.ConstantDie;
import dndlib.dice.Die;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
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

    public static List<ClassDefinition> from(JsonObject json) {
        String name = json.getString(NAME_ID);
        Die die = new ConstantDie(json.getJsonNumber(DIE_ID).intValue());
        int skillModifier = json.getJsonNumber(SKILLMOD_ID).intValue();
        Set<String> skills = new HashSet<>(JsonList.fromStringArray(json.getJsonArray(SKILLS_ID), JsonString::getString));
        return JsonEnhancement.fromList(json.getJsonArray(ENHANCEMENT_ID))
            .stream()
            .map(enhancement -> new ClassDefinition(name, die, skillModifier, skills, enhancement))
            .collect(Collectors.toList());
    }

}
