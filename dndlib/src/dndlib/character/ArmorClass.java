/*
 * Think about the license.
 */
package dndlib.character;

import dndlib.core.BonusType;
import dndlib.core.CompositeAttribute;
import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

/**
 *
 * @author emori
 */
public class ArmorClass extends CompositeAttribute {

    @Override
    public Collection<BonusType> getBonusTypes() {
        return Stream
                .concat(
                        BonusType.getDefaults().stream(),
                        Arrays.stream(Type.values()).map(Type::getBonusType)
                )
                .collect(Collectors.toList());
    }

    public enum Type {
        ARMOR(new BonusType("armor")),
        CLASS(new BonusType("class")),
        DEFLECTION(new BonusType("deflection")),
        DODGE(new BonusType("dodge", Integer::sum)),
        NATURAL(new BonusType("natural")),
        SHIELD(new BonusType("shield"));

        private final BonusType bonusType;

        private Type(BonusType bonusType) {
            this.bonusType = bonusType;
        }

        @Override
        public String toString() {
            return bonusType.getName();
        }

        public BonusType getBonusType() {
            return bonusType;
        }
    }

    private final IntegerProperty value = new SimpleIntegerProperty();

    public ArmorClass(Ability ability) {
        super("Armor Class", "AC");
        value.bind(super.valueProperty().add(ability.modifierProperty()));
    }

    @Override
    public int getValue() {
        return value.get();
    }

    @Override
    public IntegerProperty valueProperty() {
        return value;
    }

    public int getTouch() {
        return getValue()
                - getValue(Type.ARMOR.getBonusType())
                - getValue(Type.SHIELD.getBonusType())
                - getValue(Type.NATURAL.getBonusType());
    }

    public int getFlat() {
        return super.getValue();
    }
}
