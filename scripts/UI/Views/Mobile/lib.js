define(
    [
        "./Viewport",
        "./AttributeCollectionViewer",
        "./AbilityCollectionViewer",
        "./CharacterLevelCollectionViewer",
        "./SkillCollectionViewer",
        "./StatisticsViewer",
        "./EnhancementCollectionViewer"
    ],
    function(
        Viewport,
        AttributeCollectionViewer,
        AbilityCollectionViewer,
        CharacterLevelCollectionViewer,
        SkillCollectionViewer,
        StatisticsViewer,
        EnhancementCollectionViewer
    ) {
    
        "use strict";
        
        var View = function(character) {
            var container = new Viewport();
            this._container = container;
            createUI(container, character);
        };
        
        Object.defineProperties(View.prototype,
        {
            element: {
                get: function() {
                    return this._container.element;
                }
            }
        });
        
        function createUI(container, character) {
            var parts = [
                [ "Attributes", new AttributeCollectionViewer(character.attributes) ],
                [ "Statistics", new StatisticsViewer(character) ],
                [ "Ability Scores", new AbilityCollectionViewer(character.abilities) ],
                [ "Levels", new CharacterLevelCollectionViewer(character) ],
                [ "Skills", new SkillCollectionViewer(character) ],
                [ "Enhancements", new EnhancementCollectionViewer(character.enhancements) ],
            ];
            for (var p = 0; p < parts.length; p++) {
                var part = parts[p];
                container.addEntry(part[0], part[1]);
            }
        }
        
        View.prototype.show = function(name) {
            this._container.show(name);
        };

        return View;
    }
);