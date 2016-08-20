define(
    [ "./DeveloperError" ],
    function(DeveloperError) {

        "use strict";

        var GetSetMap = function(name, type) {
            this._name = name;
            this._type = type;
            this._map = { };
        };

        GetSetMap.prototype.check = function(value) {
            if (!(value instanceof this._type))
                throw new DeveloperError("Invalid type");
        };

        GetSetMap.prototype.get = function(key) {
            if (!this._map.hasOwnProperty(key)) {
                return null;
            } else {
                return this._map[key];
            }
        };

        GetSetMap.prototype.set = function(key, value) {
            this.check(value);
            this._map[key] = value;
        };

        GetSetMap.prototype.add = function(value) {
            this.set(value.toString(), value);
        };

        Object.defineProperty(GetSetMap.prototype, "keys", {
            get: function() {
                return Object.keys(this._map);
            }
        });

        return GetSetMap;
    }
);