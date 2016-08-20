define(
    [],
    function() {
    
        "use strict";
        
        var CSS_CLASS = "DnD.Dice";
        
        var DieViewer = function(die) {
            this._die = die;
            var element = document.createElement("DIV");
            element.className = CSS_CLASS + ".Die";
            element.appendChild(document.createTextNode(die.toString()));
            this._element = element;
        };
        
        Object.defineProperties(DieViewer.prototype,
        {
            element: {
                get: function() {
                    return this._element;
                }
            },
            die: {
                get: function() {
                    return this._die;
                }
            }
        });
        
        return DieViewer;
    }
);