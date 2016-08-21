define(
    [
        "../Utils/lib",
        "../Utils/GetSetMap",
        "../Utils/Observable",
        "./Attribute",
        "./Ability",
        "./Class",
        "./Level",
        "./Enhancement",
        "./Skill",
        "./ArmorClass",
        "./Statistic",
        "./Attack",
        "./DiceBag"
    ],
    function(
        Utils,
        GetSetMap,
        Observable,
        Attribute,
        Ability,
        Class,
        Level,
        Enhancement,
        Skill,
        ArmorClass,
        Statistic,
        Attack,
        DiceBag
    ) {

        "use strict";

        var BASE_RANKS = 3;
        
        var Character = function(data) {
            Observable.call(this);
            this._attributes = new GetSetMap("Attributes", Attribute);
            this._abilities = new GetSetMap("Abilities", Ability);
            this._skills = new GetSetMap("Skills", Skill);
            this._classes = new GetSetMap("Classes", Class);
            this._saves = new GetSetMap("Saves", Statistic);
            this._attacks = new GetSetMap("Attacks", Attack);
            this._levels = [ ];
            this._bab = new Observable.Number();
            if (data) {
                this.load(data);
            }
        };
        
        // extend Observable
        Utils.extend(Character, Observable);
        
        // define getters / setters
        Object.defineProperties(Character.prototype,
        {
            attributes: {
                get: function() {
                    return this._attributes;
                }
            },
            abilities: {
                get: function() {
                    return this._abilities;
                }
            },
            attacks: {
                get: function() {
                    return this._attacks;
                }
            },
            skills: {
                get: function() {
                    return this._skills;
                }
            },
            classes: {
                get: function() {
                    return this._classes;
                }
            },
            saves: {
                get: function() {
                    return this._saves;
                }
            },
            bab: {
                get: function() {
                    return this._bab;
                }
            },
            hitpoints: {
                get: function() {
                    var con = this.abilities.get("Constitution");
                    var hp = this.levels[0].classDef.hitDie
                        + Ability.getMod(con.baseValue);
                        //console.log(hp);
                    for (var l = 1; l < this.level; l++) {
                        hp += Math.floor((2 / 3) * this.levels[l].classDef.hitDie)
                            + Ability.getMod(con.valueAt(l + 1));
                            //console.log(hp);
                    }
                    hp += con.bonus / 2 * this.level;
                    return hp;
                }
            },
            armorClass: {
                get: function() {
                    return this._armorClass;
                }
            },
            level: {
                get: function() {
                    return this._levels.length;
                }
            },
            levels: {
                get: function() {
                    return this._levels;
                }
            },
            enhancements: {
                get: function() {
                    return this._enhancements;
                }
            },
            maxRanks: {
                get: function() {
                    var amount = this.level + BASE_RANKS;
                    return [ amount, amount / 2 ];
                }
            },
            initiative: {
                get: function() {
                    return this._initiative;
                }
            }
        });

        Character.prototype.load = function(data) {
            this.bab.value = 0;
            // attributes
            var attributes = data.attributes;
            for (var name in attributes) {
                var value = attributes[name];
                this.attributes.add(new Attribute(name, value));
            }
            // abilities
            var abilities = data.abilities;
            for (var name in abilities) {
                var value = abilities[name].value;
                var abbr = abilities[name].abbr;
                var bonus = abilities.bonus || 0;
                var ability = new Ability(name, value, abbr);
                ability.bonus = bonus;
                this.abilities.add(ability);
            }
            var dexterity = this.abilities.get("Dexterity");
            this._armorClass = new ArmorClass(dexterity);
            this._initiative = new Statistic("Initiative", dexterity);
            // attacks
            var attacks = data.attacks;
            for (var a = 0; a < attacks.length; a++) {
                var attack = attacks[a];
                var damage = new DiceBag(data.dice);
                if (attack.damage) {
                    for (var d = 0; d < attack.damage.length; d++) {
                        var def = attack.damage[d];
                        var number = def.number || 1;
                        for (var n = 0; n < number; n++) {
                            var die = damage.create(def.type, def.size, def.effect, def.args);
                            damage.add(die);
                        }
                    }
                }
                var ability = this.abilities.get(attack.ability) || new Ability("null", 10);
                if (attack.type === "Melee") {
                    (function() {
                        var c = damage.create("Constant", ability.modifier, ability.name);
                        ability.addListener(function() {
                            c.size = this.modifier;
                        });
                        damage.add(c);
                    })();
                }
                var atk = new Attack(attack.name, this.bab, ability, damage);
                atk.description = attack.description;
                if (attack.flavor) {
                    for (var f in attack.flavor) {
                        atk.flavor.set(f, attack.flavor[f]);
                    }
                }
                atk.bonus = attack.bonus || 0;
                this.attacks.add(atk);
            }
            // saves
            var saves = data.saves;
            for (var name in saves) {
                var save = saves[name];
                if (save.ability) {
                    var ability = this.abilities.get(save.ability);
                    if (ability === null) {
                        throw new Error("Ability does not exist: " + save.ability);
                    }
                    this.saves.add(new Statistic(name, ability));
                } else {
                    this.saves.add(new Statistic(name, new Ability("null", 10)));
                }
            }
            // skills
            var skills = data.skills;
            for (var name in skills) {
                var skill = skills[name];
                var ability = this.abilities.get(skill.ability);
                if (ability === null) {
                    throw new Error("Ability does not exist: " + name);
                }
                var options = skill.options;
                this.skills.add(new Skill(name, ability, options));
            }
            // class definitions
            var classes = data.classes;
            for (var name in classes) {
                var classDef = classes[name];
                this.classes.add(new Class(name, classDef));
            }
            // enhancements
            this._levels = [ ];
            this._enhancements = [ ];
            var enhancements = data.enhancements;
            for (var e = 0; e < enhancements.length; e++) {
                var enhance = enhancements[e];
                if (enhance.type === "Level") {
                    var classDef = this.classes.get(enhance.name);
                    if (classDef === null) {
                        throw new Error("Class does not exist: " + className);
                    }
                    this.enhance(new Level(classDef, enhance));
                } else {
                    this.enhance(new Enhancement(enhance));
                }
            }
            // notify
            this.notify();
        };
        
        Character.prototype.enhance = function(enhancement) {
            // add enhancement
            if (enhancement instanceof Level) {
                this._levels.push(enhancement);
            } else {
                this._enhancements.push(enhancement);
            }
            // add ability increases
            var abilityNames = enhancement.abilities.keys;
            for (var a = 0; a < abilityNames.length; a++) {
                var name = abilityNames[a];
                var ability = this.abilities.get(name);
                if (ability === null) {
                    throw new Error("Ability does not exist: " + name);
                }
                var amount = enhancement.abilities.get(name);
                if (enhancement instanceof Level) {
                    ability.gain(this.level, amount);
                } else {
                    ability.bonus += amount;
                }
            }
            // add skill ranks
            var skillNames = enhancement.skills.keys;
            for (var s = 0; s < skillNames.length; s++) {
                var name = skillNames[s];
                var skill = this.skills.get(name);
                if (skill === null) {
                    throw new Error("Skill does not exist: " + name);
                }
                var amount = enhancement.skills.get(name);
                if (enhancement instanceof Level) {
                    // class skill?
                    var classSkill = enhancement.classDef.skills.get(name);
                    if (classSkill !== null && classSkill.value === true) {
                        skill.ranks += amount;
                    } else {
                        skill.crossRanks += amount;
                    }
                } else {
                    skill.bonus += amount;
                }
            }
            // AC
            var ac = this.armorClass;
            for (var t = 0; t < ArmorClass.BonusTypes.length; t++) {
                var type = ArmorClass.BonusTypes[t];
                if (enhancement.armor[type]) {
                console.log(type);
                console.log(enhancement.armor[type]);
                console.log(ac[type]);
                }
                ac[type] += enhancement.armor.get(type);
                if (enhancement.armor[type]) {
                console.log(ac[type]);}
            }
            ac.DR += enhancement.armor.get("DR");
            ac.bonus += enhancement.armor.get("bonus");
            // BAB
            this.bab.value += enhancement.bab;
            // saves
            var saveNames = enhancement.saves.keys;
            for (var s = 0; s < saveNames.length; s++) {
                var name = saveNames[s];
                var save = this.saves.get(name);
                if (save === null) {
                    throw new Error("Save does not exist: " + name);
                }
                save.bonus += enhancement.saves.get(name);
            }
            // notify
            this.notify("enhancement", enhancement);
        };

        return Character;
    }
);