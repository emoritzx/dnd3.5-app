/*
 * Think about the license.
 */
package dndlib.character;

import dndlib.core.Entity;
import dndlib.dice.Die;
import java.util.Set;

/**
 *
 * @author emori
 */
public class ClassDefinition extends Entity {

    private final Die hitDie;
    private final int skillModifier;
    private final Set<String> skills;
    
    public ClassDefinition(
            String name,
            Die hitDie,
            int skillModifier,
            Set<String> skills)
    {
        super(name);
        this.hitDie = hitDie;
        this.skillModifier = skillModifier;
        this.skills = skills;
    }
    
    public Die getHitDie() {
        return hitDie;
    }
    
    public int getSkillModifier()
    {
        return skillModifier;
    }
    
    public Set<String> getSkills() {
        return skills;
    }
}
