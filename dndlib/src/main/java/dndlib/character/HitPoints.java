/**
 * Copyright (c) 2016-2017, Evan Moritz.
 * Licensed under the MIT License. See the accompanying LICENSE file for terms.
 */
package dndlib.character;

import dndlib.core.NumberedObservable;
import java.util.List;
import java.util.stream.IntStream;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.IntegerBinding;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.ObservableList;

/**
 *
 * @author emori
 */
public class HitPoints implements NumberedObservable {

    private final IntegerBinding healthPointsBinding;
    private final IntegerProperty healthPoints = new SimpleIntegerProperty();
    private final IntegerProperty damage = new SimpleIntegerProperty();

    public HitPoints(Ability ability, ObservableList<Level> levels) {
        healthPointsBinding = Bindings.createIntegerBinding(
            () -> compute(ability, levels),
            ability.modifierProperty(),
            levels,
            damage);
        healthPoints.bind(healthPointsBinding);
    }
    
    private int compute(Ability ability, List<Level> levels) {
        return IntStream.rangeClosed(1, levels.size())
            .map(level -> 
                levels
                    .get(level - 1)
                    .getClassDefinition()
                    .getHitDie()
                    .roll()
                + ability.getModifier()
            )
            .sum()
            - damage.get();
    }

    void damage(int amount) {
        damage.set(damage.get() + amount);
    }

    @Override
    public int getValue() {
        return healthPoints.get();
    }
    
    @Override
    public IntegerProperty valueProperty() {
        return healthPoints;
    }
}
