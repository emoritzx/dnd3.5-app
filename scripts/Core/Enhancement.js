define(
    ["../Utils/lib", "../Utils/Observable", "../Utils/GetSetMap"],
    function(Utils, Observable, GetSetMap) {
    
        "use strict";
        
        var DEFAULT_TYPE = "Miscellaneous";
        var UNKNOWN_COUNT = 0;
        
        var Enhancement = function(data) {
            Observable.call(this);
            this._abilities = new GetSetMap("abilities", Number);
            this._saves = new GetSetMap("saves", Number);
            this._skills = new GetSetMap("skills", Number);
            this._armor = new GetSetMap("armor", Number);
            if (data)
                load.call(this, data);
        };
        
        Utils.extend(Enhancement, Observable);
        
        Object.defineProperties(Enhancement.prototype,
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
            type: {
                get: function() {
                    return this._type;
                },
                set: function(value) {
                    this._type = value;
                    this.notify();
                }
            },
            description: {
                get: function() {
                    return this._description;
                },
                set: function(value) {
                    this._description = value;
                    this.notify();
                }
            },
            abilities: {
                get: function() {
                    return this._abilities;
                }
            },
            saves: {
                get: function() {
                    return this._saves;
                }
            },
            skills: {
                get: function() {
                    return this._skills;
                }
            },
            armor: {
                get: function() {
                    return this._armor;
                }
            },
            hp: {
                get: function() {
                    return this._hp;
                },
                set: function(value) {
                    this._hp = value || 0;
                    this.notify();
                }
            },
            bab: {
                get: function() {
                    return this._bab;
                },
                set: function(value) {
                    this._bab = value || 0;
                    this.notify();
                }
            },
            value: {
                get: function() {
                    return this._value;
                },
                set: function(value) {
                    this._value = value || 0;
                    this.notify();
                }
            },
            weight: {
                get: function() {
                    return this._weight;
                },
                set: function(value) {
                    this._weight = value || 0;
                    this.notify();
                }
            }
        });
        
        var single_properties = [
            "type",
            "slot",
            "name",
            "description",
            "value",
            "hp",
            "bab",
            "weight",
        ];
        
        var map_properties = [
            "armor",
            "abilities",
            "saves",
            "skills"
        ];
        
        function load(data) {
            for (var p = 0; p < single_properties.length; p++) {
                var prop = single_properties[p];
                this[prop] = data[prop];
            }
            for (var p = 0; p < map_properties.length; p++) {
                var prop = map_properties[p];
                if (data[prop]) {
                    for (var key in data[prop]) {
                        this[prop].set(key, new Number(data[prop][key] || 0));
                    }
                }
            }
        };
        
        Enhancement.Single = single_properties;
        Enhancement.Map = map_properties;
        
        return Enhancement;
    }
);