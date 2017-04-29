/**
 * Copyright (c) 2016-2017, Evan Moritz.
 * Licensed under the MIT License. See the accompanying LICENSE file for terms.
 */
package dndlib.json;

import java.math.BigDecimal;
import javax.json.Json;
import javax.json.JsonObject;

/**
 * Class of static functions to help building JsonObjects to use in testing the
 * classes within this package.
 *
 * @author sldur
 */
public class TestHelper {

    public static JsonObject getAbilities() {
        return Json.createObjectBuilder()
                .add("strength", 55)
                .add("e", -4)
                .add("tacos", 0)
                .build();
    }

    public static JsonObject getAbility() {
        return Json.createObjectBuilder()
                .add("roar", 5)
                .build();
    }

    public static JsonObject getArmor() {
        return Json.createObjectBuilder()
                .add("shield", 450)
                .add("tree", -65)
                .add("fish", 0)
                .build();
    }

    public static JsonObject getArmorSingle() {
        return Json.createObjectBuilder()
                .add("tuxedo", 1)
                .build();
    }

    public static JsonObject getSaves() {
        return Json.createObjectBuilder()
                .add("save1", 34)
                .add("save2", 78)
                .add("save3", -3)
                .add("save4", 0)
                .build();
    }

    public static JsonObject getSave() {
        return Json.createObjectBuilder()
                .add("save", 3)
                .build();
    }

    public static JsonObject getSkills() {
        return Json.createObjectBuilder()
                .add("sword", 56)
                .add("taste", -4)
                .add("cooking", 0)
                .build();
    }

    public static JsonObject getSkill() {
        return Json.createObjectBuilder()
                .add("scratching", 64)
                .build();
    }

    public static JsonObject getEnhancementMulti() {
        return Json.createObjectBuilder()
                .add(JsonEnhancement.ABILITY_ID, getAbilities())
                .add(JsonEnhancement.ARMOR_ID, getArmor())
                .add(JsonEnhancement.BAB_ID, 44)
                .add(JsonEnhancement.SAVE_ID, getSaves())
                .add(JsonEnhancement.SKILL_ID, getSkills())
                .add(JsonEnhancement.SLOT_ID, "id1")
                .build();
    }

    public static JsonObject getEnhancement1() {
        return Json.createObjectBuilder()
                .add(JsonEnhancement.ABILITY_ID, getAbility())
                .add(JsonEnhancement.ARMOR_ID, getArmorSingle())
                .add(JsonEnhancement.BAB_ID, 44)
                .add(JsonEnhancement.SAVE_ID, getSave())
                .add(JsonEnhancement.SKILL_ID, getSkill())
                .add(JsonEnhancement.SLOT_ID, "slot id")
                .build();
    }
    
    public static JsonObject getEnhancementNoBABSlot(){
                return Json.createObjectBuilder()
                .add(JsonEnhancement.ABILITY_ID, getAbility())
                .add(JsonEnhancement.ARMOR_ID, getArmorSingle())
                .add(JsonEnhancement.SAVE_ID, getSave())
                .add(JsonEnhancement.SKILL_ID, getSkill())
                .build();
    }

}
