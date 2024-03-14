package dev.bagel.runic.spell.spells;

import dev.bagel.runic.Runic;
import dev.bagel.runic.spell.Spell;
import dev.bagel.runic.spell.modifiers.SpellModifier;
import dev.bagel.runic.spell.modifiers.SpellModifierRegistry;
import it.unimi.dsi.fastutil.objects.Object2IntMap;
import it.unimi.dsi.fastutil.objects.Object2IntOpenHashMap;

public class EmptySpell extends Spell {
    public EmptySpell() {
        super(0);
    }

    @Override
    public Object2IntMap<SpellModifier> defaultModifier() {
        Object2IntMap<SpellModifier> map = new Object2IntOpenHashMap<>();
        map.put(SpellModifierRegistry.INSTANCE.getValue(Runic.loc("base_modifier")), 4);
        return map;
    }
}
