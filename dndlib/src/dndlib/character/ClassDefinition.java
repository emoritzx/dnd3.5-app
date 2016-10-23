/*
 * Think about the license.
 */
package dndlib.character;

import dndlib.core.ScalableDefinition;
import dndlib.core.Enhancement;
import dndlib.structures.NameEntity;
import dndlib.dice.Die;
import java.util.Set;

/**
 *
 * @author emori
 */
public class ClassDefinition extends NameEntity implements ScalableDefinition {

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

    @Override
    public Enhancement getEntry(int level) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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
