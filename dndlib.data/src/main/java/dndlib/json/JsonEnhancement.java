/*
 * Think about the license.
 */
package dndlib.json;

import dndlib.core.Enhancement;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import javax.json.JsonArray;
import javax.json.JsonNumber;
import javax.json.JsonObject;

/**
 *
 * @author emori
 */
public class JsonEnhancement implements Enhancement{

    public static final String ABILITY_ID = "abilities";
    public static final String ARMOR_ID = "armor";
    public static final String BAB_ID = "bab";
    public static final String SAVE_ID = "saves";
    public static final String SKILL_ID = "skills";
    public static final String SLOT_ID = "slot";
    
    /**
     * 
     * @param json
     * @return 
     */
    public static List<Enhancement> fromList(JsonArray json) {
        return json == null
            ? Collections.emptyList()
            : json.stream()
                .map(JsonObject.class::cast)
                .map(JsonEnhancement::new)
                .collect(Collectors.toList());
    }
    
    private final Map<String, Integer> abilities;
    private final Map<String, Integer> armor;
    private final int bab;
    private final Map<String, Integer> saves;
    private final Map<String, Integer> skills;
    private final String slot;
    
    /**
     * 
     * @param json 
     */
    public JsonEnhancement(JsonObject json) {
        abilities = JsonMap.fromNumber(json.getJsonObject(ABILITY_ID), JsonNumber::intValue);
        armor = JsonMap.fromNumber(json.getJsonObject(ARMOR_ID), JsonNumber::intValue);
        bab = json.getInt(BAB_ID, 0);
        saves = JsonMap.fromNumber(json.getJsonObject(SAVE_ID), JsonNumber::intValue);
        skills = JsonMap.fromNumber(json.getJsonObject(SKILL_ID), JsonNumber::intValue);
        slot = json.getString(SLOT_ID, "<unslotted>");
    }

    @Override
    public Map<String, Integer> getAbilities() {
        return abilities;
    }

    @Override
    public Map<String, Integer> getArmor() {
        return armor;
    }

    @Override
    public int getBaseAttackBonus() {
        return bab;
    }

    @Override
    public Map<String, Integer> getSaves() {
        return saves;
    }

    @Override
    public Map<String, Integer> getSkills() {
        return skills;
    }

    @Override
    public String getSlot() {
        return slot;
    }
}
