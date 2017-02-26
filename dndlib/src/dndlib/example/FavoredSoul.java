/*
 * Think about the license.
 */
package dndlib.example;

import dndlib.character.ClassDefinition;
import dndlib.character.Level;
import dndlib.json.JsonClassDefinition;
import java.util.List;
import javax.json.Json;
import javax.json.JsonReader;

/**
 *
 * @author emori
 */
public final class FavoredSoul {

    private final static List<ClassDefinition> LEVELS;
    
    static {
        try (JsonReader stream = Json.createReader(FavoredSoul.class.getResourceAsStream("/dndlib/data/classes/FavoredSoul.json"))) {
            LEVELS = JsonClassDefinition.from(stream.readObject());
        }
    }
    
    public static ClassDefinition getClassForLevel(int level) {
        return LEVELS.get(level - 1);
    }
    
    public static void main(String[] args) {
        dndlib.character.Character raoul = new dndlib.character.Character("Raoul Esteban Díaz");
        System.out.printf("%s (%s)%n", raoul.getName(), raoul.getAbbreviation());
        System.out.printf("%d HP%n", raoul.getHealthPoints());
        raoul.addLevel(Level.create(getClassForLevel(1)));
        System.out.printf("%d HP%n", raoul.getHealthPoints());
        raoul.addLevel(Level.create(getClassForLevel(1)));
        System.out.printf("%d HP%n", raoul.getHealthPoints());
        raoul.addLevel(Level.create(getClassForLevel(1)));
        System.out.printf("%d HP%n", raoul.getHealthPoints());
    }
}
