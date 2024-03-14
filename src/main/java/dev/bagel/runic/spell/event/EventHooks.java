package dev.bagel.runic.spell.event;

import dev.bagel.runic.registry.entity.SpellProjectileEntity;
import dev.bagel.runic.spell.Spell;
import dev.bagel.runic.spell.casting.CastType;
import dev.bagel.runic.spell.modifiers.SpellModifier;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class EventHooks {

    public static SpellCastEvent spellCastEvent(Player player, Spell spell, ItemStack usedStack, @Nullable Entity hitEntity, @Nullable SpellProjectileEntity spellProjectile,
                                      List<SpellModifier> modifiers, CastType type) {
        return new SpellCastEvent(player, spell, usedStack, hitEntity, spellProjectile, modifiers, type);
    }
}
