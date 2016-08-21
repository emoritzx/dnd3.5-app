define(function() {

    "use strict";
    
    var TabbedPane = function(options) {
        var container = document.createElement("DIV");
        this._container = container;
        options = options || { };
        this._tabs = { };
        var tabs = options.tabs || [ "Tab 1" ];
        for (var t = 0; t < tabs.length; t++) {
            this.addTab(tabs[t]);
        }
    };
    
    Object.defineProperties(TabbedPane.prototype,
    {
        element: {
            get: function() {
                return this._container;
            }
        },
        tabs: {
            get: function() {
                return Object.keys(this._tabs);
            }
        }
    });
    
    TabbedPane.prototype.addTab = function(name, content) {
        if (this._tabs.hasOwnProperty(name)) {
            return false;
        }
        var tab = document.createElement("DIV");
        this.element.appendChild(tab);
        if (content) {
            tab.appendChild(content);
        }
        this._tabs[name] = tab;
    };
    
    TabbedPane.prototype.getTab = function(name) {
        return this._tabs[name];
    };
    
    return TabbedPane;
});