define(
    function(require) {
    
        "use strict";
        
        var Utils = require("../../../Utils/lib");
        var Ability = require("../../../Core/Ability");
        require("../../../Utils/RomanNumeral");
        
        var CSS_CLASS = "DnD.Report.LevelViewer";
        
        function createHeader() {
            var container = document.createElement("DIV");
            // level
            var level = document.createElement("LABEL");
            level.appendChild(document.createTextNode("Level"));
            container.appendChild(level);
            // class
            var className = document.createElement("LABEL");
            className.className = "center";
            className.appendChild(document.createTextNode("Class"));
            container.appendChild(className);
            // skills
            var skills = document.createElement("LABEL");
            skills.appendChild(document.createTextNode("Skills"));
            container.appendChild(skills);
            return container;
        }

        function getRanksForLevel(index, character) {
            var level = character.levels[index];
            var skillModifier = level.classDef.skillModifier;
            var abilityModifier = Ability.getMod(character.abilities.get("Intelligence").valueAt(index + 1));
            var amount = skillModifier + abilityModifier;
            if (index === 0) {
                amount *= 4;
            }
            return amount;
        }
        
        function createClassLevel(index, classLevels, character) {
            var level = character.levels[index];
            var container = document.createElement("DIV");
            // number
            var number = document.createElement("LABEL");
            number.className = "right";
            number.appendChild(document.createTextNode(index + 1));
            container.appendChild(number);
            // class
            var className = document.createElement("LABEL");
            className.className = "center";
            var name = level.classDef.name;
            if (!(name in classLevels)) {
                classLevels[name] = 0;
            }
            var classLevel = (++classLevels[name]).toRoman();
            className.appendChild(document.createTextNode(name + " " + classLevel));
            container.appendChild(className);
            // skills
            var skills = document.createElement("SPAN");
            skills.className = "right";
            container.appendChild(skills);
            var ranks = level.sumRanks();
            var maxRanks = getRanksForLevel(index, character);
            var ranksAvailable = maxRanks - ranks;
            var ranksText = document.createTextNode(ranks);
            var maxRanksText = document.createTextNode(maxRanks);
            skills.appendChild(ranksText);
            skills.appendChild(document.createTextNode("/"));
            skills.appendChild(maxRanksText);
            // error checking
            if (ranksAvailable < 0) {
                skills.className += " error";
            } else if (ranksAvailable > 0) {
                skills.className += " warning";
            }
            // done
            return container;
        }
        
        function createClassLevels(container, character) {
            var classLevels = { };
            var elements = [ ];
            var levels = character.levels;
            for (var l = 0; l < levels.length; l++) {
                var level = levels[l];
                var element = createClassLevel(l, classLevels, character);
                elements.push(element);
                container.appendChild(element);
            }        
            return elements;
        }
        
        return function(character) {
            var container = document.createElement("DIV");
            container.className = CSS_CLASS;
            container.appendChild(createHeader());
            var levelContainers = createClassLevels(container, character);
            Utils.observe(character, function(o) {
                for (var l = 0; l < levelContainers.length; l++) {
                    var element = levelContainers[l];
                    container.removeChild(element);
                }
                levelContainers = createClassLevels(container, character);
            });
            return container;
        };
    }
);