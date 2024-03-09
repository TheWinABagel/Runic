package dev.bagel.runic.spell.spells;

import dev.bagel.runic.registry.rune_registry.RuneType;
import dev.bagel.runic.spell.Spell;
import dev.bagel.runic.spell.casting.CastType;

public class BasicDamageSpell extends Spell {
    public BasicDamageSpell(int level, int castXp, RuneType primaryRune, CastType type) {
        super(level, castXp, primaryRune, type);
    }
}
