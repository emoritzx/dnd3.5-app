define(
    ["../Core/Die", "../Utils/lib"],
    function(Die, Utils) {
    
        "use strict";
        
        var DESCRIPTION = "A die that rolls the same number... always.";
        
        var ConstantDie = function(size, effect) {
            Die.call(this, size, effect);
            this._description = DESCRIPTION;
        };
        
        Utils.extend(ConstantDie, Die);
        
        ConstantDie.prototype._calculate = function() {
            return this.size;
        };
        
        ConstantDie.prototype.toString = function() {
            var flavor = (this.effect) ? " " + this.effect : "";
            return this.size + flavor;
        };
        
        return ConstantDie;
    }
);