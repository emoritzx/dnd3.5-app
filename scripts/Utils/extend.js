define(function() {

    "use strict";

    return function(source, extension) {
        source.prototype = Object.create(extension.prototype);
        source.prototype.constructor = source;
    };

});