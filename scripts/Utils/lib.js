define(
    [
        "./DeveloperError",
        "./extend",
        "./mixin",
        "./Observable"
    ],
    function(
        DeveloperError,
        extend,
        mixin,
        Observable
    ) {

        "use strict";

        var lib = {

            extend: extend,

            mixin: mixin,

            observe: function(observable, callback) {
                if (observable instanceof Observable) {
                    observable.addListener(callback);
                } else {
                    console.warn("Object is not observable");
                }
            },
            
            formatModifier: function(modifier) {
                if (modifier >= 0) {
                    return "+" + modifier;
                }
                return modifier;
            },
            
            construct: function(constructor, args) {
                function F() {
                    return constructor.apply(this, args);
                }
                F.prototype = constructor.prototype;
                return new F();
            }
        };

        return lib;
    }
);