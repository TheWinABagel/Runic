package dev.bagel.runic.spell.spells;

import dev.bagel.runic.registry.RunicRegistry;
import dev.bagel.runic.spell.Spell;
import dev.bagel.runic.spell.modifiers.SpellModifier;
import dev.shadowsoffire.placebo.util.PlaceboUtil;
import it.unimi.dsi.fastutil.objects.Object2IntMap;
import it.unimi.dsi.fastutil.objects.Object2IntOpenHashMap;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;

public class BasicBreakSpell extends Spell {
    public BasicBreakSpell(int xp) {
        super(xp);
    }

    @Override
    public Object2IntMap<SpellModifier> defaultModifier() {
        Object2IntMap<SpellModifier> map = new Object2IntOpenHashMap<>();
        map.put(RunicRegistry.SpellModifiers.TOUCH_MODIFIER.get(), 1);
        return map;
    }

    @Override
    public InteractionResult onHitBlock(Level level, Player player, ItemStack usedStack, Spell spell, UseOnContext context) {
        if (level.isClientSide()) return InteractionResult.PASS;

        PlaceboUtil.tryHarvestBlock((ServerPlayer) player, context.getClickedPos());

        return InteractionResult.SUCCESS;
    }
}
