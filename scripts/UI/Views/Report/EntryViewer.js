define(
    [],
    function() {
    
        "use strict";
        
        var CSS_CLASS = "DnD.Report.Entry";
        
        var Viewer = function(entry) {
            var el = document.createElement("DIV");
            el.className = CSS_CLASS;
            this._element = el;
            createHeader.call(this, entry);
            if (entry.description)
                createDescription.call(this, entry);
            var body = document.createElement("DIV");
            this._body = body;
            el.appendChild(body);
        };
        
        Object.defineProperties(Viewer.prototype,
        {
            name: {
                get: function() {
                    return this._name.nodeValue;
                },
                set: function(value) {
                    this._name.nodeValue = value;
                }
            },
            element: {
                get: function() {
                    return this._element;
                }
            }
        });
        
        Viewer.prototype.addFlavor = function(element) {
            this._body.appendChild(element);
        };
        
        Viewer.prototype.addFlavorText = function(text) {
            this.addFlavor(textDiv(text));
        };
        
        function textDiv(text) {
            var el = document.createElement("DIV");
            el.appendChild(document.createTextNode(text));
            return el;        
        }
        
        function createHeader(entry) {
            var el = textDiv(entry.name);
            this.element.appendChild(el);
            this._name = el.childNodes[0];
        }
        
        function createDescription(entry) {
            var el = textDiv(entry.description);
            el.className = CSS_CLASS + ".Description";
            this.element.appendChild(el);
        }
        
        return Viewer;
    }
);