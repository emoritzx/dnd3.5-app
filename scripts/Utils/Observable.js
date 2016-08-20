define(
    [ "./extend" ],
    function(extend) {

    "use strict";
    
    // base

    var Observable = function() {
        this._listeners = [ ];
    };

    Observable.prototype.addListener = function(callback, context) {
        context = context || this;
        this._listeners.push({
            callback: callback,
            context: context
        });
    };

    Observable.prototype.notify = function() {
        var listeners = this._listeners;
        for (var i = 0; i < listeners.length; i++) {
            var listener = listeners[i];
            listener.callback.apply(listener.context, arguments);
        }
    };
    
    // Boolean
    
    Observable.Boolean = function(value) {
        Observable.call(this);
        this.value = (value) ? true : false;
    };
    
    extend(Observable.Boolean, Observable);
    
    Object.defineProperties(Observable.Boolean.prototype,
    {
        value: {
            get: function() {
                return this._value;
            },
            set: function(value) {
                this._value = (value) ? true : false;
                this.notify();
            }
        }
    });
    
    Observable.Boolean.prototype.toString = function() {
        return this.value.toString();
    };
    
    // Number
    
    Observable.Number = function(value) {
        Observable.call(this);
        this.value = new Number(value);
    };
    
    extend(Observable.Number, Observable);
    
    Object.defineProperties(Observable.Number.prototype,
    {
        value: {
            get: function() {
                return this._value;
            },
            set: function(value) {
                this._value = new Number(value);
                this.notify();
            }
        }
    });
    
    Observable.Number.prototype.toString = function() {
        return this.value.toString();
    };
    
    // Object
    
    Observable.Object = function(values) {
        Observable.call(this);
        this._cache = { };
        if (typeof(values) === "object") {
            var keys = Object.keys(values);
            for (var i = 0; i < keys.length; i++) {
                var key = keys[i];
                this._cache[key] = values[key];
            }
        }
    };
    
    extend(Observable.Object, Observable);
    
    Object.defineProperties(Observable.Object.prototype,
    {
        keys: {
            get: function() {
                return Object.keys(this._cache);
            }
        }
    });
    
    Observable.Object.prototype.get = function(name) {
        return this._cache[name];
    };
    
    Observable.Object.prototype.set = function(name, value) {
        this._cache[name] = value;
        this.notify("set", name, value);
    };
    
    // done
    
    return Observable;
});