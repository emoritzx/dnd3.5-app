/**
 * Copyright (c) 2016-2017, Evan Moritz.
 * Licensed under the MIT License. See the accompanying LICENSE file for terms.
 */
package dndlib.json;

import java.util.Map;
import java.util.function.BiFunction;
import java.util.function.Function;
import javax.json.Json;
import javax.json.JsonNumber;
import javax.json.JsonObject;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.Test;

/**
 * Test cases for the JsonMap class
 * @author sldur
 */
public class JsonMapTest {
    
    JsonObject singleEntry = Json.createObjectBuilder()
            .add("ability1", 1)
            .build();
    
    JsonObject multiEntry = Json.createObjectBuilder()
            .add("a", 1)
            .add("b", 2)
            .add("c", 33)
            .build();
    
    JsonObject empty = Json.createObjectBuilder().build();
    
    BiFunction<String, JsonNumber, Integer> numFnc = (str, jsonNum) -> jsonNum.intValue();
    
    Function<JsonNumber, Integer> numFnc2 = (jsonNum)-> jsonNum.intValue();

    /**
     * Test of from method, of class JsonMap.
     * Test case with a single entry in the JSON object.
     */
    @Test
    public void testFromSingleEntry() {
        Map<String, Integer> map = JsonMap.from(singleEntry, numFnc);
        assertEquals(1, map.size());
        assertEquals(1, map.get("ability1").intValue());
    }

    /**
     * Test of from method, of class JsonMap.
     * Test case with multiple entries in the JSON object.
     */
    @Test
    public void testFromMultiEntry(){
        Map<String, Integer> map = JsonMap.from(multiEntry, numFnc);
        assertEquals(3, map.size());
        assertEquals(1, map.get("a").intValue());
        assertEquals(2, map.get("b").intValue());
        assertEquals(33, map.get("c").intValue());
    }
    
    /**
     * Test of from method, of class JsonMap.
     * Test case with empty JSON object.
     */
    @Test
    public void testFromEmpty(){
        Map<String, Integer> map = JsonMap.from(empty, numFnc);
        assertTrue(map.isEmpty());
    }
    /**
     * Test of from method, of class JsonMap.
     * Test case with null JSON object.
     */
    @Test
    public void testFromNullJson(){
        Map<String, Integer> map = JsonMap.from(null, numFnc);
        assertTrue(map.isEmpty());
    }
    
    
    /**
     * Test of fromNumber method, of class JsonMap.
     * Test case with a single entry in the JSON object.
     */
    @Test
    public void testFromNumberSingleEntry() {
        Map<String, Integer> map = JsonMap.fromNumber(singleEntry, numFnc2);
        assertEquals(1, map.size());
        assertEquals(1, map.get("ability1").intValue());
    }

    /**
     * Test of fromNumber method, of class JsonMap.
     * Test case with multiple entries in the JSON object.
     */
    @Test
    public void testFromNumberMultiEntry(){
        Map<String, Integer> map = JsonMap.fromNumber(multiEntry, numFnc2);
        assertEquals(3, map.size());
        assertEquals(1, map.get("a").intValue());
        assertEquals(2, map.get("b").intValue());
        assertEquals(33, map.get("c").intValue());
    }
    
    /**
     * Test of fromNumber method, of class JsonMap.
     * Test case with empty JSON object.
     */
    @Test
    public void testFromNumberEmpty(){
        Map<String, Integer> map = JsonMap.fromNumber(empty, numFnc2);
        assertTrue(map.isEmpty());
    }
    /**
     * Test of from method, of class JsonMap.
     * Test case with null JSON object.
     */
    @Test
    public void testFromNumberNullJson(){
        Map<String, Integer> map = JsonMap.fromNumber(null, numFnc2);
        assertTrue(map.isEmpty());
    }

    /**
     * Test of fromNumber with BiFunction method, of class JsonMap.
     * Test case with a single entry in the JSON object.
     */
    @Test
    public void testFromNumberBiFunctionSingleEntry() {
        Map<String, Integer> map = JsonMap.fromNumber(singleEntry, numFnc);
        assertEquals(1, map.size());
        assertEquals(1, map.get("ability1").intValue());
    }

    /**
     * Test of fromNumber with BiFunction method, of class JsonMap.
     * Test case with multiple entries in the JSON object.
     */
    @Test
    public void testFromNumberBiFunctionMultiEntry(){
        Map<String, Integer> map = JsonMap.fromNumber(multiEntry, numFnc);
        assertEquals(3, map.size());
        assertEquals(1, map.get("a").intValue());
        assertEquals(2, map.get("b").intValue());
        assertEquals(33, map.get("c").intValue());
    }
    
    /**
     * Test of fromNumber with BiFunction method, of class JsonMap.
     * Test case with empty JSON object.
     */
    @Test
    public void testFromNumberBiFunctionEmpty(){
        Map<String, Integer> map = JsonMap.fromNumber(empty, numFnc);
        assertTrue(map.isEmpty());
    }
    /**
     * Test of fromNumber with BiFunction method, of class JsonMap.
     * Test case with null JSON object.
     */
    @Test
    public void testFromNumberBiFunctionNullJson(){
        Map<String, Integer> map = JsonMap.from(null, numFnc);
        assertTrue(map.isEmpty());
    }
    
}
