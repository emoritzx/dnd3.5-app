define(
    ["../Utils/lib", "../Utils/Observable"],
    function(Utils, Observable) {
    
        "use strict";
        
        var DEFAULT_TYPE = "Die";
        var DEFAULT_DESCRIPTION = "A fair die that selects randomly from a uniform distribution.";
        var DEFAULT_SIZE = 6;
        var MIN = 1;
        
        var Die = function(size, effect) {
            Observable.call(this);
            this.size = size || DEFAULT_SIZE;
            this.effect = effect;
            this._description = DEFAULT_DESCRIPTION;
            this._type = DEFAULT_TYPE;
        };
        
        Utils.extend(Die, Observable);
        
        Object.defineProperties(Die.prototype,
        {
            size: {
                get: function() {
                    return this._size;
                },
                set: function(value) {
                    this._size = Math.abs(value || 0);
                    this.notify("size", this.size);
                }
            },
            effect: {
                get: function() {
                    return this._effect;
                },
                set: function(value) {
                    this._effect = value
                    this.notify("effect", value);
                }
            },
            description: {
                get: function() {
                    return this._description;
                }
            },
            type: {
                get: function() {
                    return this._type;
                }
            }
        });
        
        Die.prototype._calculate = function() {
            return Math.floor(Math.random() * (this.size - MIN + 1)) + MIN;
        };
        
        Die.prototype.roll = function() {
            var roll = this._calculate();
            this.notify("roll", roll);
            return roll;
        };
        
        Die.prototype.toString = function() {
            var flavor = (this.effect) ? " " + this.effect : "";
            return "d" + this.size + flavor;
        };
        
        return Die;
    }
);