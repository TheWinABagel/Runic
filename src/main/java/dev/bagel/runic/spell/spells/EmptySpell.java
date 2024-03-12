package dev.bagel.runic.spell.spells;

import dev.bagel.runic.registry.RunicRegistry;
import dev.bagel.runic.spell.Spell;
import dev.bagel.runic.spell.modifiers.SpellModifier;

public class EmptySpell extends Spell {
    public EmptySpell() {
        super(0);
    }

    @Override
    public SpellModifier defaultModifier() {
        return RunicRegistry.SpellModifiers.PROJECTILE_MODIFIER.get();
    }
}
