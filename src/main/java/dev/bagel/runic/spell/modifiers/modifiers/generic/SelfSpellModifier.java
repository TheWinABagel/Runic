package dev.bagel.runic.spell.modifiers.modifiers.generic;

import dev.bagel.runic.spell.Spell;
import dev.bagel.runic.spell.casting.CastType;
import dev.bagel.runic.spell.modifiers.SpellModifier;

public class SelfSpellModifier extends SpellModifier {
    public SelfSpellModifier() {
        super((Spell) null);
        this.setCastType(CastType.SELF);
    }
}
