/**
 * Copyright (c) 2016-2017, Evan Moritz.
 * Licensed under the MIT License. See the accompanying LICENSE file for terms.
 */
package dndlib.json;

import dndlib.core.Enhancement;
import java.util.List;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonNumber;
import javax.json.JsonObject;
import javax.json.JsonString;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.Test;
import org.junit.experimental.theories.DataPoints;
import org.junit.experimental.theories.Theories;
import org.junit.experimental.theories.Theory;
import org.junit.runner.RunWith;

/**
 * Test cases for the JsonEnhancement class
 * @author sldur
 */
@RunWith(Theories.class)
public class JsonEnhancementTest {
    
    @DataPoints
    public static JsonObject[] cases = new JsonObject[]{
        TestHelper.getEnhancement1(),
        TestHelper.getEnhancementMulti()
    };
    
    
    /**
     * Test of fromList method, of class JsonEnhancement.
     * 
     * Covers fromList def-use path [1,2,3,4,5,6]
     */
    @Test
    public void testFromList() {
        JsonArray arr = Json.createArrayBuilder()
                .add(cases[0])
                .add(cases[1])
                .build();
        List<Enhancement> list = JsonEnhancement.fromList(arr);
        assertEquals(2, list.size());
    }
    
    /**
     * Test of fromList method, of class JsonEnhancement.
     * Test with null input JsonArray.
     * 
     * Covers fromList def-use path [1,2,3]
     * 
     */
    @Test
    public void testFromListNull(){
        List<Enhancement> list = JsonEnhancement.fromList(null);
        assertTrue(list.isEmpty());
    }

    /**
     * Test of fromList method, of class JsonEnhancement.
     * Test with empty input JsonArray.
     * 
     * Covers fromList def-use path [1,2,3,4,5,7]
     */
    @Test
    public void testFromListEmpty(){
        JsonArray empty = Json.createArrayBuilder().build();
        List<Enhancement> list = JsonEnhancement.fromList(empty);
        assertTrue(list.isEmpty());
    }
    
    
    /**
     * Test of getAbilities method, of class JsonEnhancement.
     * 
     * Covers getAbilities def-use path [1,2]
     * and Constructor def-use path [1,2]
     * 
     * @param jsonObj the input json object
     */
    @Theory
    public void testGetAbilities(JsonObject jsonObj) {
        JsonEnhancement enhance = new JsonEnhancement(jsonObj);
        JsonObject abilities = jsonObj.getJsonObject(JsonEnhancement.ABILITY_ID);
        abilities.entrySet().stream().forEach((expectedEntry)-> 
            assertEquals(((JsonNumber)expectedEntry.getValue()).intValue(), 
                    enhance.getAbilities().get(expectedEntry.getKey()).intValue())
        );
    }

    /**
     * Test of getArmor method, of class JsonEnhancement.
     * Covers getArmor def-use path [1,2]
     * and Constructor def-use path [1,2]
     * 
     * @param jsonObj the input json object
     */
    @Theory
    public void testGetArmor(JsonObject jsonObj) {
        JsonEnhancement enhance = new JsonEnhancement(jsonObj);
        JsonObject armor = jsonObj.getJsonObject(JsonEnhancement.ARMOR_ID);
        armor.entrySet().stream().forEach((expectedEntry)-> 
            assertEquals(((JsonNumber)expectedEntry.getValue()).intValue(), 
                    enhance.getArmor().get(expectedEntry.getKey()).intValue())
        );
    }

    /**
     * Test of getBaseAttackBonus method, of class JsonEnhancement.
     * Covers getBaseAttackBonus def-use path [1,2]
     * and Constructor def-use path [1,2]
     * 
     * @param jsonObj the input json object
     */
    @Theory
    public void testGetBaseAttackBonus(JsonObject jsonObj) {
        JsonEnhancement enhance = new JsonEnhancement(jsonObj);
        JsonNumber bab = jsonObj.getJsonNumber(JsonEnhancement.BAB_ID);
        assertEquals(bab.intValue(), enhance.getBaseAttackBonus());
    }
    
    /**
     * Test of getBaseAttackBonus method, of class JsonEnhancement.
     * Input json object does not contain a BAB entry
     */
    @Test
    public void testGetBaseAttackBonusDefault(){
        JsonEnhancement enhance = new JsonEnhancement(TestHelper.getEnhancementNoBABSlot());
        int expected = 0;
        assertEquals(expected, enhance.getBaseAttackBonus());
    }

    /**
     * Test of getSaves method, of class JsonEnhancement.
     * 
     * Covers getSaves def-use path [1,2]
     * and Constructor def-use path [1,2]
     * 
     * @param jsonObj the input json object
     */
    @Theory
    public void testGetSaves(JsonObject jsonObj) {
        JsonEnhancement enhance = new JsonEnhancement(jsonObj);
        JsonObject saves = jsonObj.getJsonObject(JsonEnhancement.SAVE_ID);
        saves.entrySet().stream().forEach((expectedEntry)
                -> assertEquals(((JsonNumber) expectedEntry.getValue()).intValue(),
                        enhance.getSaves().get(expectedEntry.getKey()).intValue())
        );
    }

    /**
     * Test of getSkills method, of class JsonEnhancement.
     * 
     * Covers getSkills def-use path [1,2]
     * and Constructor def-use path [1,2]
     * 
     * @param jsonObj the input json object
     */
    @Theory
    public void testGetSkills(JsonObject jsonObj) {
        JsonEnhancement enhance = new JsonEnhancement(jsonObj);
        JsonObject skills = jsonObj.getJsonObject(JsonEnhancement.SKILL_ID);
        skills.entrySet().stream().forEach((expectedEntry)
                -> assertEquals(((JsonNumber) expectedEntry.getValue()).intValue(),
                        enhance.getSkills().get(expectedEntry.getKey()).intValue())
        );
    }

    /**
     * Test of getSlot method, of class JsonEnhancement.
     * 
     * Covers getSlot def-use path [1,2]
     * and Constructor def-use path [1,2]
     * 
     * @param jsonObj the input json object
     */
    @Theory
    public void testGetSlot(JsonObject jsonObj) {
        JsonEnhancement enhance = new JsonEnhancement(jsonObj);
        JsonString slot = jsonObj.getJsonString(JsonEnhancement.SLOT_ID);
        assertEquals(slot.getString(), enhance.getSlot());
    }
    
    /**
     * Test of getSlot method, of class JsonEnhancement.
     * Input json object does not contain a slot entry
     */
    @Test
    public void testGetSlotDefault(){
        JsonEnhancement enhance = new JsonEnhancement(TestHelper.getEnhancementNoBABSlot());
        String expected = "<unslotted>" ;
        assertEquals(expected, enhance.getSlot());
    }
    
}
