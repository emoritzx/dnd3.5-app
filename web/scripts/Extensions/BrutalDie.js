define(
    ["../Core/Die", "../Utils/lib"],
    function(Die, Utils) {
    
        "use strict";
        
        var DESCRIPTION = "Rolling low is brutal. So roll another to make up for it!";
        
        var BrutalDie = function(size, effect, brutal) {
            Die.call(this, size, effect);
            this._description = DESCRIPTION;
            this.brutal = brutal;
        };
        
        Utils.extend(BrutalDie, Die);
        
        Object.defineProperties(BrutalDie.prototype,
        {
            brutal: {
                get: function() {
                    return this._brutal;
                },
                set: function(value) {
                    this._brutal = Math.abs(value || 0);
                    this.notify("brutal");
                }
            }
        });
        
        BrutalDie.prototype.isBrutal = function(roll) {
            return roll <= this.brutal;
        };
        
        BrutalDie.prototype._calculate = function() {
            var rolls = [];
            var roll;
            do
            {
                roll = Die.prototype._calculate.call(this);
                rolls.push(roll);
            } while (this.isBrutal(roll));
            return rolls;
        };
        
        BrutalDie.prototype.toString = function() {
            var flavor = (this.effect) ? " " + this.effect : "";
            return "d" + this.size + " (brutal " + this.brutal + ")" + flavor;
        };
        
        return BrutalDie;
    }
);