define(
    [ "../../../Utils/lib" ],
    function(Utils) {

        "use strict";
        
        var CSS_CLASS = "DnD.Report.Heading";
        var DEFAULT_NAME = "Character";
        
        return function(character) {
            var container = document.createElement("H1");
            container.className = CSS_CLASS;
            var name = character.attributes.get("Name");
            var text = (name) ? name.value : DEFAULT_NAME;
            var textNode = document.createTextNode(text);
            container.appendChild(textNode);
            Utils.observe(name, function(o) {
                textNode.nodeValue = o.value;
            });
            return container;
        };
    }
);