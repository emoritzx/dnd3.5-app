/*
 * Think about the license.
 */
package dndlib.structures;

import dndlib.character.Ability;
import dndlib.core.BonusType;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import org.testng.annotations.Test;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

/**
 *
 * @author emori
 */
public class CompositeNumberEntityTest {

    @Test
    public void basicTest() {
        
        int ability = 14;
        int modifier = 2;
        int base = 10;
        int shield = 5;
        int ac = base + modifier + shield;
        
        Ability dex = new Ability(new NameEntity(""));
        dex.setValue(ability);
        
        Map<BonusType, IntegerProperty> bonusTypes = new HashMap<>();
        bonusTypes.put(new BonusEntity("class"), new SimpleIntegerProperty(base));
        bonusTypes.put(new BonusEntity("ability"), dex.modifierProperty());
        bonusTypes.put(new BonusEntity("shield"), new SimpleIntegerProperty(shield));
        
        CompositeNumberEntity armor = new CompositeNumberEntity(bonusTypes);
        assertEquals(armor.getValue(), ac);
        
        bonusTypes.entrySet().stream()
            .forEach(entry -> assertEquals(armor.getValue(entry.getKey()), entry.getValue().intValue()));
    }
    
    @Test
    public void removeTest() {
        int ability = 14;
        int modifier = 2;
        int base = 10;
        int shield = 5;
        int ac = base + modifier + shield;
        
        Ability dex = new Ability(new NameEntity(""));
        dex.setValue(ability);
        
        BonusType shieldBonus = new BonusEntity("shield");
        IntegerProperty shieldProperty = new SimpleIntegerProperty(shield);
        
        Map<BonusType, IntegerProperty> bonusTypes = new HashMap<>();
        bonusTypes.put(new BonusEntity("class"), new SimpleIntegerProperty(base));
        bonusTypes.put(new BonusEntity("ability"), dex.modifierProperty());
        bonusTypes.put(shieldBonus, shieldProperty);
        
        CompositeNumberEntity armor = new CompositeNumberEntity(bonusTypes);
        assertEquals(armor.getValue(), ac);
        armor.removeBonusValue(shieldBonus, shieldProperty);
        assertEquals(armor.getValue(), ac - shield);
        assertEquals(armor.getValue(shieldBonus), 0);
    }
    
    @Test
    public void triggerTest() {
        int ability = 14;
        int modifier = 2;
        int base = 10;
        int shield = 5;
        int ac = base + modifier + shield;
        
        Ability dex = new Ability(new NameEntity(""));
        dex.setValue(ability);
        
        BonusType shieldBonus = new BonusEntity("shield");
        IntegerProperty shieldProperty = new SimpleIntegerProperty(shield);
        
        Map<BonusType, IntegerProperty> bonusTypes = new HashMap<>();
        bonusTypes.put(new BonusEntity("class"), new SimpleIntegerProperty(base));
        bonusTypes.put(new BonusEntity("ability"), dex.modifierProperty());
        bonusTypes.put(shieldBonus, shieldProperty);
        
        CompositeNumberEntity armor = new CompositeNumberEntity(bonusTypes);
        assertEquals(armor.getValue(), ac);
        
        AtomicBoolean updated = new AtomicBoolean(false);
        
        armor.valueProperty().addListener((observable, oldValue, newValue) -> {
            assertEquals(oldValue, ac);
            assertEquals(newValue, ac - shield);
            updated.set(true);
        }) ;
        
        armor.removeBonusValue(shieldBonus, shieldProperty);
        assertTrue(updated.get());
    }
    
    @Test
    public void initialValuesTest() {
        int ability = 14;
        int modifier = 2;
        int base = 10;
        int shield = 5;
        int ac = base + modifier + shield;
        
        Ability dex = new Ability(new NameEntity(""));
        dex.setValue(ability);
        
        Map<BonusType, IntegerProperty> bonusTypes = new HashMap<>();
        bonusTypes.put(new BonusEntity("class"), new SimpleIntegerProperty(base));
        bonusTypes.put(new BonusEntity("ability"), dex.modifierProperty());
        bonusTypes.put(new BonusEntity("shield"), new SimpleIntegerProperty(shield));
        
        CompositeNumberEntity armor = new CompositeNumberEntity(bonusTypes);
        assertEquals(armor.getValue(), ac);
        
        armor.getBonusTypes().stream()
            .forEach(bonusType -> bonusTypes.containsKey(bonusType));
        bonusTypes.keySet().stream()
            .forEach(bonusType -> armor.getBonusTypes().contains(bonusType));
    }
}
