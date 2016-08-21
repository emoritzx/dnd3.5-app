define(
    ["jquery"],
    function($) {
    
        "use strict";
        
        function listener(event, value) {
            if (event === "roll") {
                var element = this.element;
                var nodes = element.childNodes;
                while (nodes.length > 0)
                    element.removeChild(nodes[0]);
                $(value).each(function() {
                    var result = document.createElement("DIV");
                    result.appendChild(document.createTextNode(this));
                    element.appendChild(result);
                });
            }
        }
        
        var Viewer = function(die) {
        console.log("viewer created");
            var element = document.createElement("DIV");
            this._element = element;
            this._die = die;
            die.addListener(listener, this);
        };
        
        Object.defineProperties(Viewer.prototype,
        {
            element: {
                get: function() {
                console.log("element requested");
                console.debug(this._element);
                    return this._element;
                }
            },
            die: {
                get: function() {
                    return this._die;
                }
            }
        });
        
        return Viewer;
    }
);