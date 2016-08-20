define(
    [ "../../../Utils/lib", "../../../Core/Enhancement", "./EntryViewer" ],
    function(Utils, Enhancement, EntryViewer) {
    
        "use strict";
        
        var Viewer = function(entry) {
            EntryViewer.call(this, entry);
            if (entry.slot)
                this.name += " (" + entry.slot + ")";
            var singles = Enhancement.Single;
            var maps = Enhancement.Map;
            for (var i = 0; i < singles.length; i++) {
                var key = singles[i];
                if (key !== "name" && key !== "description" && key !== "type" && key != "slot" && entry[key]) {
                    this.addFlavorText(key.charAt(0).toUpperCase() + key.slice(1) + ": " + entry[key]);
                }
            }
            for (var i = 0; i < maps.length; i++) {
                var group = maps[i];
                var keys = entry[group].keys;
                if (keys.length === 0)
                    continue;
                for (var j = 0; j < keys.length; j++) {
                    var key = keys[j];
                    var num = entry[group].get(key);
                    if (num !== 0) {
                        this.addFlavorText(Utils.formatModifier(num) + " " + key);
                    }
                }
            }
        };
        
        Utils.extend(Viewer, EntryViewer);
        
        Object.defineProperties(Viewer.prototype,
        {
            element: {
                get: function() {
                    return this._element;
                }
            }
        });
        
        return Viewer;
    }
);