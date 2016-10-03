/*
 * Think about the license.
 */
package dndlib.character;

import dndlib.core.CompositeNumberEntity;
import dndlib.core.AbstractNumberEntity;
import java.util.function.Function;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

/**
 *
 * @author emori
 */
public class AbilityAttribute extends AbstractNumberEntity {

    private final Ability ability;
    private final IntegerProperty value = new SimpleIntegerProperty();
    private final CompositeNumberEntity bonusAttribute;

    public AbilityAttribute(Ability ability, CompositeNumberEntity bonusAttribute, String name, Function<String, String> abbreviator) {
        super(name, abbreviator);
        this.ability = ability;
        this.bonusAttribute = bonusAttribute;
        init();
    }

    public AbilityAttribute(Ability ability, CompositeNumberEntity bonusAttribute, String name, String abbreviation) {
        super(name, abbreviation);
        this.ability = ability;
        this.bonusAttribute = bonusAttribute;
        init();
    }

    public AbilityAttribute(Ability ability, CompositeNumberEntity bonusAttribute, String name) {
        super(name);
        this.ability = ability;
        this.bonusAttribute = bonusAttribute;
        init();
    }

    private void init() {
        value.bind(
            ability.modifierProperty()
            .add(bonusAttribute.valueProperty())
        );
    }

    public Ability getAbility() {
        return ability;
    }
    
    public CompositeNumberEntity getBonus() {
        return bonusAttribute;
    }

    @Override
    public IntegerProperty valueProperty() {
        return value;
    }
}
