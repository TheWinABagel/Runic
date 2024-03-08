package dev.bagel.runic.spell.spells;

import dev.bagel.runic.reg.rune_registry.RuneType;
import dev.bagel.runic.spell.Spell;
import dev.bagel.runic.spell.casting.CastType;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class BasicEffectSpell extends Spell {
    public BasicEffectSpell(int level, RuneType primaryRune, int castXp, CastType type) {
        super(level, castXp, primaryRune, type);
    }

    @Override
    public InteractionResult onSpellCast(Level level, Player player, ItemStack usedStack) {
        player.removeEffect(MobEffects.DAMAGE_RESISTANCE);
        player.addEffect(new MobEffectInstance(MobEffects.DAMAGE_RESISTANCE, 100, 2));
        player.sendSystemMessage(Component.literal("casting basic effect spell"));
        return InteractionResult.SUCCESS;
    }

}
