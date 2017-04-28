/**
 * Copyright (c) 2016-2017, Evan Moritz.
 * Licensed under the MIT License. See the accompanying LICENSE file for terms.
 */
package dndlib.views.text;

import java.io.PrintWriter;

/**
 *
 * @author emori
 */
public class CharacterTextView {

    private final dndlib.character.Character character;

    public CharacterTextView(dndlib.character.Character c, PrintWriter eventStream) {
        this.character = c;
        // add HP listener
        c.getHitPoints().valueProperty().addListener((obs, oldValue, newValue) -> eventStream.printf("%d -> %d HP%n", oldValue, newValue));
    }
    
    public CharacterTextView printAbilities(PrintWriter stream) {
        character.getAbilities().values().forEach(stream::println);
        return this;
    }

    public CharacterTextView printSkills(PrintWriter stream) {
        character.getSkills().values().forEach(stream::println);
        return this;
    }
}
