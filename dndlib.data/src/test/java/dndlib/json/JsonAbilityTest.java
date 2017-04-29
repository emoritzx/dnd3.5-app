/**
 * Copyright (c) 2016-2017, Evan Moritz.
 * Licensed under the MIT License. See the accompanying LICENSE file for terms.
 */
package dndlib.json;

import dndlib.character.Ability;
import java.util.Map;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import org.junit.Test;

/**
 *Test cases for the JsonAbility class
 * @author sldur
 */
public class JsonAbilityTest {

    /**
     * Test of fromShortForm method, of class JsonAbility.
     */
    @Test
    public void testFromShortForm() {
        String agility = "agility";
        String empty = "";
        String s = "s";
        JsonArray jsonArray = Json.createArrayBuilder()
                .add(agility)
                .add(empty)
                .add(s)
                .build();
        Map<String, Ability> abilities = JsonAbility.fromShortForm(jsonArray);
        assertTrue(abilities.containsKey(agility));
        assertTrue(abilities.containsKey(empty));
        assertTrue(abilities.containsKey(s));

        Ability agilityAbility = abilities.get(agility);
        Ability emptyAbility = abilities.get(empty);
        Ability sAbility = abilities.get(s);
        assertNotNull(agilityAbility.getValue());
        assertEquals(agility, agilityAbility.getName());
        assertNotNull(emptyAbility.getValue());
        assertEquals(empty, emptyAbility.getName());
        assertNotNull(sAbility.getValue());
        assertEquals(s, sAbility.getName());
    }
    
    /**
     * Test of fromShortForm method, of class JsonAbility
     * Test for JsonArray with null entry
     */
    @Test(expected = Exception.class)
    public void testFromShortFormNullEntry(){
        String ability1 = "1";
        String ability2 = "2";
        JsonArray jsonArr = Json.createArrayBuilder()
                .add(ability1)
                .addNull()
                .add(ability2)
                .build();
        Map<String, Ability> abilities = JsonAbility.fromShortForm(jsonArr);                
    }

    /**
     * Test of fromShortForm method, of class JsonAbility. This will test that a
     * JsonParsingException will be thrown if there are non-strings in the array
     */
        @Test(expected = Exception.class)
    public void testFromShortFormInvalidType(){
        String ability1 = "1";
        int ability2 = 2;
        JsonArray jsonArr = Json.createArrayBuilder()
                .add(ability1)
                .add(ability2)
                .build();
        Map<String, Ability> abilities = JsonAbility.fromShortForm(jsonArr);                
    }

    
    /**
     * Test of fromLongForm method, of class JsonAbility.
     */
    @Test
    public void testFromLongForm() {
        String agility = "agility";
        int agilityVal =7;
        String empty = "";
        int emptyVal =0;
        String s = "s";
        int sVal = 5;
        JsonObject jsonObj = Json.createObjectBuilder()
                .add(agility, agilityVal)
                .add(empty, emptyVal)
                .add(s, sVal)
                .build();
        Map<String, Ability> abilities = JsonAbility.fromLongForm(jsonObj);
        assertTrue(abilities.containsKey(agility));
        assertTrue(abilities.containsKey(empty));
        assertTrue(abilities.containsKey(s));

        Ability agilityAbility = abilities.get(agility);
        Ability emptyAbility = abilities.get(empty);
        Ability sAbility = abilities.get(s);
        assertEquals(agilityVal, agilityAbility.getValue());
        assertEquals(agility, agilityAbility.getName());
        assertEquals(emptyVal, emptyAbility.getValue());
        assertEquals(empty, emptyAbility.getName());
        assertEquals(sVal, sAbility.getValue());
        assertEquals(s, sAbility.getName());
    }
    
        /**
     * Test of fromShortForm method, of class JsonAbility
     * Test for JsonArray with null entry
     */
    @Test(expected = Exception.class)
    public void testFromLongFormNullEntry(){
        String ability1 = "1";
        JsonObject jsonObj = Json.createObjectBuilder()
                .add(ability1, 1)
                .addNull("")
                .build();
        Map<String, Ability> abilities = JsonAbility.fromLongForm(jsonObj);                
    }

    /**
     * Test of fromShortForm method, of class JsonAbility. This will test that a
     * JsonParsingException will be thrown if there are non-strings in the array
     */
        @Test(expected = Exception.class)
    public void testFromLongFormInvalidType(){
        String ability1 = "1";
        int val1 = 2;
        String ability2 = "2";
        JsonObject jsonObj = Json.createObjectBuilder()
                .add(ability1, val1)
                .add(ability2, ability2)
                .build();
        Map<String, Ability> abilities = JsonAbility.fromLongForm(jsonObj);                
    }

}
