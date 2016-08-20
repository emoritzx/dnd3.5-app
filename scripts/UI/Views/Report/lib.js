define(
    [
        "./Heading",
        "./AttributeCollectionViewer",
        "./AbilityCollectionViewer",
        "./CharacterLevelCollectionViewer",
        "./SkillCollectionViewer",
        "./StatisticsViewer",
        "./EnhancementCollectionViewer"
    ],
    function(
        Heading,
        AttributeCollectionViewer,
        AbilityCollectionViewer,
        CharacterLevelCollectionViewer,
        SkillCollectionViewer,
        StatisticsViewer,
        EnhancementCollectionViewer
    ) {
    
        "use strict";
        
        var CSS_CLASS = "DnD.Report";
        
        var View = function(character) {
            var container = document.createElement("DIV");
            container.className = CSS_CLASS;
            this._container = container;
            createUI(container, character);
        };
        
        Object.defineProperties(View.prototype,
        {
            element: {
                get: function() {
                    return this._container;
                }
            }
        });
        
        function createUI(container, character) {
            var parts = [
                new Heading(character),
                new AttributeCollectionViewer(character.attributes),
                new StatisticsViewer(character),
                new AbilityCollectionViewer(character.abilities),
                new CharacterLevelCollectionViewer(character),
                new SkillCollectionViewer(character),
                new EnhancementCollectionViewer(character.enhancements),
            ];
            for (var p = 0; p < parts.length; p++) {
                container.appendChild(parts[p]);
            }
        }

        return View;
    }
);