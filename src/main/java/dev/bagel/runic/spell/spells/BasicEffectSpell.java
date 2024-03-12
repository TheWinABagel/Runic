package dev.bagel.runic.spell.spells;

import dev.bagel.runic.registry.RunicRegistry;
import dev.bagel.runic.spell.Spell;
import dev.bagel.runic.spell.modifiers.SpellModifier;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class BasicEffectSpell extends Spell {
    public BasicEffectSpell(int castXp) {
        super(castXp);
    }

    @Override
    public SpellModifier defaultModifier() {
        return RunicRegistry.SpellModifiers.SELF_MODIFIER.get();
    }

    @Override
    public InteractionResult onCast(Level level, Player player, ItemStack usedStack, Spell spell) {
        player.removeEffect(MobEffects.DAMAGE_RESISTANCE);
        player.addEffect(new MobEffectInstance(MobEffects.DAMAGE_RESISTANCE, 100, 2));
        player.sendSystemMessage(Component.literal("casting basic effect spell"));
        return InteractionResult.SUCCESS;
    }

}
