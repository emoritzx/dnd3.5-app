define(
    [ "./AttributeViewer" ],
    function(AttributeViewer) {
    
        "use strict";
        
        var CSS_CLASS = "DnD.Report.AttributeCollection";
        
        return function(attributes) {
            var container = document.createElement("DIV");
            container.className = CSS_CLASS;
            var keys = attributes.keys;
            for (var a = 0; a < keys.length; a++) {
                container.appendChild(new AttributeViewer(attributes.get(keys[a])));
            }
            return container;
        };
    }
);