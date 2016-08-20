define(
    ["../Core/Die", "../Utils/lib"],
    function(Die, Utils) {
    
        "use strict";
        
        var DESCRIPTION = "When it hits, it HITS! This die grant an additional roll when it rolls maximum value.";
                
        var ExplodingDie = function(size, effect) {
            Die.call(this, size, effect);
            this._description = DESCRIPTION;
        };
        
        Utils.extend(ExplodingDie, Die);
        
        ExplodingDie.prototype.isExploding = function(roll) {
            return roll === this.size;
        };
        
        ExplodingDie.prototype._calculate = function() {
            var rolls = [];
            var roll;
            console.log(Die);
            do
            {
                roll = Die.prototype._calculate.call(this);
                rolls.push(roll);
            } while (this.isExploding(roll));
            return rolls;
        };
        
        ExplodingDie.prototype.toString = function() {
            var flavor = (this.effect) ? " " + this.effect : "";
            return "d" + this.size + " (exploding)" + flavor;
        };
        
        return ExplodingDie;
    }
);