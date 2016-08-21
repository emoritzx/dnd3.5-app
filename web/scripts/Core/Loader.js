define(
    [
        "jquery",
        "../Utils/Path"
    ],
    function(
        $,
        Path
    ) {
    
        "use strict";
        
        var EXTENSION_PATH = "Extensions";
        
        var resources = {
            attributes: "attributes.json",
            abilities:  "abilities.json",
            classes:    "classes.json",
            skills:     "skills.json",
            saves:      "saves.json",
            dice:       "dice.json",
            attacks:    "attacks.json"
        };
        
        var enhancement_types = {
            base: "enhancements.json",
            equipment: "equipment.json",
            levels: "levels.json",
            feats: "feats.json"
        };
        
        var Loader = new Object();
        
        Loader.getCharacterData = function(path) {
            var deferred = $.Deferred();
            $.when(
                Loader.getAttributeData(path),
                Loader.getAbilityData(path),
                Loader.getClassData(path),
                Loader.getSkillData(path),
                Loader.getSaveData(path),
                Loader.getEnhancementData(path),
                Loader.getDice(path),
                Loader.getAttackData(path)
            ).then(function(
                attributes,
                abilities,
                classes,
                skills,
                saves,
                enhancements,
                dice,
                attacks
            ) {
                var success = true;
                try
                {
                    var data = new Object();
                    data.attributes = attributes[0];
                    data.abilities = abilities[0];
                    data.classes = classes[0];
                    data.saves = saves[0];
                    data.enhancements = enhancements;
                    data.skills = skills[0];
                    data.dice = dice;
                    data.attacks = attacks[0];
                } catch (e) {
                    success = false;
                    console.error(e);
                }
                if (success) {
                    deferred.resolve(data);
                } else {
                    deferred.reject();
                }
            }).fail(function(jqXHR, status, error) {
                deferred.reject.apply(undefined, arguments);
            });
            return deferred.promise();
        };
        
        Loader.getAttributeData = function(path) {
            return loadResource(path, resources.attributes);
        };
        
        Loader.getAbilityData = function(path) {
            return loadResource(path, resources.abilities);
        };
        
        Loader.getClassData = function(path) {
            return loadResource(path, resources.classes);
        };
        
        Loader.getSaveData = function(path) {
            return loadResource(path, resources.saves);
        };
        
        Loader.getAttackData = function(path) {
            return loadResource(path, resources.attacks);
        };
        
        Loader.getSkillData = function(path) {
            return loadResource(path, resources.skills);
        };
        
        Loader.getEnhancementData = function(path) {
            var deferred = new $.Deferred();
            var list = [ ];
            for (var type in enhancement_types) {
                list.push(loadResource(path, enhancement_types[type]));
            }
            $.when.apply($, list).then(function() {
                var enhc = [ ];
                for (var a = 0; a < arguments.length; a++) {
                    var r = arguments[a][0];
                    Array.prototype.push.apply(enhc, r);
                }
                deferred.resolve(enhc);
            }).fail(function(jqXHR, status, error) {
                deferred.reject.apply(undefined, arguments);
            });
            return deferred.promise();
        };
        
        Loader.getDice = function(path) {
            var deferred = new $.Deferred();
            loadResource(path, resources.dice)
            .done(function(data) {
                var deferreds = [ ];
                $(data).each(function() {
                    var ext = new $.Deferred();
                    var name = this;
                    require([Path.join(EXTENSION_PATH, this + "Die")],
                        function(die) {
                            ext.resolve(name, die);
                        },
                        function(err) {
                            console.warn(err);
                            ext.reject(err);
                        }
                    );
                    deferreds.push(ext.promise());
                });
                $.when.apply($, deferreds)
                .then(function() {
                    var types = { };
                    for (var a = 0; a < arguments.length; a++) {
                        var r = arguments[a];
                        types[r[0]] = r[1];
                    }
                    deferred.resolve(types);
                }).fail(function(err) {
                    console.log(err);
                    deferred.reject(err);
                });
            })
            .fail(function(err) {
                deferred.reject(err);
            });
            return deferred.promise();
        };
        
        function loadResource(path, resource) {
            return $.ajax(
            {
                url: Path.join(path, resource),
                method: "GET",
                dataType: "json"
            }).fail(function(jqXHR, status, error) {
                console.warn(resource + " (" + status + ") " + error);
                console.debug(jqXHR);
            });
        }
        
        return Loader;
    }
);