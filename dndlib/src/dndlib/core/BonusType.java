/*
 * Think about the license.
 */
package dndlib.core;

import java.util.Arrays;
import java.util.Collection;
import java.util.Objects;
import java.util.function.Function;
import java.util.function.IntBinaryOperator;
import java.util.stream.Collectors;

/**
 *
 * @author emori
 */
public class BonusType extends Attribute {

    public static final IntBinaryOperator DefaultOperator = Integer::max;

    private final IntBinaryOperator operator;

    public BonusType(String name) {
        this(name, DefaultOperator);
    }

    public BonusType(String name, Function<String, String> abbreviator) {
        this(name, abbreviator, DefaultOperator);
    }

    public BonusType(String name, String abbreviation) {
        this(name, abbreviation, DefaultOperator);
    }

    public BonusType(String name, IntBinaryOperator operator) {
        super(name);
        this.operator = operator;
    }

    public BonusType(String name, Function<String, String> abbreviator, IntBinaryOperator operator) {
        super(name, abbreviator);
        this.operator = operator;
    }

    public BonusType(String name, String abbreviation, IntBinaryOperator operator) {
        super(name, abbreviation);
        this.operator = operator;
    }

    public IntBinaryOperator getOperator() {
        return operator;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 19 * hash + getName().hashCode();
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final BonusType other = (BonusType) obj;
        if (!Objects.equals(getName(), other.getName())) {
            return false;
        }
        
        return true;
    }
    
    

    public static enum Defaults {

        CIRCUMSTANCE(new BonusType("circumstance")),
        COMPETANCE(new BonusType("competance")),
        DIVINE(new BonusType("divine")),
        ENHANCEMENT(new BonusType("enhancement")),
        INSIGHT(new BonusType("insight")),
        LUCK(new BonusType("luck")),
        MORALE(new BonusType("morale")),
        RACIAL(new BonusType("racial")),
        UNTYPED(new BonusType("untyped"));

        private final BonusType bonusType;

        private Defaults(BonusType bonusType) {
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
    
    public static Collection<BonusType> getDefaults() {
        return Arrays
            .stream(BonusType.Defaults.values())
            .map(BonusType.Defaults::getBonusType)
            .collect(Collectors.toList());
    }
}
