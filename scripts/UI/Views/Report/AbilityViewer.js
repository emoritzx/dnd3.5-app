define(
    [ "../../../Utils/lib" ],
    function(Utils) {

        "use strict";
    
        var CSS_CLASS = "DnD.Report.Ability";
        
        function formatModifier(modifier) {
            if (modifier >= 0) {
                return "+" + modifier;
            }
            return modifier;
        }
        
        return function(attribute) {
            var container = document.createElement("DIV");
            container.className = CSS_CLASS;
            var label = document.createElement("LABEL");
            label.title = attribute.name;
            container.appendChild(label);
            var labelText = document.createTextNode(attribute.abbr);
            label.appendChild(labelText);
            var value = document.createElement("SPAN");
            container.appendChild(value);
            var valueText = document.createTextNode(attribute.value);
            value.appendChild(valueText);
            var mod = document.createElement("SPAN");
            container.appendChild(mod);
            var modText = document.createTextNode(formatModifier(attribute.modifier));
            mod.appendChild(modText);
            Utils.observe(attribute, function(o) {
                label.title         = o.name;
                labelText.nodeValue = o.abbr;
                valueText.nodeValue = o.value;
                modText.nodeValue   = formatModifier(o.modifier);
            });
            return container;
        };
    }
);