define(
    [
        "../Utils/extend",
        "../Utils/Observable"
    ],
    function(
        extend,
        Observable
    ) {

        "use strict";

        var Attribute = function(name, value) {
            Observable.call(this);
            this._name = name;
            this._value = value;
        };

        extend(Attribute, Observable);

        Object.defineProperties(Attribute.prototype,
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
            value: {
                get: function() {
                    return this._value;
                },
                set: function(value) {
                    this._value = value;
                    this.notify();
                }
            }
        });

        Attribute.prototype.toString = function() {
            return this.name;
        };

        return Attribute;
    }
);