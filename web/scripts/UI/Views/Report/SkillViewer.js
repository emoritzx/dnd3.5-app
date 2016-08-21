define(
    [ "jquery", "../../../Utils/lib" ],
    function($, Utils) {

        "use strict";
    
        var CSS_CLASS = "DnD.Report.Skill";
        var TOGGLE_ATTR = "dnd-hidden";
        var TRAINED_ATTR = "dnd-trained";
        var TRAINED_ONLY_ATTR = "dnd-trained-only";
        var OVERMAX_ATTR = "dnd-overmax";
        var OVERMAX_TITLE = "You have trained more than max ranks";
        var MAXED_ATTR = "dnd-maxed";
        
        function formatName(skill) {
            var text = skill.abbr || skill.name;
            if (skill.trainedOnly) {
                text += "*";
            }
            return text;
        }
        
        function formatAbilityText(skill) {
            var text = skill.ability.abbr;
            if (skill.armorPenalty)
            {
                text += "*";
            }
            return text;
        }
        
        function formatRanksText(skill) {
            return skill.ranks + "/" + (skill.crossRanks / 2);
        }
                
        function checkOverMax(character, skill) {
            var maxRanks = character.maxRanks;
            return skill.ranks > maxRanks[0]
                || (skill.crossRanks / 2) > maxRanks[1]
                || (skill.ranks + (skill.crossRanks / 2)) > maxRanks[0];
        }
        
        function checkMaxed(character, skill) {
            var maxRanks = character.maxRanks;
            return skill.ranks >= maxRanks[0]
                || (skill.crossRanks / 2) >= maxRanks[1]
                || (skill.ranks + (skill.crossRanks / 2)) >= maxRanks[0];
        }
        
        function formatModifier(value) {
            return value;
        }
        
        function SkillViewer(character, skill) {
            var container = document.createElement("DIV");
            container.className = CSS_CLASS;
            container.setAttribute(TRAINED_ATTR, skill.trained);
            container.setAttribute(TRAINED_ONLY_ATTR, skill.trainedOnly);
            // name
            var name = document.createElement("LABEL");
            name.title = skill.name;
            container.appendChild(name);
            var nameValue = formatName(skill);
            var nameText = document.createTextNode(nameValue);
            name.appendChild(nameText);
            // ability
            var ability = document.createElement("SPAN");
            ability.title = skill.ability.name;
            container.appendChild(ability);
            var abilityText = document.createTextNode(formatAbilityText(skill));
            ability.appendChild(abilityText);
            // value
            var value = document.createElement("SPAN");
            value.className = CSS_CLASS + ".Value";
            container.appendChild(value);
            var valueText = document.createTextNode(formatModifier(skill.value));
            value.appendChild(valueText);
            // modifier
            var modifier = document.createElement("SPAN");
            container.appendChild(modifier);
            var modifierText = document.createTextNode(skill.ability.modifier);
            modifier.appendChild(modifierText);
            // ranks
            var ranks = document.createElement("SPAN");
            var overMax = checkOverMax(character, skill);
            var maxed = checkMaxed(character, skill);
            container.setAttribute(MAXED_ATTR, maxed);
            ranks.setAttribute(OVERMAX_ATTR, overMax);
            ranks.title = (overMax) ? OVERMAX_TITLE : "";
            container.appendChild(ranks);
            var ranksText = document.createTextNode(formatRanksText(skill));
            ranks.appendChild(ranksText);
            // bonus
            var bonus = document.createElement("SPAN");
            container.appendChild(bonus);
            var bonusText = document.createTextNode(skill.bonus);
            bonus.appendChild(bonusText);
            // skill observer
            Utils.observe(skill, function(o) {
                name.title = o.name;
                nameText.nodeValue = formatName(o);
                ability.title = o.ability.name;
                abilityText.nodeValue = formatAbilityText(o);
                valueText.nodeValue = o.value;
                modifierText.nodeValue = o.ability.modifier;
                ranksText.nodeValue = formatRanksText(o);
                bonusText.nodeValue = o.bonus;
                container.setAttribute(TRAINED_ATTR, o.trained);
                container.setAttribute(TRAINED_ONLY_ATTR, o.trainedOnly);
                var overMax = checkOverMax(character, o)
                ranks.setAttribute(OVERMAX_ATTR, overMax);
                ranks.title = (overMax) ? OVERMAX_TITLE : "";
                var maxed = checkMaxed(character, o);
                container.setAttribute(MAXED_ATTR, maxed);
            });
            // character observer
            Utils.observe(character, function(o) {
                var overMax = checkOverMax(o, skill);
                ranks.setAttribute(OVERMAX_ATTR, overMax);
                ranks.title = (overMax) ? OVERMAX_TITLE : "";
                var maxed = checkMaxed(o, skill);
                container.setAttribute(MAXED_ATTR, maxed);
            });
            return container;
        };
        
        SkillViewer.TOGGLE_ATTR = TOGGLE_ATTR;
        SkillViewer.TRAINED_ATTR = TRAINED_ATTR;
        SkillViewer.TRAINED_ONLY_ATTR = TRAINED_ONLY_ATTR;
        SkillViewer.OVERMAX_ATTR = OVERMAX_ATTR;
        
        return SkillViewer;
    }
);