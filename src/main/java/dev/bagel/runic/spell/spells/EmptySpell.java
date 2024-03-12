package dev.bagel.runic.spell.spells;

import dev.bagel.runic.registry.rune_registry.RuneType;
import dev.bagel.runic.spell.Spell;
import dev.bagel.runic.spell.casting.CastType;

public class EmptySpell extends Spell {
    public EmptySpell() {
        super(0, CastType.NONE);
    }
}
