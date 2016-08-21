define(
    [ "../Utils/lib", "../Utils/Observable" ],
    function(Utils, Observable) {
    
        "use strict";
        
        function update(skill) {
            skill._value = calculate(skill);
            skill.notify();
        }
        
        function calculate(skill) {
            return skill.ability.modifier
                 + skill.ranks
                 + Math.floor(skill.crossRanks / 2)
                 + skill.bonus;
        }
        
        var Skill = function(name, ability, options) {
            Observable.call(this);
            this.name         = name;
            this.ability      = ability;
            options           = options || { };
            this.abbr         = options.abbr;
            this.ranks        = options.ranks || 0;
            this.crossRanks   = options.crossRanks || 0;
            this.bonus        = options.bonus || 0;
            this.trainedOnly  = options.trainedOnly || false;
            this.armorPenalty = options.armorPenalty || false;
            this._value       = calculate(this);
        };
        
        // extend Observable
        Utils.extend(Skill, Observable);
        
        // define getters / setters
        Object.defineProperties(Skill.prototype,
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
            abbr: {
                get: function() {
                    return this._abbr;
                },
                set: function(value) {
                    this._abbr = value;
                    this.notify();
                }
            },
            ability: {
                get: function() {
                    return this._ability;
                },
                set: function(value) {
                    if (this._abilityListener) {
                        this.ability.removeListener(this._abilityListener);
                    }
                    this._ability = value;
                    var closure = this;
                    this._abilityListener = function(o) {
                        update(closure);
                    };
                    Utils.observe(value, this._abilityListener);
                    update(this);
                }
            },
            ranks: {
                get: function() {
                    return this._ranks;
                },
                set: function(value) {
                    this._ranks = value || 0;
                    update(this);
                }
            },
            crossRanks: {
                get: function() {
                    return this._cross;
                },
                set: function(value) {
                    this._cross = value || 0;
                    update(this);
                }
            },
            bonus: {
                get: function() {
                    return this._bonus;
                },
                set: function(value) {
                    this._bonus = value || 0;
                    update(this);
                }
            },
            trained: {
                get: function() {
                    return (this.ranks >= 1 || this.crossRanks >= 2);
                }
            },
            armorPenalty: {
                get: function() {
                    return this._armorPenalty;
                },
                set: function(value) {
                    this._armorPenalty = (value) ? true : false;
                    update(this);
                }
            },
            trainedOnly: {
                get: function() {
                    return this._trainedOnly;
                },
                set: function(value) {
                    this._trainedOnly = (value) ? true : false;
                    this.notify();
                }
            },
            value: {
                get: function() {
                    return this._value;
                }
            }
        });
        
        Skill.prototype.toString = function() {
            return this.name;
        };
        
        return Skill;
    }
);