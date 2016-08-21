define(
    [ "../Utils/lib", "../Utils/Observable", "./Statistic" ],
    function(Utils, Observable, Statistic) {
    
        "use strict";
        
        var Attack = function(name, bab, ability, damage) {
            Statistic.call(this, name, ability);
            this._bab = bab;
            var closure = this;
            bab.addListener(function() {
                closure.notify("bab", this.value); 
            });
            this._damage = damage;
            this._flavor = new Observable.Object();
        };
        
        Utils.extend(Attack, Statistic);
        
        Object.defineProperties(Attack.prototype,
        {
            bab: {
                get: function() {
                    return this._bab;
                }
            },
            description: {
                get: function() {
                    return this._description;
                },
                set: function(value) {
                    this._description = value;
                    this.notify("description", value);
                }
            },
            damage: {
                get: function() {
                    return this._damage;
                }
            },
            flavor: {
                get: function() {
                    return this._flavor;
                }
            }
        });
        
        Attack.prototype.getAttacks = function() {
            var attacks = [ ];
            var bab = this.bab.value;
            var mod = this.ability.modifier;
            var bonus = this.bonus;
            do
            {
                var value = bab + mod + bonus;
                attacks.push(value);
                bab -= 5;
            } while (bab > 0);
            return attacks;
        };
        
        return Attack;
    }
);