define(
    ["jquery", "../../Core/DiceBag", "./DieResultViewer"],
    function($, DiceBag, DieResultViewer) {
    
        "use strict";
        
        var CSS_CLASS = "DnD.Dice";
        
        function listener(event, value) {
        console.log(event);
            if (event === "added") {
                var result = new DieResultViewer(value);
                this._results.appendChild(result.element);
            } else if (event === "roll") {
                this._total.nodeValue = value;
            }
        }
        
        var Viewer = function(bag) {
            this._bag = bag;
            var element = document.createElement("DIV");
            this._element = element;
            var total = document.createElement("DIV");
            var totalNode = document.createTextNode("-");
            element.appendChild(total);
            total.appendChild(document.createTextNode("Total:"));
            total.appendChild(totalNode);
            this._total = totalNode;
            this._results = document.createElement("DIV");
            element.appendChild(this._results);
            bag.addListener(listener, this);
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