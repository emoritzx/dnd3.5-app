/*
 * Think about the license.
 */
package dndlib.example;

import dndlib.views.text.CharacterTextView;
import java.io.PrintWriter;

/**
 *
 * @author emori
 */
public final class FavoredSoul {
    
    public static void main(String[] args) {

        // create character
        dndlib.character.Character raoul = Loaders.getCharacter("raoul");
        
        // set up output view
        try (PrintWriter writer = new PrintWriter(System.out)) {
            
            CharacterTextView view = new CharacterTextView(raoul, writer);
            view.printAbilities(writer);

            // damage raoul
            raoul.damage(12, 8, "piercing");
        }
    }
}
