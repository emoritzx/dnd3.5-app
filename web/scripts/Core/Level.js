define(
    [ "../Utils/lib", "./Enhancement" ],
    function(Utils, Enhancement) {
    
        "use strict";
        
        var Level = function(classDef, data) {
            Enhancement.call(this, data);
            this.classDef = classDef;
        };
        
        // extend Observable
        Utils.extend(Level, Enhancement);
        
        // define getters / setters
        Object.defineProperties(Level.prototype,
        {
            classDef: {
                get: function() {
                    return this._classDef;
                },
                set: function(value) {
                    this._classDef = value;
                    this.notify();
                }
            }
        });
        
        Level.prototype.sumRanks = function() {
            var amount = 0;
            var skills = this.skills.keys;
            for (var s = 0; s < skills.length; s++) {
                var name = skills[s];
                var skill = this.skills.get(name);
                amount += skill;
            }
            return amount;
        };
        
        return Level;
    }
);