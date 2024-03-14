package dev.bagel.runic.spell.modifiers.modifiers.generic;

import dev.bagel.runic.registry.RunicRegistry;
import dev.bagel.runic.spell.modifiers.SpellModifier;

public class BlankModifier extends SpellModifier {

    public BlankModifier() {
        super(RunicRegistry.Spells.EMPTY.get());
    }

}
