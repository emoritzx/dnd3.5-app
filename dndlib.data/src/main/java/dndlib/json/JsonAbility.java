/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dndlib.json;

import dndlib.character.Ability;
import dndlib.core.Named;
import dndlib.core.NamedHashMap;
import java.util.Map;
import javax.json.JsonArray;
import javax.json.JsonObject;

/**
 *
 * @author emori
 */
public class JsonAbility {

    /**
     * 
     * @param json
     * @return 
     */
    public static Map<String, Ability> fromShortForm(JsonArray json) {
        return JsonList
            .fromStringArray(
                json,
                name -> new Ability(Named.create(name.getString(), Ability::abbreviate)))
            .stream()
            .collect(NamedHashMap.collector());
    }
    
    /**
     * 
     * @param json
     * @return 
     */
    public static Map<String, Ability> fromLongForm(JsonObject json) {
        return JsonMap
            .fromNumber(
                json,
                (name, number) -> new Ability(
                    Named.create(name, Ability::abbreviate),
                    number.intValue())
            );
    }
}
