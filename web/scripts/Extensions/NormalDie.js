define(
    ["../Utils/lib", "../Core/Die"],
    function(Utils, Die) {
    
        "use strict";
        
        var NormalDie = function(size, effect) {
            Die.call(this, size, effect);
        };
        
        Utils.extend(NormalDie, Die);

        return NormalDie;
    }
);