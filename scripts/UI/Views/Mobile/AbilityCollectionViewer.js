define(
    [ "./AbilityViewer" ],
    function(AbilityViewer) {
    
        "use strict";
        
        var CSS_CLASS = "DnD.Report.AbilityCollection";
                
        function createHeader() {
            var container = document.createElement("DIV");
            var label = document.createElement("LABEL");
            label.appendChild(document.createTextNode("Ability"));
            container.appendChild(label);
            var score = document.createElement("SPAN");
            score.appendChild(document.createTextNode("Score"));
            container.appendChild(score);
            var mod = document.createElement("SPAN");
            mod.appendChild(document.createTextNode("Mod"));
            container.appendChild(mod);
            return container;
        }
        
        return function(abilities) {
            var container = document.createElement("DIV");
            container.className = CSS_CLASS;
            container.appendChild(createHeader());
            var keys = abilities.keys;
            for (var a = 0; a < keys.length; a++) {
                container.appendChild(new AbilityViewer(abilities.get(keys[a])));
            }
            return container;
        };
    }
);