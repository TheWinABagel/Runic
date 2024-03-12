package dev.bagel.runic.spell.spells;

import dev.bagel.runic.registry.rune_registry.RuneType;
import dev.bagel.runic.spell.Spell;
import dev.bagel.runic.spell.casting.CastType;
import dev.shadowsoffire.placebo.util.PlaceboUtil;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.BlockHitResult;

public class BasicBreakSpell extends Spell {
    public BasicBreakSpell(int xp, CastType type) {
        super(xp, type);
//        setCostForRune(RuneType.EARTH, 1);
    }

    @Override
    public InteractionResult onHitBlock(Level level, Player caster, BlockHitResult hitResult, ItemStack usedStack, Spell spell) {
        if (level.isClientSide()) return InteractionResult.PASS;
        if (caster instanceof ServerPlayer sp) {
            PlaceboUtil.tryHarvestBlock(sp, hitResult.getBlockPos());
        }

        return InteractionResult.SUCCESS;
    }
}
