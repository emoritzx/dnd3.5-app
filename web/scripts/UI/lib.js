define(
    [
        "./Views/Mobile/lib",
        "./Log"
    ],
    function(
        View,
        Log
    ) {
    
        "use strict";
        
        var UI = function(container) {
            this._container = container || document.body;
            // setup log
            var log = new Log();
            this._log = log;
            // attach to container
            //this._container.appendChild(log.element);
        };
        
        Object.defineProperties(UI.prototype,
        {
            log: {
                get: function() {
                    return this._log;
                }
            },
            view: {
                get: function() {
                    return this._view;
                }
            }
        });

        UI.prototype.load = function(character) {
            if (this.view) {
                this._container.removeChild(this.view.element);
            }
            this._view = new View(character);
            this._container.appendChild(this.view.element);
        };
        
        return UI;
    }
);