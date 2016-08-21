define(function() {

    "use strict";

    return function(source, extension) {
        for (var property in extension.prototype) {
            console.log(property);
            source.prototype[property] = extension.prototype[property];
        }
        source.prototype.constructor = source;
        console.log(source);
    };

});