define(
    [],
    function() {
        
        "use strict";
        
        var Functor = function(callback, instance) {
            this._instance = instance;
            return callback;
        };
        
        Object.defineProperties(Functor.prototype,
        {
            instance: {
                get: function() {
                    return this._instance;
                }
            }
        });
        
        return Functor;
    }
);