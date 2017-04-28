/**
 * Copyright (c) 2016-2017, Evan Moritz.
 * Licensed under the MIT License. See the accompanying LICENSE file for terms.
 */
package dndlib.core;

import java.util.Map;

/**
 *
 * @author emori
 */
public interface Enhancement {

    public Map<String, Integer> getSkills();

    public Map<String, Integer> getAbilities();

    public Map<String, Integer> getArmor();

    public int getBaseAttackBonus();

    public Map<String, Integer> getSaves();

    public String getSlot();
}
