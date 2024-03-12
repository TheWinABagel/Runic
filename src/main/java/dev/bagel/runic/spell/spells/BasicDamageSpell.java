package dev.bagel.runic.spell.spells;

import dev.bagel.runic.registry.rune_registry.RuneType;
import dev.bagel.runic.spell.Spell;
import dev.bagel.runic.spell.casting.CastType;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class BasicDamageSpell extends Spell {
    public BasicDamageSpell(int level, int castXp, RuneType primaryRune, CastType type) {
        super(level, castXp, primaryRune, type);
    }

    @Override
    public InteractionResult onHitEntity(Level level, Player player, LivingEntity target, ItemStack usedStack, Spell spell) {
        target.hurt(level.damageSources().playerAttack(player), 5f);
        return InteractionResult.SUCCESS;
    }
}
