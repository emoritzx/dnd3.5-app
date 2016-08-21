define(
    [ "jquery", "../../../Utils/lib", "./SkillViewer" ],
    function($, Utils, SkillViewer) {
    
        "use strict";
        
        var CSS_CLASS = "DnD.Report.SkillCollection";
                
        function addHeading(container, name, helpText) {
            var label = document.createElement("SPAN");
            label.appendChild(document.createTextNode(name));
            container.appendChild(label);
            if (helpText) {
                var help = document.createElement("SUP");
                help.className = "help";
                help.title = helpText;
                label.appendChild(help);
            }
            return label;
        }
        
        function createHeader() {
            var container = document.createElement("DIV");
            container.className = CSS_CLASS + ".Header";
            addHeading(container, "Skill", "* denotes trained-only");
            addHeading(container, "Ability", "* denotes armor check penalty");
            addHeading(container, "Value");
            addHeading(container, "Mod");
            addHeading(container, "Ranks", "Trained (class/cross)");
            addHeading(container, "Misc");
            return container;
        }
        
        function createTrainedOnlyToggle(container) {
            var trainedOnlyToggleContainer = document.createElement("SPAN");
            trainedOnlyToggleContainer.className = "clickable-all";
            trainedOnlyToggleContainer.title = "Show \"Trained Only\" skills";
            var trainedOnlyToggle = document.createElement("INPUT");
            trainedOnlyToggle.type = "checkbox";
            trainedOnlyToggle.checked = true;
            trainedOnlyToggle.addEventListener("change", function() {
                var input = this;
                $(container.children).each(function() {
                    if (this.getAttribute(SkillViewer.TRAINED_ATTR) === "false"
                        && this.getAttribute(SkillViewer.TRAINED_ONLY_ATTR) === "true")
                    {
                        this.setAttribute(SkillViewer.TOGGLE_ATTR, !input.checked);
                    }
                });
            });
            trainedOnlyToggleContainer.appendChild(trainedOnlyToggle);
            var trainedOnlyToggleLabel = document.createElement("LABEL");
            trainedOnlyToggleLabel.appendChild(
                document.createTextNode("Show Trained-Only")
            );
            trainedOnlyToggleLabel.addEventListener("click", function() {
                trainedOnlyToggle.click();
            }, false);
            trainedOnlyToggleContainer.appendChild(trainedOnlyToggleLabel);
            return trainedOnlyToggleContainer;
        }

        function createMaxRanks(character) {
            var container = document.createElement("SPAN");
            var label = document.createElement("LABEL");
            label.appendChild(document.createTextNode("Max Ranks:"));
            container.appendChild(label);
            var ranks = document.createElement("SPAN");
            container.appendChild(ranks);
            var maxRanks = character.maxRanks;
            var ranksText = document.createTextNode(maxRanks[0]);
            var crossRanksText = document.createTextNode(maxRanks[1]);
            ranks.appendChild(ranksText);
            ranks.appendChild(document.createTextNode("/"));
            ranks.appendChild(crossRanksText);
            Utils.observe(character, function(o) {
                var maxRanks = o.maxRanks;
                ranksText.nodeValue = maxRanks[0];
                crossRanksText.nodeValue = maxRanks[1];
            });
            return container;
        }
        
        function createToolbar(container, character) {
            var toolbar = document.createElement("DIV");
            toolbar.className = CSS_CLASS + ".Toolbar";
            var toolbarCell = document.createElement("TD");
            toolbarCell.colSpan = 6;
            toolbarCell.appendChild(createMaxRanks(character));
            toolbarCell.appendChild(createTrainedOnlyToggle(container));
            toolbar.appendChild(toolbarCell);
            return toolbar;
        }
        
        return function(character) {
            var skills = character.skills;
            var container = document.createElement("DIV");
            container.className = CSS_CLASS;
            container.appendChild(createToolbar(container, character));
            container.appendChild(createHeader());
            var keys = skills.keys;
            for (var a = 0; a < keys.length; a++) {
                container.appendChild(new SkillViewer(character, skills.get(keys[a])));
            }
            return container;
        };
    }
);