define(
    [ "../../../Utils/lib" ],
    function(Utils) {

        "use strict";
    
        var CSS_CLASS = "DnD.Report.Attribute";
        
        return function(attribute) {
            var container = document.createElement("DIV");
            container.className = CSS_CLASS;
            var label = document.createElement("LABEL");
            container.appendChild(label);
            var labelText = document.createTextNode(attribute.name);
            label.appendChild(labelText);
            var value = document.createElement("SPAN");
            container.appendChild(value);
            var valueText = document.createTextNode(attribute.value);
            value.appendChild(valueText);
            Utils.observe(attribute, function(o) {
                labelText.nodeValue = o.name;
                valueText.nodeValue = o.value;
            });
            return container;
        };
    }
);