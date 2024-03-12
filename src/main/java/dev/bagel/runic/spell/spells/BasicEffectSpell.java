package dev.bagel.runic.spell.spells;

import dev.bagel.runic.registry.rune_registry.RuneType;
import dev.bagel.runic.spell.Spell;
import dev.bagel.runic.spell.casting.CastType;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.BlockHitResult;

public class BasicEffectSpell extends Spell {
    public BasicEffectSpell(int castXp, CastType type) {
        super(castXp, type);
    }

    @Override
    public InteractionResult onCast(Level level, Player player, ItemStack usedStack, Spell spell) {
        player.removeEffect(MobEffects.DAMAGE_RESISTANCE);
        player.addEffect(new MobEffectInstance(MobEffects.DAMAGE_RESISTANCE, 100, 2));
        player.sendSystemMessage(Component.literal("casting basic effect spell"));
        return InteractionResult.SUCCESS;
    }

}
