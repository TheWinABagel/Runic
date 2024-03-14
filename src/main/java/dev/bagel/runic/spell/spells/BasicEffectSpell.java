package dev.bagel.runic.spell.spells;

import dev.bagel.runic.registry.RunicRegistry;
import dev.bagel.runic.spell.Spell;
import dev.bagel.runic.spell.modifiers.SpellModifier;
import it.unimi.dsi.fastutil.objects.Object2IntMap;
import it.unimi.dsi.fastutil.objects.Object2IntOpenHashMap;
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
    public Object2IntMap<SpellModifier> defaultModifier() {
        Object2IntMap<SpellModifier> map = new Object2IntOpenHashMap<>();
        map.put(RunicRegistry.SpellModifiers.SELF_MODIFIER.get(), 1);
        return map;
    }

    @Override
    public InteractionResult onCastTail(Level level, Player player, ItemStack usedStack, Spell spell) {
        player.removeEffect(MobEffects.DAMAGE_RESISTANCE);
        player.addEffect(new MobEffectInstance(MobEffects.DAMAGE_RESISTANCE, 100, 2));
        player.sendSystemMessage(Component.literal("casting basic effect spell"));
        return InteractionResult.SUCCESS;
    }

}
