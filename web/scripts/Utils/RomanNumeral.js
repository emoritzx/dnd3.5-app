(function() {

    "use strict";
    
    function NumeralTracker() {
        var _arr = [ ];
        var closure = this;
        this.addNumeral = function(digit, value) {
            _arr.push({
                digit: digit,
                value: value
            })
            return closure;
        };
        this.toArray = function() {
            return _arr;
        };
    };
    
    var numerals = new NumeralTracker()
        .addNumeral("M",    1000)
        .addNumeral("CM",   900)
        .addNumeral("D",    500)
        .addNumeral("CD",   400)
        .addNumeral("C",    100)
        .addNumeral("XC",   90)
        .addNumeral("L",    50)
        .addNumeral("XL",   40)
        .addNumeral("X",    10)
        .addNumeral("IX",   9)
        .addNumeral("V",    5)
        .addNumeral("IV",   4)
        .addNumeral("I",    1)
        .toArray();

    // extend Number
    
    if (!Number.prototype.toRoman) {
        Number.prototype.toRoman = function romanize() {
            var digits = [ ];
            var index = 0;
            var value = Math.abs(this.valueOf());
            while (value >= 1) {
                var roman = numerals[index];
                if (value >= roman.value) {
                    digits.push(roman.digit);
                    value -= roman.value;
                } else {
                    index++;
                }
            }
            if (digits.length === 0)
            {
                digits.push(0);
            }
            return digits.join("");
        };
    }
    
    if (!Number.parseRoman) {
        Number.parseRoman = function(input) {
            input = input.toUpperCase();
            var index = 0;
            var value = 0;
            var pos = 0;
            while (index < numerals.length) {
                var numeral = numerals[index];
                var length = numeral.digit.length;
                var digit = input.substr(pos, length);
                if (digit === numeral.digit) {
                    value += numeral.value;
                    pos += length;
                } else {
                    index++;
                }
            }
            if (pos < input.length)
            {
                value = Number.NaN;
            }
            return value;
        };
    }
    
    if (typeof define === "function") {
        define(function() {
            return Number;
        });
    }
    
})();