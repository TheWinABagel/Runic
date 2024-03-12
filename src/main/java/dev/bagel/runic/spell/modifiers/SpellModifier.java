package dev.bagel.runic.spell.modifiers;

import dev.bagel.runic.spell.Spell;

public abstract class SpellModifier {
    private Spell spell;
    public SpellModifier(Spell modifiedSpell) {
        this.spell = modifiedSpell;
    }


}
