define(
    [
        "jquery",
        "../Utils/lib",
        "../Utils/Observable"
    ],
    function(
        $,
        Utils,
        Observable
    ) {
    
        "use strict";
        
        var DiceBag = function(types) {
            Observable.call(this);
            this._collection = [];
            this._types = types;
        };
        
        Utils.extend(DiceBag, Observable);
        
        Object.defineProperties(DiceBag.prototype,
        {
            types: {
                get: function() {
                    return this._types;
                }
            }
        });
        
        DiceBag.prototype.create = function(name, size, effect, args) {
            var type = this.types[name];
            var a = [ size, effect ];
            if (args)
                Array.prototype.push.apply(a, args);
            var die = Utils.construct(type, a);
            return die;
        };
        
        DiceBag.prototype.addListener = function(callback, context) {
            Observable.prototype.addListener.call(this, callback, context);
            var length = this._collection.length;
            for (var i = 0; i < length; i++) {
                callback.call(context, "added", this._collection[i]);
            }
        };
        
        DiceBag.prototype.empty = function() {
            this._collection = [];
            this.notify("empty");
        };
        
        DiceBag.prototype.add = function(die) {
            this._collection.push(die);
            this.notify("added", die);
        };
        
        DiceBag.prototype.remove = function(die) {
            var index = this.collection.indexOf(die);
            if (index !== -1)
            {
                this._collection.splice(index, 1);
                this.notify("removed", index);
            }
        };
        
        DiceBag.prototype.roll = function() {
            var total = 0;
            $(this._collection).each(function() {
                var roll = this.roll();
                $(roll).each(function() {
                    total += this;
                });
            });
            this.notify("roll", total);
            return total;
        };
        
        DiceBag.prototype.summarize = function() {
            var dice = { };
            $(this._collection).each(function() {
                var key = this.toString();
                if (!(key in dice))
                    dice[key] = 0;
                dice[key]++;
            });
            var s = [ ];
            for (var key in dice) {
                if (key.charAt(0) === 'd') {
                    s.push(dice[key] + key);
                } else {
                    for (var n = 0; n < dice[key]; n++) {
                        s.push(key);
                    }
                }
            }
            return s.join(" + ");
        };
        
        return DiceBag;
    }
);