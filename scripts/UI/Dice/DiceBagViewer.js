define(
    ["../../Core/DiceBag", "./DieViewer"],
    function(DiceBag, DieViewer) {
    
        "use strict";
        
        var CSS_CLASS = "DnD.Dice";
        
        function listener(event, value) {
            if (event === "added") {
                this.element.appendChild(new DieViewer(value).element);
            }
        }
        
        var Viewer = function(bag) {
            var element = document.createElement("DIV");
            this._element = element;
            element.className = CSS_CLASS + ".Bag";
            bag = bag || new DiceBag();
            bag.addListener(listener, this);
            this._bag = bag;
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