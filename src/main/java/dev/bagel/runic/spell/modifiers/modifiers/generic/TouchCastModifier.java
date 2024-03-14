package dev.bagel.runic.spell.modifiers.modifiers.generic;

import dev.bagel.runic.spell.Spell;
import dev.bagel.runic.spell.casting.CastType;
import dev.bagel.runic.spell.modifiers.SpellModifier;

public class TouchCastModifier extends SpellModifier {
    public TouchCastModifier() {
        super((Spell) null);
        this.setCastType(CastType.TOUCH);
    }

}
