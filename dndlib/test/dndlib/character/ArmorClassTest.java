/*
 * Think about the license.
 */
package dndlib.character;

import javafx.beans.property.SimpleIntegerProperty;
import static org.testng.Assert.assertEquals;
import org.testng.annotations.Test;

/**
 *
 * @author emori
 */
public class ArmorClassTest {

    @Test
    public void hello() {
        int ability = 14;
        int modifier = 2;
        int base = 10;
        int shield = 5;
        int ac = base + modifier + shield;
        int touch = ac - shield;
        int flat = ac - modifier;
        Ability dex = new Ability("Dexterity");
        dex.setValue(ability);
        ArmorClass armor = new ArmorClass(dex);
        armor.addBonusValue(ArmorClass.Type.CLASS.getBonusType(), new SimpleIntegerProperty(base));
        armor.addBonusValue(ArmorClass.Type.SHIELD.getBonusType(), new SimpleIntegerProperty(shield));
        assertEquals(armor.getValue(), ac);
        assertEquals(armor.getFlat(), flat);
        assertEquals(armor.getTouch(), touch);
    }
}
