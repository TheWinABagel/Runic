package dev.bagel.runic.cca.entity;

import dev.bagel.runic.reg.rune_registry.RuneType;
import dev.bagel.runic.spell.Spell;
import it.unimi.dsi.fastutil.objects.Object2IntMap;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Player;

public class SpellProvider {

    public static Spell getSelectedSpell(Player player) {
        return SpellComponent.SPELL_COMPONENT.get(player).getSpell();
    }

    public static void setSpell(Player player, Spell spell) {
        SpellComponent.SPELL_COMPONENT.get(player).setSpell(spell);
    }
}
