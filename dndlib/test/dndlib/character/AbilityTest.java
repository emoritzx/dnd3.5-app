/*
 * Think about the license.
 */
package dndlib.character;

import dndlib.character.Ability;
import static org.testng.Assert.*;
import org.testng.annotations.Test;

/**
 *
 * @author emori
 */
public class AbilityTest {

    @Test
    public void calculateModifierTest() {
        Ability ability = new Ability("");
        ability.setValue(14);
        assertEquals(ability.getModifier(), 2, "modifier butts");
    }
}
