/*
 * Think about the license.
 */
package dndlib.character;

import dndlib.core.Named;
import dndlib.core.NumberedObservable;
import javafx.beans.binding.Bindings;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;

/**
 *
 * @author emori
 */
public class Skill implements Named, NumberedObservable {

    private final boolean armorPenalty;
    private final IntegerProperty crossRanks = new SimpleIntegerProperty();
    private final Named name;
    private final IntegerProperty ranks = new SimpleIntegerProperty();
    private final BooleanProperty trained = new SimpleBooleanProperty();
    private final boolean trainedOnly;
    private final IntegerProperty value = new SimpleIntegerProperty();

    public Skill(
        Named name,
        Ability ability,
        NumberedObservable bonus,
        boolean trainedOnly,
        boolean armorPenalty)
    {
        this.name = name;
        this.armorPenalty = armorPenalty;
        this.trainedOnly = trainedOnly;
        trained.bind(
            Bindings.or(
                Bindings.greaterThanOrEqual(ranks, 1),
                Bindings.greaterThanOrEqual(crossRanks, 2)
            )
        );
        value.bind(
            ability.modifierProperty()
            .add(bonus.valueProperty())
            .add(ranks)
            .add(Bindings.divide(crossRanks, 2))
        );
    }

    @Override
    public final String getAbbreviation() {
        return name.getAbbreviation();
    }

    @Override
    public final String getName() {
        return name.getName();
    }

    @Override
    public final int getValue() {
        return value.intValue();
    }

    @Override
    public IntegerProperty valueProperty() {
        return value;
    }

    public boolean hasArmorPenalty() {
        return armorPenalty;
    }

    public boolean isTrainedOnly() {
        return trainedOnly;
    }

    public boolean isTrained() {
        return trained.get();
    }

    public int getRanks() {
        return ranks.get();
    }

    public void addRanks(int num) {
        ranks.set(ranks.get() + num);
    }

    public void addCrossRanks(int num) {
        crossRanks.set(crossRanks.get() + num);
    }

    public int getCrossRanks() {
        return crossRanks.get();
    }

    // properties
    
    public IntegerProperty ranksProperty() {
        return ranks;
    }

    public IntegerProperty crossRanksProperty() {
        return crossRanks;
    }

    public BooleanProperty trainedProperty() {
        return trained;
    }
}
