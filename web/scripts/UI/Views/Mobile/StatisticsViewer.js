define(
    ["../../../Utils/lib", "./EntryViewer"],
    function(Utils, EntryViewer) {
    
        "use strict";
        
        function createArmorClassViewer(character) {
            var container = document.createElement("DIV");
            var label = document.createElement("H2");
            label.appendChild(document.createTextNode("Defense"));
            container.appendChild(label);
            container.appendChild(document.createTextNode("HP: "));
            var hp = document.createTextNode(character.hitpoints);
            container.appendChild(hp);
            var acRow = document.createElement("DIV");
            container.appendChild(acRow);
            var ac = document.createTextNode(character.armorClass.value);
            acRow.appendChild(document.createTextNode("AC: "));
            acRow.appendChild(ac);
            var touchRow = document.createElement("DIV");
            container.appendChild(touchRow);
            var touch = document.createTextNode(character.armorClass.touch);
            touchRow.appendChild(document.createTextNode("Touch: "));
            touchRow.appendChild(touch);
            var flatRow = document.createElement("DIV");
            container.appendChild(flatRow);
            var flat = document.createTextNode(character.armorClass.flat);
            flatRow.appendChild(document.createTextNode("Flat: "));
            flatRow.appendChild(flat);
            var drRow = document.createElement("DIV");
            container.appendChild(drRow);
            var dr = document.createTextNode(character.armorClass.DR);
            drRow.appendChild(document.createTextNode("DR: "));
            drRow.appendChild(dr);
            Utils.observe(character.armorClass, function(o) {
                hp.nodeValue = o.hitpoints;
                ac.nodeValue = o.value;
                touch.nodeValue = o.touch;
                flat.nodeValue = o.flat;
                dr.nodeValue = o.DR;
            });
            return container;
        }
        
        function addAttacks(container, attacks) {
            while (container.childNodes.length > 0)
                container.removeChild(container.childNodes[0]);
            var keys = attacks.keys;
            for (var i = 0; i < keys.length; i++) {
                var attack = attacks.get(keys[i]);
                var box = new EntryViewer(attack);
                container.appendChild(box.element);
                var list = attack.getAttacks();
                var mods = [ ];
                for (var j = 0; j < list.length; j++) {
                    mods.push(Utils.formatModifier(list[j]));
                }
                box.addFlavorText("Attack: " + mods.join("/"));
                if (attack.damage)
                    box.addFlavorText("Damage: " + attack.damage.summarize());
                var flavorkeys = attack.flavor.keys;
                for (var k = 0; k < flavorkeys.length; k++) {
                    var flavor = flavorkeys[k];
                    box.addFlavorText(flavor.charAt(0).toUpperCase() + flavor.slice(1) + ": " + attack.flavor.get(flavor));
                }
            }
        }
        
        function createAttackViewer(character) {
            var container = document.createElement("DIV");
            var label = document.createElement("H2");
            label.appendChild(document.createTextNode("Attacks"));
            container.appendChild(label);
            container.appendChild(createInitiativeViewer(character));
            container.appendChild(document.createTextNode("BAB "));
            var bab = document.createTextNode(Utils.formatModifier(character.bab));
            container.appendChild(bab);
            var attacks = document.createElement("DIV");
            container.appendChild(attacks);
            addAttacks(attacks, character.attacks);
            Utils.observe(character, function(o) {
                bab.nodeValue = o.bab;
                addAttacks(attacks, o.attacks);
            });
            return container;
        }
        
        function createInitiativeViewer(character) {
            var container = document.createElement("DIV");
            var name = document.createTextNode(character.initiative.name + " ");
            container.appendChild(name);
            var value = document.createTextNode(Utils.formatModifier(character.initiative.value));
            container.appendChild(value);
            Utils.observe(character.initiative, function(o) {
                name.nodeValue = o.name + " ";
                value.nodeValue = Utils.formatModifier(o.value);
            });
            return container;
        }
        
        function createSavesViewer(character) {
            var container = document.createElement("DIV");
            var names = character.saves.keys;
            //names.sort();
            for (var n = 0; n < names.length; n++) {
                var name = names[n];
                var save = character.saves.get(name);
                var row = document.createElement("DIV");
                container.appendChild(row);
                var label = document.createTextNode(save.name + " ");
                row.appendChild(label);
                var text = document.createTextNode(Utils.formatModifier(save.value));
                row.appendChild(text);
                Utils.observe(save, function(s) {
                    label.nodeValue = s.name + " ";
                    text.nodeValue = Utils.formatModifier(s.value);
                });
            }
            return container;
        }   
        
        return function(character) {
            var container = document.createElement("DIV");
            container.appendChild(createArmorClassViewer(character));
            container.appendChild(createSavesViewer(character));
            container.appendChild(createAttackViewer(character));
            return container;
        }
    }
);