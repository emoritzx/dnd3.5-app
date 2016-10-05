/*
 * Think about the license.
 */
package dndlib.core;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.function.Supplier;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.IntStream;
import static org.testng.Assert.*;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

/**
 *
 * @author emori
 */
public class CycleTest {

    @DataProvider
    public Object[][] getData() {
        return new Object[][]{
            {Arrays.asList(1, 2, 3, 4, 5, 6)},
            {Collections.emptyList()},};
    }

    @Test(dataProvider = "getData")
    public void iteratorTest(List<Integer> list) {
        Iterator<Integer> iterator = Cycle.iterator(list);
        assertEquals(iterator.hasNext(), !list.isEmpty());
        IntStream.range(0, list.size() * 3)
                .forEach(i -> assertEquals((int) iterator.next(), (int) list.get(i % list.size())));
    }

    @Test(dataProvider = "getData")
    public void supplierTest(List<Integer> list) {
        Supplier<Integer> supplier = Cycle.supplier(list);
        IntStream.range(0, list.size() * 3)
                .forEach(i -> assertEquals((int) supplier.get(), (int) list.get(i % list.size())));
    }

    @Test
    public void emptyIteratorTest() {
        Iterator<Integer> iterator = Cycle.iterator(Collections.emptyList());
        assertFalse(iterator.hasNext());
    }

    @Test(expectedExceptions = NoSuchElementException.class)
    public void emptySupplierTest() {
        Supplier<Integer> supplier = Cycle.supplier(Collections.emptyList());
        supplier.get();
    }

    @Test(expectedExceptions = InstantiationException.class)
    public void disabledConstructorTest() throws Throwable {
        final Constructor<?>[] constructors = Cycle.class.getDeclaredConstructors();
        // check that all constructors are 'private':
        for (final Constructor<?> constructor : constructors) {
            assertTrue(Modifier.isPrivate(constructor.getModifiers()));
        }
        // call the private constructor:
        constructors[0].setAccessible(true);
        try {
            constructors[0].newInstance();
        } catch (InvocationTargetException ex) {
            throw ex.getCause();
        }
    }
}
