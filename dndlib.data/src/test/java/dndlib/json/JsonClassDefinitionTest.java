/**
 * Copyright (c) 2016-2017, Evan Moritz.
 * Licensed under the MIT License. See the accompanying LICENSE file for terms.
 */
package dndlib.json;

import dndlib.character.ClassDefinition;
import dndlib.core.Enhancement;
import dndlib.dice.Die;
import java.util.List;
import java.util.function.BiFunction;
import java.util.stream.Collectors;
import javax.json.JsonObject;
import javax.json.JsonString;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.Test;
import org.junit.experimental.theories.DataPoints;
import org.junit.experimental.theories.Theories;
import org.junit.experimental.theories.Theory;
import org.junit.runner.RunWith;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Test cases for the JsonClassDefinition class
 *
 * @author sldur
 */
@RunWith(Theories.class)
public class JsonClassDefinitionTest {

    BiFunction<Integer, Integer, Die> cnvtr = (level, hit) -> {
        Die die = mock(Die.class);
        when(die.roll()).thenReturn(hit - level);
        return die;
    };

    public static class TestItem {

        int level;
        Enhancement expected;

        public TestItem(int level, Enhancement expected) {
            this.level = level;
            this.expected = expected;
        }
    }

    @DataPoints
    public static TestItem[] items = new TestItem[]{
        new TestItem(1, new JsonEnhancement(TestHelper.getEnhancementMulti())),
        new TestItem(2, new JsonEnhancement(TestHelper.getEnhancement1()))
    };

    /**
     * Test of getClassForLevel method, of class JsonClassDefinition.
     *
     * Covers getClassForLevel def-use path [1,2]
     * and Constructor def-use paths [1,2,3,4,5] and [5,3,4,5]
     * 
     * @param testCase
     */
    @Theory
    public void testGetClassForLevel(TestItem testCase) {
        JsonObject jsonObj = TestHelper.getClassDefinition();
        JsonClassDefinition jsonDef = new JsonClassDefinition(jsonObj, cnvtr);
        ClassDefinition def = jsonDef.getClassForLevel(testCase.level);

        //check enhancements
        assertTrue(TestHelper.equals(testCase.expected, def.getEnhancements()));
        //check names
        assertEquals(jsonObj.getString("name"), def.getName());
        //check skill modifier
        assertEquals(jsonObj.getInt(JsonClassDefinition.SKILLMOD_ID),
                def.getSkillModifier());
        //check hit die
        assertEquals(jsonObj.getInt(JsonClassDefinition.DIE_ID) - testCase.level,
                def.getHitDie().roll());
        //check skills
        List<String> skills = jsonObj.getJsonArray(JsonClassDefinition.SKILLS_ID)
                .getValuesAs(JsonString.class)
                .stream().map(JsonString::getString)
                .collect(Collectors.toList());
        assertTrue(skills.containsAll(def.getSkills()));
        assertTrue(def.getSkills().containsAll(skills));
    }

    /**
     * Test of size method, of class JsonClassDefinition.
     * 
     * Covers size def-use path [1,2]
     * and Constructor def-use path [1,2,3,4,5] and [5,3,4,5]
     */
    @Test
    public void testSize() {
        JsonClassDefinition jsonDef = new JsonClassDefinition(
                TestHelper.getClassDefinition(), cnvtr);
        assertEquals(2, jsonDef.size());
    }

}
