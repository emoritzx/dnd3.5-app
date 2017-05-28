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
import java.io.InputStream;
import java.util.function.BiFunction;
import javax.json.Json;
import javax.json.JsonReader;

/**
 *
 * @author emori
 */
public class Loaders {

    public static Die noAdjustment(int level, int hitDie) {
        return new ConstantDie(hitDie);
    }

    public static Die standardAdjustment(int level, int hitDie) {
        return ConstantDie
            .levelAdjustment(size -> (int) Math.floor(size * 3.0 / 4))
            .apply(level, hitDie);
    }
    
    public static dndlib.character.Character getCharacter(String name, BiFunction<Integer, Integer, Die> levelAdjustment) {
        try (JsonReader stream = Loaders.getResource("characters", name)) {
            return JsonCharacter.from(stream.readObject(), Loaders::getRace, (className, level) -> getClassForLevel(className, level, levelAdjustment));
        }
    }
    
    public static JsonReader getResource(String type, String name) {
        String resource = String.format("/dndlib/data/%s/%s.json", type, name);
        InputStream stream = Loaders.class.getResourceAsStream(resource);
        if (stream == null) {
            throw new IllegalArgumentException("Error loading resource: " + resource);
        }
        return Json.createReader(stream);
    }
    
    public static ClassDefinition getClassForLevel(String name, int level, BiFunction<Integer, Integer, Die> levelAdjustment) {
        try (JsonReader stream = getResource("classes", name)) {
            return new JsonClassDefinition(stream.readObject(), levelAdjustment).getClassForLevel(level);
        }
    }
    
    public static Enhancement getRace(String name) {
        try (JsonReader stream = getResource("races", name)) {
            return new JsonEnhancement(stream.readObject());
        }
    }
}
