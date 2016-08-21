define(
    [],
    function() {
    
        "use strict";
        
        var Path = new Object();
        
        Path.join = function() {
            var path = [ ];
            for (var a = 0; a < arguments.length; a++) {
                path.push(arguments[a]);
            }
            return path.join("/");
        };
        
        return Path;
    }
);