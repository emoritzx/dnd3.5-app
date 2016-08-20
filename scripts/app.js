define(
    [
        "jquery",
        "./Core/Character",
        "./Core/Level",
        "./Core/Loader",
        "./UI/lib"
    ],
    function(
        $,
        Character,
        Level,
        Loader,
        UI
    ) {

        var App = function(container) {
            this._ui = new UI(container);
        };
        
        Object.defineProperties(App.prototype,
        {
            character: {
                get: function() {
                    return this._character;
                }
            },
            ui: {
                get: function() {
                    return this._ui;
                }
            },
        });

        App.prototype.load = function(name) {
            var closure = this;
            var deferred = $.Deferred();
            Loader.getCharacterData(name)
            .done(function(data) {
                //try
                //{
                    var character = new Character(data);
                    closure._character = character;
                    closure.ui.load(character);
                    deferred.resolve(character);
                //} catch (e) {
                //    console.error(e);
                //    deferred.reject(e);
                //}
            })
            .fail(function() {
                deferred.reject.apply(undefined, arguments);
            });
            return deferred.promise();
        };
        
        // static
        
        App.Level = Level;
        

        return App;
    }
);