define(
    ["../../Core/DiceBag", "./DieViewer"],
    function(DiceBag, DieViewer) {
    
        "use strict";
        
        var CSS_CLASS = "DnD.Dice";
        
        function listener(event, value) {
            if (event === "added") {
                var die = new value();
                this.element.appendChild(document.createTextNode(die.type));
                this.element.appendChild(document.createTextNode(die.description));
            }
        }
        
        var Viewer = function(bag) {
            var element = document.createElement("DIV");
            this._element = element;
            element.className = CSS_CLASS + ".Creator";
            bag = bag || new DiceBag();
            this._bag = bag;
            bag.types.addListener(listener, this);
        };
        
        Object.defineProperties(Viewer.prototype,
        {
            element: {
                get: function() {
                    return this._element;
                }
            },
            bag: {
                get: function() {
                    return this._bag;
                }
            }
        });
        
        return Viewer;
    }
);