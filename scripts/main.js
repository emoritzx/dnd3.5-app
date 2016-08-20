requirejs.config({
    paths: {
        "jquery":       "jquery-2.1.3.min",
        "jquery-url":   "url.min"
    },
    shim: {
        "jquery-url": {
            deps: [ 'jquery' ]
        }
    }
});

var dnd;

require(["jquery", "jquery-url"], function($) {

    "use strict";
    
    var loading;
    var characterParameter = $.url("?character") || "default";
    var characterPath = "./saved/" + characterParameter;
    
    $(document).ready(function() {
        // fake the log until the app is loaded
        loading = (function() {
            var container = document.createElement("DIV");
            container.className = "DnD.Log";
            var text = document.createTextNode("Loading scripts...");
            container.appendChild(text);
            return container;
        })();
        document.body.appendChild(loading);
        // load app
        require(["app"], function(App) {
            // remove old log
            document.body.removeChild(loading);
            var app = new App(document.body);
            dnd = {
                lib: App,
                app: app,
            };
            app.ui.log.write("Loading character...");
            // load character
            app.load(characterPath)
            .done(function(character) {
                app.ui.log.write("Character loaded: " + characterPath);
                var hash = $.url("hash");
                if (hash) {
                    app.ui.view.show(hash);
                }
            }).fail(function() {
                app.ui.log.write("Load failed :(");
            });
        });
    });
});