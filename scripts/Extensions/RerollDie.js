define(
    ["../Core/Die", "../Utils/lib"],
    function(Die, Utils) {
    
        "use strict";
        
        var DESCRIPTION = "This die rerolls on specific values.";
                
        var RerollDie = function(size, effect, rerolls) {
            Die.call(this, size, effect);
            this._description = DESCRIPTION;
            this._rerolls = rerolls;
        };
        
        Utils.extend(RerollDie, Die);
        
        Object.defineProperties(RerollDie.prototype,
        {
            rerolls: {
                get: function() {
                    return this._rerolls;
                }
            }
        });
        
        RerollDie.prototype.canReroll = function(roll) {
            for (var i = 0; i < this.rerolls.length; i++) {
                if (roll === this.rerolls[i]) {
                    return true;
                }
            }
            return false;
        };
        
        RerollDie.prototype._calculate = function() {
            var roll;
            do
            {
                roll = Die.prototype._calculate.call(this);
            } while (this.canReroll(roll));
            return roll;
        };
        
        RerollDie.prototype.toString = function() {
            var flavor = (this.effect) ? " " + this.effect : "";
            return "d" + this.size + " (reroll " + this.rerolls.join(",") + ")" + flavor;
        };
        
        return RerollDie;
    }
);