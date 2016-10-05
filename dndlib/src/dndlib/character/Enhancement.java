/*
 * Think about the license.
 */
package dndlib.character;

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
