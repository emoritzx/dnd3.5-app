/*
 * Think about the license.
 */
package dndlib.character;

import dndlib.structures.NameEntity;
import static org.testng.Assert.*;
import org.testng.annotations.Test;

/**
 *
 * @author emori
 */
public class AbilityTest {

    @Test
    public void calculateModifierTest() {
        Ability ability = new Ability(new NameEntity(""), 14);
        assertEquals(ability.getModifier(), 2);
    }
}
