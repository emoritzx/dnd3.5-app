/*
 * Think about the license.
 */
package dndlib.core;

import dndlib.character.ArmorClass;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 * @author emori
 */
public final class CompositeAttributeBuilder {
    
    private final String name;
    private final List<BonusType> bonusTypes = new ArrayList<>();

    private CompositeAttributeBuilder(String name) {
        this.name = name;
    }
    
    public CompositeAttributeBuilder add(BonusType bonusType) {
        bonusTypes.add(bonusType);
        return this;
    }
    
    public CompositeAttribute build() {
        return new CompositeAttribute(name) {
            @Override
            public Collection<BonusType> getBonusTypes() {
                return new ArrayList<>(bonusTypes);
            }
        };
    }
    
    public static CompositeAttributeBuilder create(String name) {
        return new CompositeAttributeBuilder(name);
    }
    
    public static CompositeAttribute defaults() {
        return defaults("Bonuses");
    }
    
    public static CompositeAttribute defaults(String name) {
        CompositeAttributeBuilder builder = new CompositeAttributeBuilder(name);
        Arrays
            .stream(BonusType.Defaults.values())
            .map(BonusType.Defaults::getBonusType)
            .forEach(builder::add);
        return builder.build();
    }
}
