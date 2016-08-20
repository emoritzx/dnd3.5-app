define(
    [],
    function() {
    
        "use strict";
        
        var CSS_CLASS = "DnD.Log";
        
        var LogFunctor = function(callback, instance) {
            Object.defineProperties(instance,
            {
                instance: {
                    get: function() {
                        return instance;
                    }
                }
            });
            return callback;
        };
        
        Object.defineProperties(LogFunctor.prototype,
        {
            instance: {
                get: function() {
                    return this._instance;
                }
            }
        });
        
        var Log = function() {
            this._element = document.createElement("DIV");
            this.element.className = CSS_CLASS;
            this._textNode = document.createTextNode("no status");
            this.element.appendChild(this._textNode);
        };
        
        Object.defineProperties(Log.prototype,
        {
            element: {
                get: function() {
                    return this._element;
                }
            }
        });
        
        Log.prototype.write = function(text) {
            this._textNode.nodeValue = text;
            return this;
        }
        
        return Log;
    }
);