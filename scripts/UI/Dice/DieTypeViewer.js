define(
    [],
    function() {
    
        "use strict";
        
        var CSS_CLASS = "DnD.Dice";
        
        var DieViewer = function(dieType) {
            var die = new dieType();
            this._die = dieType;
            var element = document.createElement("DIV");
            element.className = CSS_CLASS + ".Die";
            element.appendChild(document.createTextNode(die.type));
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