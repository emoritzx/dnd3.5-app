define(
    [ "../Utils/lib", "../Utils/Observable", "../Utils/GetSetMap" ],
    function(Utils, Observable, GetSetMap) {
    
        "use strict";
        
        var Class = function(name, options) {
            Observable.call(this);
            this.name = name;
            options = options || { };
            this.hitDie = options.hitDie || 0;
            this.skillModifier = options.skillModifier || 0;
            createSkills.call(this, options.skills);
        };
        
        // extend Observable
        Utils.extend(Class, Observable);
        
        // define getters / setters
        Object.defineProperties(Class.prototype,
        {
            name: {
                get: function() {
                    return this._name;
                },
                set: function(value) {
                    this._name = value;
                    this.notify();
                }
            },
            hitDie: {
                get: function() {
                    return this._hitDie;
                },
                set: function(value) {
                    this._hitDie = parseInt(value);
                    this.notify();
                }
            },
            skillModifier: {
                get: function() {
                    return this._skillModifier;
                },
                set: function(value) {
                    this._skillModifier = parseInt(value);
                    this.notify();
                }
            },
            skills: {
                get: function() {
                    return this._skills;
                }
            }
        });
        
        Class.prototype.toString = function() {
            return this.name;
        };
        
        function createSkills(skills) {
            skills = skills || { };
            var map = new GetSetMap("skills", Observable.Boolean);
            this._skills = map;
            for (var name in skills) {
                var value = skills[name];
                map.set(name, new Observable.Boolean(value));
            }
        }
        
        return Class;
    }
);