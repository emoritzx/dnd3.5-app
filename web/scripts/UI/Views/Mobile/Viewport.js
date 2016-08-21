define(
    [ "jquery" ],
    function($) {
    
        "use strict";
        
        var CSS_CLASS = "DnD.Mobile";
        
        var Viewport = function() {
            // container
            var container = document.createElement("DIV");
            container.className = CSS_CLASS + ".Main";
            this._element = container;
            // sidebar
            var sidebar = document.createElement("DIV");
            sidebar.className = CSS_CLASS + ".Menu hidden";
            this._menu = sidebar;
            // titlebar
            var titlebar = document.createElement("DIV");
            titlebar.className = CSS_CLASS + ".Title";
            var menuButton = document.createElement("INPUT");
            menuButton.type = "button";
            menuButton.value = "Menu";
            menuButton.addEventListener("click", function() {
                $(sidebar).toggleClass("hidden");
            });
            titlebar.appendChild(menuButton);
            var titleText = document.createElement("SPAN");
            titlebar.appendChild(titleText);
            var titleTextNode = document.createTextNode("Character");
            titleText.appendChild(titleTextNode);
            this._title = titleTextNode;
            // body
            var body = document.createElement("DIV");
            body.className = CSS_CLASS + ".Body";
            this._body = body;
            // attach
            container.appendChild(titlebar);
            container.appendChild(sidebar);
            container.appendChild(body);
            this._selectedIndex = -1;
        };
        
        Object.defineProperties(Viewport.prototype,
        {
            character: {
                get: function() {
                    return this._character;
                }
            },
            element: {
                get: function() {
                    return this._element;
                }
            },
            title: {
                get: function() {
                    return this._title.nodeValue;
                },
                set: function(value) {
                    this._title.nodeValue = value;
                }
            },
            selectedIndex: {
                get: function() {
                    return this._selectedIndex;
                },
                set: function(value) {
                    var nodes = this._body.childNodes;
                    $(nodes).each(function() {
                        $(this).addClass("hidden");
                    });
                    $(nodes[value]).removeClass("hidden");
                }
            }
        });
        
        Viewport.prototype.addEntry = function(name, element) {
            var closure = this;
            var menu = this._menu;
            var body = this._body;
            var entry = document.createElement("DIV");
            var link = document.createElement("A");
            var linkText = document.createTextNode(name);
            link.appendChild(linkText);
            link.href = "#" + name;
            var index = menu.childNodes.length;
            link.addEventListener("click", function() {
                closure.selectedIndex = index;
                $(menu).addClass("hidden");
            });
            entry.appendChild(link);
            menu.appendChild(entry);
            $(element).addClass("hidden");
            body.appendChild(element);
        };
        
        Viewport.prototype.show = function(name) {
            $.each(this._menu.children, function(index, element) {
                var link = element.children[0];
                if (link.hash === "#" + name) {
                    link.click();
                }
            });
        };
        
        return Viewport;
    }
);