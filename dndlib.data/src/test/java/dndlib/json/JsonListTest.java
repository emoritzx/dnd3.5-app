/**
 * Copyright (c) 2016-2017, Evan Moritz.
 * Licensed under the MIT License. See the accompanying LICENSE file for terms.
 */
package dndlib.json;

import java.util.List;
import java.util.function.Function;
import javafx.scene.shape.Rectangle;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonNumber;
import javax.json.JsonString;
import javax.json.JsonValue;
import org.junit.Test;
import org.junit.experimental.theories.Theories;
import org.junit.runner.RunWith;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 *
 * @author sldur
 */
@RunWith(Theories.class)
public class JsonListTest {

    private final JsonArray strArr = Json.createArrayBuilder()
            .add("a")
            .add("nachos")
            .add("")
            .build();

    private final JsonArray intArr = Json.createArrayBuilder()
            .add(0)
            .add(100)
            .add(45)
            .add(600)
            .build();

    private final JsonArray heteroArr = Json.createArrayBuilder()
            .add("a")
            .add(1)
            .add(3.1415926)
            .add(false)
            .build();

    private final JsonArray emptyArr = Json.createArrayBuilder().build();
    
    private final Function<JsonString, String> strFnc = (jsonStr) -> jsonStr.getString();

    /**
     * Test of fromArray method, of class JsonList.
     */
    @Test
    public void testFromArrayStrings() {
        List<String> returnList
                = JsonList.fromArray(strArr, strFnc);

        assertEquals(3, returnList.size());
        assertEquals("a", returnList.get(0));
        assertEquals("nachos", returnList.get(1));
        assertEquals("", returnList.get(2));
    }

    /**
     * Test of fromArray method, of class JsonList.
     */
    @Test
    public void testFromArrayInts() {
        List<Rectangle> squares = JsonList.fromArray(intArr,
                (JsonNumber jsonNum) -> new Rectangle(jsonNum.doubleValue(), jsonNum.doubleValue()));
        assertEquals(4, squares.size());
        assertEquals(0.0, squares.get(0).getWidth(), 0.0);
        assertEquals(0.0, squares.get(0).getHeight(), 0.0);
        assertEquals(100.0, squares.get(1).getWidth(), 0.0);
        assertEquals(100.0, squares.get(1).getHeight(), 0.0);
        assertEquals(45.0, squares.get(2).getWidth(), 0.0);
        assertEquals(45.0, squares.get(2).getHeight(), 0.0);
        assertEquals(600.0, squares.get(3).getWidth(), 0.0);
        assertEquals(600.0, squares.get(3).getHeight(), 0.0);
    }

    /**
     * Test of fromArray method, of class JsonList. Testing with a heterogeneous
     * list of data
     */
    @Test
    public void testFromArrayHeterogenous() {
        List<String> results = JsonList.fromArray(heteroArr,
                (JsonValue value) -> value.toString().replaceAll("\"", ""));
        assertEquals(4, results.size());
        assertEquals("a", results.get(0));
        assertEquals("1", results.get(1));
        assertEquals("3.1415926", results.get(2));
        assertEquals("false", results.get(3));
    }
    
    /**
     * Test of fromArray method, of class JsonList.
     * Testing with an empty list of data.
     */
    public void testFromArrayEmpty(){
        List<String> results = JsonList.fromArray(emptyArr,
                (JsonValue value) -> value.toString().replaceAll("\"", ""));
        assertTrue(results.isEmpty());
    }
    
    /**
     * Test of fromStringArray method, of class JsonList.
     */
    @Test
    public void testFromStringArray() {
        List<String> returnList
                = JsonList.fromStringArray(strArr, strFnc);

        assertEquals(3, returnList.size());
        assertEquals("a", returnList.get(0));
        assertEquals("nachos", returnList.get(1));
        assertEquals("", returnList.get(2));
    }

    /**
     * Test of fromStringArray method, of class JsonList.
     */
    @Test(expected = ClassCastException.class)
    public void testFromStringArrayInts() {
        List<String> squares = JsonList.fromStringArray(intArr, strFnc);
    }

    /**
     * Test of fromStringArray method, of class JsonList. Testing with a heterogeneous
     * list of data
     */
    @Test(expected = ClassCastException.class)
    public void testFromStringArrayHeterogenous() {
        List<String> results = JsonList.fromStringArray(heteroArr, strFnc);
    }
    
    /**
     * Test of fromStringArray method, of class JsonList.
     * Testing with an empty list of data.
     */
    public void testFromStringArrayEmpty(){
        List<String> results = JsonList.fromArray(emptyArr, strFnc);
        assertTrue(results.isEmpty());
    }

}
