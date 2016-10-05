/*
 * Think about the license.
 */
package dndlib.character;

import javafx.beans.property.IntegerProperty;

/**
 *
 * @author emori
 */
public class Character {

    
    
    private final IntegerProperty healthPoints;

    public int getHealthPoints() {
        return healthPoints.get();
    }
    
    public IntegerProperty getHealthPointsProperty() {
        return healthPoints;
    }
    
    public void addLevel(Level level) {
        
    }
}
