package dev.bagel.runic.spell.modifiers.modifiers;

import dev.bagel.runic.spell.casting.CastContext;
import dev.bagel.runic.spell.casting.CastType;
import dev.bagel.runic.spell.modifiers.SpellModifier;

public class SelfSpellModifier extends SpellModifier {
    public SelfSpellModifier() {
        super();
    }

    @Override
    public void apply(CastContext context) {
        context.setCastType(CastType.SELF);
    }
}
