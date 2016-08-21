define(
    [
        "../../Core/DiceBag",
        "./DieCreator",
        "./DiceBagViewer",
        "./DiceBagResultsViewer"
    ],
    function(DiceBag, DieCreator, DiceBagViewer, DiceBagResultsViewer) {
    
        "use strict";
        
        var CSS_CLASS = "DnD.Dice";
        
        var Roller = function(element, bag) {
            element = element || document.createElement("DIV");;
            bag = bag || new DiceBag();
            this._element = element;
            this._bag = bag;
            element.className = CSS_CLASS + ".Roller";
            var viewer = new DiceBagViewer(bag);
            var results = new DiceBagResultsViewer(bag);
            element.appendChild(new DieCreator(bag).element);
            element.appendChild(viewer.element);
            element.appendChild(results.element);
        };
        
        Object.defineProperties(Roller.prototype,
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
        
        return Roller;
    }
);