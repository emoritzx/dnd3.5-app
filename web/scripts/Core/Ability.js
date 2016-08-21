define(
    [
        "../Utils/lib",
        "../Utils/Observable",
    ],
    function(
        Utils,
        Observable
    ) {
    
        "use strict";
        
        var Ability = function(name, value, abbr) {
            Observable.call(this);
            this.bonus = 0;
            this.name = name;
            this.abbr = abbr || calcAbbr(name);
            this.baseValue = value;
            this._gains = { };
        };
        
        // extend Observable
        Utils.extend(Ability, Observable);
        
        // define getters / setters
        Object.defineProperties(Ability.prototype,
        {
            name: {
                get: function() {
                    return this._name;
                },
                set: function(value) {
                    this._name = value;
                    this._abbr = calcAbbr(value);
                    this.notify();
                }
            },
            baseValue: {
                get: function() {
                    return this._baseValue;
                },
                set: function(value) {
                    this._baseValue = value || 0;
                    this.notify();
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
            value: {
                get: function() {
                    return this.baseValue + this.gained + this.bonus;
                }
            },
            gained: {
                get: function() {
                    return sumGains(this._gains, maxLevel(this._gains));
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
            modifier: {
                get: function() {
                    return Ability.getMod(this.value);
                }
            }
        });
        
        Ability.prototype.gain = function(level, amount) {
            this._gains[level] = amount || 0;
            this.notify();
        };
        
        Ability.prototype.valueAt = function(level) {
            return this.baseValue + sumGains(this._gains, level);
        };
        
        Ability.prototype.toString = function() {
            return this.name;
        };
        
        Ability.getMod = function(value) {
            return Math.floor((value - 10) / 2);
        }
        
        function calcAbbr(name) {
            if (name.length <= 3) {
                return name;
            }
            return name.substr(0, 3).toUpperCase();
        }
        
        function sumGains(gains, level) {
            level = parseInt(level);
            if (level === 0) {
                return 0;
            }
            if (isNaN(level)) {
                throw new Error("Level must be a number (" + level + " given)");
            }
            var gain = gains[level.toString()];
            gain = gain || 0;
            return gain + sumGains(gains, level - 1);
        }
        
        function maxLevel(gains) {
            var keys = Object.keys(gains);
            if (!keys.length)
            {
                return 0;
            }
            keys.sort(compareNumbers);
            return parseInt(keys[0]);
        }
        
        function compareNumbers(a, b) {
            return parseInt(b) - parseInt(a);
        }
        
        return Ability;
    }
);