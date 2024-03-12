package dev.bagel.runic.spell.casting;

import dev.bagel.runic.registry.entity.SpellProjectileEntity;
import dev.bagel.runic.spell.Spell;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

public record CastContext(Level level, Player caster, Spell spell, ItemStack usedStack, @Nullable Entity hitEntity, @Nullable SpellProjectileEntity spellProjectile) {
}
