define(
    ["../Utils/lib", "./Statistic"],
    function(Utils, Statistic) {
    
        "use strict";
        
        var DEFAULT_NAME = "Armor Class";
        
        var bonus_types = [
            "class",
            "armor",
            "shield",
            "enhancement",
            "deflection",
            "dodge",
            "natural"
        ];
        
        var ArmorClass = function(ability) {
            Statistic.call(this, DEFAULT_NAME, ability);
            this._types = { };
            for (var b = 0; b < bonus_types.length; b++) {
                var bonus = bonus_types[b];
                this[bonus] = 0;
            }
            this.DR = 0;
        };
        
        Utils.extend(ArmorClass, Statistic);
        
        function defineBonus(type) {
            Object.defineProperty(ArmorClass.prototype, type,
            {
                get: function() {
                    return this._types[type];
                },
                set: function(value) {
                    this._types[type] = value || 0;
                    this.notify();
                }
            });
        }
        
        for (var b = 0; b < bonus_types.length; b++) {
            var type = bonus_types[b];
            defineBonus(type);
        }
        
        Object.defineProperties(ArmorClass.prototype,
        {
            value: {
                get: function() {
                    var base = this.ability.modifier + this.bonus;
                    for (var t = 0; t < bonus_types.length; t++) {
                        var type = bonus_types[t];
                        base += this[type];
                    }
                    return base;
                }
            },
            touch: {
                get: function() {
                    return this.value - this.armor - this.shield - this.natural;
                }
            },
            flat: {
                get: function() {
                    return this.value - this.ability.modifier;
                }
            },
            DR: {
                get: function() {
                    return this._dr;
                },
                set: function(value) {
                    this._dr = value || 0;
                    this.notify();
                }
            }
        });
        
        ArmorClass.BonusTypes = bonus_types;
        
        return ArmorClass;
    }
);