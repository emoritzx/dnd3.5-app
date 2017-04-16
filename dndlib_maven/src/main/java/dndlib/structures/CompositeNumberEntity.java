/*
 * Think about the license.
 */
package dndlib.structures;

import dndlib.core.BonusType;
import dndlib.core.NumberedObservable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
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
public class CompositeNumberEntity implements NumberedObservable {
    
    private final Map<BonusType, List<ObservableNumberValue>> map = new HashMap<>();
    private final IntegerProperty value = new SimpleIntegerProperty();
    
    public CompositeNumberEntity(Set<BonusType> bonusTypes) {
        map.putAll(
            bonusTypes
            .stream()
            .collect(Collectors.toMap(
                Function.identity(),
                b -> new ArrayList<>()
            ))
        );
    }
    
    public CompositeNumberEntity(Map<BonusType, ? extends ObservableNumberValue> initialValues) {
        this(initialValues.keySet());
        initialValues.entrySet().stream()
            .forEach(entry -> addBonusValue(entry.getKey(), entry.getValue()));
    }
    
    public Set<BonusType> getBonusTypes() {
        return map.keySet();
    }

    public void addBonusValue(BonusType bonusType, ObservableNumberValue onv) {
        map.get(bonusType).add(onv);
        onv.addListener(listener);
        listener.changed(onv, 0, onv.getValue());
    }

    public void removeBonusValue(BonusType bonusType, ObservableNumberValue onv) {
        onv.removeListener(listener);
        map.get(bonusType).remove(onv);
        listener.changed(onv, onv.getValue(), 0);
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

    @Override
    public final int getValue() {
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
