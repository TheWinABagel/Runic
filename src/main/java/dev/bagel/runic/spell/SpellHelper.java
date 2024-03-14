package dev.bagel.runic.spell;

import dev.bagel.runic.registry.RunicRegistry;
import net.minecraft.world.entity.player.Player;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class SpellHelper {
    public static Map<Spell, SpellInstance> getSpells(Player player) {
        Map<Spell, SpellInstance> map = new HashMap<>();
        if (player == null) return Collections.emptyMap();
        Spell spell = player.getData(RunicRegistry.Attachments.SPELL).getSpell();

//        map.put(spell, new SpellInstance(spell, player.getData(RunicRegistry.Attachments.SPELL_MODIFIERS).getSelectedModifiersForSpell(spell), player));
        return map;
    }
}
