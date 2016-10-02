/*
 * Think about the license.
 */
package dndlib.core;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableNumberValue;

/**
 *
 * @author emori
 */
public abstract class CompositeAttribute extends ValueAttribute {
    
    private final Map<BonusType, List<ObservableNumberValue>> map = new HashMap<>();
    private final IntegerProperty value = new SimpleIntegerProperty();

    public CompositeAttribute(String name, Function<String, String> abbreviator) {
        super(name, abbreviator);
        init();
    }

    public CompositeAttribute(String name, String abbreviation) {
        super(name, abbreviation);
        init();
    }

    public CompositeAttribute(String name) {
        super(name);
        init();
    }
    
    private void init() {
        map.putAll(
            getBonusTypes()
            .stream()
            .collect(Collectors.toMap(
                Function.identity(),
                b -> new ArrayList<>()
            ))
        );
    }
    
    public abstract Collection<BonusType> getBonusTypes();

    public void addBonusValue(BonusType bonusType, ObservableNumberValue onv) {
        map.get(bonusType).add(onv);
        onv.addListener(listener);
        listener.changed(onv, null, onv.getValue());
    }

    public void removeBonusValue(BonusType bonusType, ObservableNumberValue onv) {
        onv.removeListener(listener);
        map.get(bonusType).remove(onv);
    }

    private final ChangeListener<? super Number> listener = (a, b, c) -> {
        value.setValue(
            map
                .keySet()
                .stream()
                .mapToInt(this::getValue)
                .sum()
        );
    };

    public int getValue() {
        return value.get();
    }

    public int getValue(BonusType bonusType) {
        return map
            .get(bonusType)
            .stream()
            .mapToInt(ObservableNumberValue::intValue)
            .reduce(bonusType.getOperator())
            .orElse(0);
    }

    @Override
    public IntegerProperty valueProperty() {
        return value;
    }

}
