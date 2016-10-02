/*
 * Think about the license.
 */
package dndlib.character;

import dndlib.core.CompositeAttribute;
import dndlib.core.ValueAttribute;
import java.util.function.Function;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

/**
 *
 * @author emori
 */
public class AbilityAttribute extends ValueAttribute {

    private final Ability ability;
    private final IntegerProperty value = new SimpleIntegerProperty();
    private final CompositeAttribute bonusAttribute;

    public AbilityAttribute(Ability ability, CompositeAttribute bonusAttribute, String name, Function<String, String> abbreviator) {
        super(name, abbreviator);
        this.ability = ability;
        this.bonusAttribute = bonusAttribute;
        init();
    }

    public AbilityAttribute(Ability ability, CompositeAttribute bonusAttribute, String name, String abbreviation) {
        super(name, abbreviation);
        this.ability = ability;
        this.bonusAttribute = bonusAttribute;
        init();
    }

    public AbilityAttribute(Ability ability, CompositeAttribute bonusAttribute, String name) {
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
    
    public CompositeAttribute getBonus() {
        return bonusAttribute;
    }

    @Override
    public IntegerProperty valueProperty() {
        return value;
    }
}
