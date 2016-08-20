define(
    [ "./EnhancementViewer" ],
    function(EnhancementViewer) {
    
        "use strict";
        
        var EnhancementCollectionViewer = function(collection) {
            var container = document.createElement("DIV");
            this._element = container;
            var types = { };
            for (var i = 0; i < collection.length; i++) {
                var entry = collection[i];
                var type = entry.type;
                var el = types[type];
                if (!el) {
                    var heading = document.createElement("H2");
                    heading.appendChild(document.createTextNode(type));
                    container.appendChild(heading);
                    el = document.createElement("DIV");
                    types[type] = el;
                    container.appendChild(el);
                }
                el.appendChild(new EnhancementViewer(entry).element);
            }
            return container;
        };
        
        /*Object.defineProperties(EnhancementCollectionViewer.prototype,
        {
            element: {
                get: function() {
                    return this._element;
                }
            }
        });
        */
        return EnhancementCollectionViewer;
    }
);