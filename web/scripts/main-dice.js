requirejs.config({
    paths: {
        "jquery": "jquery-2.1.3.min"
    }
});

var app;

require(["jquery", "./Core/Loader"], function($, Loader) {

    "use strict";
    
    var loading;
    var path = "./saved/roller";
    
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
        // get dice
        Loader.getDice(path)
        // success
        .done(function(dice) {
            console.log(dice);
            // load app
            require(["./UI/Dice/Roller", "./Core/DiceBag", "./Utils/Observable"], function(UI, DiceBag, Observable) {
                // remove old log
                document.body.removeChild(loading);
                app = new UI(null, new DiceBag(new Observable.Object(dice)));
                document.body.appendChild(app.element);
                setTimeout(function() {
                    var type = app.bag.types.get("Normal");
                    var die = new type(8);
                    app.bag.add(die);
                    die = new type(8);
                    app.bag.add(die);
                    die = new type(8);
                    app.bag.add(die);
                    die = new type(8);
                    app.bag.add(die);
                    die = new type(8);
                    app.bag.add(die);
                    die = new type(8);
                    app.bag.add(die);
                    setTimeout(function() {
                        app.bag.roll();
                    }, 2000);
                }, 2000);
            });
        })
        // no dice
        .fail(function() {
            alert("Failed");
        });
    });
});