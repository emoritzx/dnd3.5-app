/*
 * Think about the license.
 */
package dndlib.character;

import dndlib.character.Ability;
import dndlib.character.ArmorClass;
import javafx.beans.property.SimpleIntegerProperty;
import static org.testng.Assert.assertEquals;
import org.testng.annotations.Test;

/**
 *
 * @author emori
 */
public class ArmorClassTest {
    
    public ArmorClassTest() {
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
     @Test
     public void hello() {
         Ability dex = new Ability("Dexterity");
         dex.setValue(14);
         ArmorClass armor = new ArmorClass(dex);
         armor.addBonusValue(ArmorClass.Type.CLASS.getBonusType(), new SimpleIntegerProperty(10));
         armor.addBonusValue(ArmorClass.Type.SHIELD.getBonusType(), new SimpleIntegerProperty(5));
         assertEquals(armor.getValue(), 17, "Armor value butts");
     }

}
