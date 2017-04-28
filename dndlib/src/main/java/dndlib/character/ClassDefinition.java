/**
 * Copyright (c) 2016-2017, Evan Moritz.
 * Licensed under the MIT License. See the accompanying LICENSE file for terms.
 */
package dndlib.character;

import dndlib.core.Enhancement;
import dndlib.structures.NameEntity;
import dndlib.dice.Die;
import java.util.Set;

/**
 *
 * @author emori
 */
public class ClassDefinition extends NameEntity {

    private final Die hitDie;
    private final int skillModifier;
    private final Set<String> skills;
    private final Enhancement enhancements;
    
    public ClassDefinition(
            String name,
            Die hitDie,
            int skillModifier,
            Set<String> skills,
            Enhancement enhancements)
    {
        super(name);
        this.hitDie = hitDie;
        this.skillModifier = skillModifier;
        this.skills = skills;
        this.enhancements = enhancements;
    }

    public Enhancement getEnhancements() {
        return enhancements;
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
