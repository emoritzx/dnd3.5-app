/*
 * Think about the license.
 */
package dndlib.core;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

/**
 *
 * @author emori
 */
public final class CompositeNumberBuilder {
    
    private final String name;
    private final List<BonusType> bonusTypes = new ArrayList<>();

    private CompositeNumberBuilder(String name) {
        this.name = name;
    }
    
    public CompositeNumberBuilder add(BonusType bonusType) {
        bonusTypes.add(bonusType);
        return this;
    }
    
    public CompositeNumberEntity build() {
        return new CompositeNumberEntity(name) {
            @Override
            public Collection<BonusType> getBonusTypes() {
                return new ArrayList<>(bonusTypes);
            }
        };
    }
    
    public static CompositeNumberBuilder create(String name) {
        return new CompositeNumberBuilder(name);
    }
    
    public static CompositeNumberEntity defaults() {
        return defaults("Bonuses");
    }
    
    public static CompositeNumberEntity defaults(String name) {
        CompositeNumberBuilder builder = new CompositeNumberBuilder(name);
        Arrays
            .stream(BonusType.Defaults.values())
            .map(BonusType.Defaults::getBonusType)
            .forEach(builder::add);
        return builder.build();
    }
}
