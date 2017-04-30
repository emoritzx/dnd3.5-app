/**
 * Copyright (c) 2016-2017, Evan Moritz.
 * Licensed under the MIT License. See the accompanying LICENSE file for terms.
 */
package dndlib.json;

import dndlib.character.ClassDefinition;
import dndlib.character.Level;
import dndlib.core.Enhancement;
import java.util.HashMap;
import java.util.Set;
import java.util.function.BiFunction;
import java.util.function.Function;
import javax.json.JsonArray;
import javax.json.JsonObject;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import org.junit.Test;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 *
 * @author sldur
 */
public class JsonCharacterTest {

           BiFunction<String, Integer, ClassDefinition> classCnvtr = (str, level)-> null;
    
    /**
     * Test of from method, of class JsonCharacter.
     */
    @Test
    public void testFrom() {
       Function<String, Enhancement> raceCnvtr = (str)-> {
               Enhancement race = mock(Enhancement.class);
               when(race.getAbilities()).thenReturn(new HashMap<>());
               return race;
       };
       JsonObject jsonChar = TestHelper.getCharacter();

       dndlib.character.Character character = JsonCharacter.from(jsonChar, raceCnvtr, classCnvtr);
       //check name
       assertEquals(jsonChar.getString(JsonCharacter.NAME_ID), character.getName());
       //check abilities
       Set<String> abilities = TestHelper.getAbilities().keySet();
       assertTrue(abilities.containsAll(character.getAbilities().keySet()));
       assertTrue(character.getAbilities().keySet().containsAll(abilities));
       //check levels
       int numLevels = jsonChar.getJsonArray(JsonCharacter.LEVEL_ID).size();
       assertEquals(numLevels, character.getLevels().size());
    }

    /**
     * Test of parseLevel method, of class JsonCharacter.
     */
    @Test
    public void testParseLevel() {
        JsonArray levels = TestHelper.getCharacter().getJsonArray(JsonCharacter.LEVEL_ID);
        JsonObject levelObj = levels.getJsonObject(0);
        int numEnhancements = levelObj.getJsonArray(JsonCharacter.ENHANCEMENT_ID).size();
        Level level = JsonCharacter.parseLevel(1, levelObj, classCnvtr);
        assertNull(level.getClassDefinition());
        assertEquals(numEnhancements, level.getEnhancements().size());
    }
    
}
