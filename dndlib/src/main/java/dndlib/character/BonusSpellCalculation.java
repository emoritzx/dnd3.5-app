/**
 * Copyright (c) 2016-2017, Evan Moritz.
 * Licensed under the MIT License. See the accompanying LICENSE file for terms.
 */
package dndlib.character;

import dndlib.core.IntegerMath;
import dndlib.core.ScalableDefinition;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.IntBinaryOperator;
import javafx.beans.binding.Bindings;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

/**
 *
 * @author emori
 */
public final class BonusSpellCalculation implements ScalableDefinition<IntegerProperty> {

    private final Map<Integer, IntegerProperty> levels = new ConcurrentHashMap<>();
    private final IntegerProperty modifierProperty;
    private final IntBinaryOperator calculation;

    public BonusSpellCalculation(IntegerProperty modifierProperty) {
        this(modifierProperty, BonusSpellCalculation::calculate);
    }
    
    public BonusSpellCalculation(IntegerProperty modifierProperty, IntBinaryOperator calculation) {
         this.modifierProperty = Objects.requireNonNull(modifierProperty);
         this.calculation = Objects.requireNonNull(calculation);
    }

    @Override
    public IntegerProperty atLevel(int level) {
        return levels.computeIfAbsent(level, this::createProperty);
    }
    
    private IntegerProperty createProperty(int level) {
        IntegerProperty property = new SimpleIntegerProperty();
        property.bind(
            Bindings.createIntegerBinding(
                () -> calculation.applyAsInt(modifierProperty.get(), level),
                modifierProperty));
        return property;
    }
    
    public static int calculate(int modifier, int level)  {
        return level == 0
            ? 0
            : IntegerMath.ceilingDivision(modifier - level + 1, 4);
    }
}
