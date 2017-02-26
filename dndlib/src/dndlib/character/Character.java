/*
 * Think about the license.
 */
package dndlib.character;

import dndlib.core.Named;
import java.util.Arrays;
import java.util.stream.Collectors;
import javafx.beans.binding.IntegerBinding;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author emori
 */
public class Character implements Named {

    private final IntegerBinding healthPointsBinding;

    private final ObservableList<Level> levels = FXCollections.observableArrayList();
    
    private final IntegerProperty healthPoints = new SimpleIntegerProperty();
    private final String name;

    public Character(String name) {
        this.name = name;
        healthPointsBinding = new IntegerBinding() {
            @Override
            protected int computeValue() {
                return levels.stream().mapToInt(level -> level.getClassDefinition().getHitDie().roll()).sum();
            }
        };
        healthPoints.bind(healthPointsBinding);
    }

    @Override
    public String getAbbreviation() {
        return Arrays
            .stream(name.split("\\s+"))
            .map(word -> String.valueOf(java.lang.Character.toUpperCase(word.charAt(0))))
            .collect(Collectors.joining());
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

    @Override
    public String getName() {
        return name;
    }
}
