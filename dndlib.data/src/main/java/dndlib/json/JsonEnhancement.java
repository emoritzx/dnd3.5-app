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
 * Deserializes JSON data into an Enhancement object
 * 
 * Example:
 * <code>
 * {
 *     "abilities": <JsonMap:Integer>,
 *     "armor": <JsonMap:Integer>,
 *     "bab": <Integer>,
 *     "saves": <JsonMap:Integer>,
 *     "skills": <JsonMap:Integer>,
 *     "slot": <String>
 * }
 * </code>
 * 
 * @author E. Moritz
 */
public final class JsonEnhancement implements Enhancement {

    /** JSON key: abilities */
    public static final String ABILITY_ID = "abilities";

    /** JSON key: armor */
    public static final String ARMOR_ID = "armor";

    /** JSON key: bab */
    public static final String BAB_ID = "bab";

    /** JSON key: saves */
    public static final String SAVE_ID = "saves";

    /** JSON key: skills */
    public static final String SKILL_ID = "skills";

    /** JSON key: slot */
    public static final String SLOT_ID = "slot";

    /**
     * Deserialize a JsonArray of Enhancements
     * 
     * @param json JSON array
     * @return List of enhancements
     */
    public static List<Enhancement> fromList(JsonArray json) {
        return json == null
            ? Collections.emptyList()
            : json.stream()
                .map(JsonObject.class::cast)
                .map(JsonEnhancement::new)
                .collect(Collectors.toList());
    }

    // private member fields
    private final Map<String, Integer> abilities;
    private final Map<String, Integer> armor;
    private final int bab;
    private final Map<String, Integer> saves;
    private final Map<String, Integer> skills;
    private final String slot;
    

    /**
     * Constructor
     * 
     * Parses the values from the JSON object
     * 
     * @param json JSON object
     */
    public JsonEnhancement(JsonObject json) {
        abilities = JsonMap.fromNumber(json.getJsonObject(ABILITY_ID), JsonNumber::intValue);
        armor = JsonMap.fromNumber(json.getJsonObject(ARMOR_ID), JsonNumber::intValue);
        bab = json.getInt(BAB_ID, 0);
        saves = JsonMap.fromNumber(json.getJsonObject(SAVE_ID), JsonNumber::intValue);
        skills = JsonMap.fromNumber(json.getJsonObject(SKILL_ID), JsonNumber::intValue);
        slot = json.getString(SLOT_ID, "<unslotted>");
    }

    /**
     * Get map of ability name to value
     * 
     * @return Ability map
     */
    @Override
    public Map<String, Integer> getAbilities() {
        return abilities;
    }

    /**
     * Get map of armor type to value
     * 
     * @return Armor map
     */
    @Override
    public Map<String, Integer> getArmor() {
        return armor;
    }

    /**
     * Get base attack bonus
     * 
     * @return Base attack bonus
     */
    @Override
    public int getBaseAttackBonus() {
        return bab;
    }

    /**
     * Get map of save name to value
     * 
     * @return Save map
     */
    @Override
    public Map<String, Integer> getSaves() {
        return saves;
    }

    /**
     * Get map of skill name to value
     * 
     * @return Skill map
     */
    @Override
    public Map<String, Integer> getSkills() {
        return skills;
    }

    /**
     * Get slot name
     * 
     * @return Slot name
     */
    @Override
    public String getSlot() {
        return slot;
    }
}
