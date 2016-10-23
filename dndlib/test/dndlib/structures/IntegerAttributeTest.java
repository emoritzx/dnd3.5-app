/*
 * Think about the license.
 */
package dndlib.structures;

import static org.testng.Assert.*;
import org.testng.annotations.Test;

/**
 *
 * @author emori
 */
public class IntegerAttributeTest {
    @Test
    public void simpleSetAndGetTest() {
        NumberEntity attribute = new NumberEntity();
        attribute.setValue(10);
        assertEquals(attribute.getValue(), 10);
    }
}
