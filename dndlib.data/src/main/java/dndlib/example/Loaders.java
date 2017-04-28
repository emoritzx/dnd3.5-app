/**
 * Copyright (c) 2016-2017, Evan Moritz.
 * Licensed under the MIT License. See the accompanying LICENSE file for terms.
 */
package dndlib.example;

import dndlib.character.ClassDefinition;
import dndlib.core.Enhancement;
import dndlib.dice.ConstantDie;
import dndlib.dice.Die;
import dndlib.json.JsonCharacter;
import dndlib.json.JsonClassDefinition;
import dndlib.json.JsonEnhancement;
import javax.json.Json;
import javax.json.JsonReader;

/**
 *
 * @author emori
 */
public class Loaders {
    
    public static Die levelAdjustment(int level, int hitDie) {
        return ConstantDie
            .levelAdjustment(size -> (int) Math.floor(size * 3.0 / 4))
            .apply(level, hitDie);
    }
    
    public static dndlib.character.Character getCharacter(String name) {
        try (JsonReader stream = Loaders.getResource("characters", name)) {
            return JsonCharacter.from(stream.readObject(), Loaders::getRace, Loaders::getClassForLevel);
        }
    }
    
    public static JsonReader getResource(String type, String name) {
        return Json.createReader(Loaders.class.getResourceAsStream("/dndlib/data/" + type + "/" + name + ".json"));
    }
    
    public static ClassDefinition getClassForLevel(String name, int level) {
        try (JsonReader stream = getResource("classes", name)) {
            return new JsonClassDefinition(stream.readObject(), Loaders::levelAdjustment).getClassForLevel(level);
        }
    }
    
    public static Enhancement getRace(String name) {
        try (JsonReader stream = getResource("races", name)) {
            return new JsonEnhancement(stream.readObject());
        }
    }
}
