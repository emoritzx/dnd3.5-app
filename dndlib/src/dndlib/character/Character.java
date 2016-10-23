/*
 * Think about the license.
 */
package dndlib.character;

import javafx.beans.binding.Bindings;
import javafx.beans.binding.IntegerBinding;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author emori
 */
public class Character {

    private final IntegerBinding healthPointsBinding;

    private final ObservableList<Level> levels = FXCollections.observableArrayList();
    
    private final IntegerProperty healthPoints = new SimpleIntegerProperty();

    public Character() {
        healthPointsBinding = new IntegerBinding() {
            @Override
            protected int computeValue() {
                return levels.stream().mapToInt(level -> level.getClassDefinition().getHitDie().roll()).sum();
            }
        };
        healthPoints.bind(healthPointsBinding);
    }

    public int getHealthPoints() {
        return healthPoints.get();
    }
    
    public IntegerProperty getHealthPointsProperty() {
        return healthPoints;
    }
    
    public void addLevel(Level level) {
       levels.add(level);
       healthPointsBinding.invalidate();
    }
}
