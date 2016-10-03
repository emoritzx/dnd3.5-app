/*
 * Think about the license.
 */
package dndlib.core.test;

import dndlib.core.SimpleNumberEntity;
import static org.testng.Assert.*;
import org.testng.annotations.Test;

/**
 *
 * @author emori
 */
public class IntegerAttributeTest {
    @Test
    public void simpleSetAndGetTest() {
        SimpleNumberEntity attribute = new SimpleNumberEntity("");
        attribute.setValue(10);
        assertEquals(attribute.getValue(), 10);
    }
}
