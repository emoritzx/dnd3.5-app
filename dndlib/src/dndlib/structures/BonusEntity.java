/*
 * Think about the license.
 */
package dndlib.structures;

import dndlib.core.BonusType;
import dndlib.core.Named;
import java.util.Objects;
import java.util.function.IntBinaryOperator;

/**
 *
 * @author emori
 */
public class BonusEntity implements BonusType {

    public static final IntBinaryOperator DEFAULT_OPERATOR = Integer::max;
    
    private final Named name;
    private final IntBinaryOperator operator;
    
    public BonusEntity(String name) {
        this(new NameEntity(name));
    }
    
    public BonusEntity(Named name) {
        this(name, DEFAULT_OPERATOR);
    }

    public BonusEntity(Named name, IntBinaryOperator operator) {
        this.name = name;
        this.operator = operator;
    }
    
    @Override
    public String getAbbreviation() {
        return name.getAbbreviation();
    }

    @Override
    public String getName() {
        return name.getName();
    }

    @Override
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
        final BonusEntity other = (BonusEntity) obj;
        if (!Objects.equals(getName(), other.getName())) {
            return false;
        }
        return true;
    }
}
