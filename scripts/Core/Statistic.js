define(
    ["../Utils/lib", "../Utils/Observable"],
    function(Utils, Observable) {
    
        "use strict";
        
        var Statistic = function(name, ability) {
            Observable.call(this);
            this.name = name;
            this.ability = ability;
            this.bonus = 0;
        };
        
        Utils.extend(Statistic, Observable);
        
        Object.defineProperties(Statistic.prototype,
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
            ability: {
                get: function() {
                    return this._ability;
                },
                set: function(value) {
                    this._ability = value;
                    var closure = this;
                    Utils.observe(value, function(o) {
                        closure.notify();
                    });
                    this.notify();
                }
            },
            value: {
                get: function() {
                    return this.ability.modifier + this.bonus;
                }
            },
            bonus: {
                get: function() {
                    return this._bonus;
                },
                set: function(value) {
                    this._bonus = value || 0;
                    this.notify();
                }
            },
        });
        
        Statistic.prototype.toString = function() {
            return this.name;
        };
        
        return Statistic;
    }
);