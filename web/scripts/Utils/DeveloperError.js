define(function() {
    "use strict";
    var DeveloperError = function(message) {
        this.name = "DeveloperError";
        this.message = message;
        this.stack = (new Error()).stack;
    };
    DeveloperError.prototype = new Error;
    return DeveloperError;
});