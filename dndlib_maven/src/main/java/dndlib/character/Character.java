/*
 * Think about the license.
 */
package dndlib.character;

import dndlib.core.Enhancement;
import dndlib.core.Named;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author emori
 */
public class Character implements Named {
    
    public static final String HEALTH_ABILITY = "Constitution";

    private final Map<String, Ability> abilities;
    private final HitPoints hp;
    private final ObservableList<Level> levels = FXCollections.observableArrayList();
    private final String name;
    private final Map<String, Skill> skills = new HashMap<>();

    public Character(String name, Map<String, Ability> abilities, Enhancement race) {
        this.name = name;
        this.abilities = Collections.unmodifiableMap(abilities);
        applyRace(race);
        this.hp = new HitPoints(abilities.get(HEALTH_ABILITY), levels);
//        levels.addListener(() -> );
    }

    private void applyRace(Enhancement race) {
        race
            .getAbilities()
            .forEach((ability, value) -> abilities.get(ability).adjust(value));
    }

    public void damage(int hit, int damage, String... types) {
        hp.damage(damage);
    }

    @Override
    public String getAbbreviation() {
        return Arrays
            .stream(name.split("\\s+"))
            .map(word -> String.valueOf(java.lang.Character.toUpperCase(word.charAt(0))))
            .collect(Collectors.joining());
    }
    
    public HitPoints getHitPoints() {
        return hp;
    }
    
    public void addLevel(Level level) {
       levels.add(level);
    }

    @Override
    public String getName() {
        return name;
    }
    
    public Map<String, Ability> getAbilities() {
        return abilities;
    }
    
    public Map<String, Skill> getSkills() {
        return skills;
    }
}
