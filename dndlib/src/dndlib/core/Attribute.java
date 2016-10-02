package dndlib.core;

import java.util.function.Function;

/**
 *
 * @author emori
 */
public class Attribute {

    private final String name;
    private final String abbreviation;

    public Attribute(String name) {
        this(name, Function.identity());
    }
    
    public Attribute(String name, Function<String, String> abbreviator)
    {
        this(name, abbreviator.apply(name));
    }

    public Attribute(String name, String abbreviation) {
        this.name = name;
        this.abbreviation = abbreviation;
    }

    public final String getAbbreviation() {
        return abbreviation;
    }

    public final String getName() {
        return name;
    }

    @Override
    public String toString() {
        return name;
    }
    
    
}
